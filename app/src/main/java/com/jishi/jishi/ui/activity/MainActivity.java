package com.jishi.jishi.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.PathUtils;
import com.jishi.jishi.R;
import com.jishi.jishi.business.AccountBiz;
import com.jishi.jishi.business.impl.AccountBizImpl;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.response.CommonReturnType;
import com.jishi.jishi.util.DrawableUtils;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * @author WM
 * @description
 * @date 2020/2/22 18:08
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    public static final long MAX_EXIT_TIME = 2000;
    private final static String MY_PRE_NAME = "preferences";
    private final static String TOKEN = "TOKEN";
    private final static String ACCOUNTID = "ACCOUNTID";

    private AccountBiz accountBiz;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private ImageView imv_navigation_avatar;
    private TextView txv_navigation_nickname, txv_navigation_signature;

    private LinearLayout nav_menu_nightmode;
    private LinearLayout nav_menu_setting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountBiz = new AccountBizImpl();
        initView();

        setSupportActionBar(toolbar);

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//禁止手势滑动

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_menu_style, R.id.nav_menu_favourite, R.id.nav_menu_album,
                R.id.nav_menu_file, R.id.nav_menu_setting, R.id.nav_menu_nightmode)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        initDrawerViewListener();
        initAccountData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        initDrawerView();
    }

    private void initDrawerView() {
        initHeaderView();

        nav_menu_nightmode = findViewById(R.id.nav_menu_nightmode);
        nav_menu_setting = findViewById(R.id.nav_menu_setting);

    }

    private void initHeaderView() {
        imv_navigation_avatar = navigationView.getHeaderView(0).findViewById(R.id.imv_navigation_avatar);
        txv_navigation_nickname = navigationView.getHeaderView(0).findViewById(R.id.txv_navigation_nickname);
        txv_navigation_signature = navigationView.getHeaderView(0).findViewById(R.id.txv_navigation_signature);
    }

    private void initDrawerViewListener() {
        imv_navigation_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAvatar();
            }
        });

        nav_menu_nightmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "开启夜间模式", Toast.LENGTH_SHORT).show();
            }
        });
        nav_menu_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    private void uploadAvatar() {
        String[] mPermissionList = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(MainActivity.this, mPermissionList, 100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                boolean writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (grantResults.length > 0 && writeExternalStorage && readExternalStorage) {
                    getImage();
                } else {
                    Toast.makeText(this, "请设置必要权限", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public static final int REQUEST_PICK_IMAGE = 11101;

    private void getImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"),
                    REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PICK_IMAGE:
                    if (data != null) {
                        Bitmap bitmap;
                        try {
                            bitmap = BitmapFactory.decodeStream(MainActivity.this.getContentResolver().openInputStream(data.getData()));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        byte[] imgBytes = ImageUtils.bitmap2Bytes(bitmap, Bitmap.CompressFormat.JPEG);

                        //--------------------
                        String filename = PathUtils.getExternalDownloadsPath() + "/newpic.jpeg";
                        if (imgBytes.length < 3 || filename == null) {
                            Toast.makeText(this, "图片cuowu,huolujingcuowu", Toast.LENGTH_SHORT).show();
                        }
                        File file = new File(filename);
                        try {
                            FileOutputStream imageOutput = new FileOutputStream(file);
                            imageOutput.write(imgBytes, 0, imgBytes.length);
                            imageOutput.close();
                            System.out.println("Make Picture success,Please find image in " + file.getPath());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        Picasso.with(MainActivity.this)
                                .load(file)
                                .error(R.mipmap.ic_pic_error)
                                .noFade()
                                .fit()
                                .into(imv_navigation_avatar, new com.squareup.picasso.Callback() {
                                    @Override
                                    public void onSuccess() {
                                        Drawable drawable = imv_navigation_avatar.getDrawable();
                                        drawable = DrawableUtils.zoomAndRadiusDrawable(drawable, 250, 250, 125);
                                        toolbar.setNavigationIcon(drawable);
                                    }

                                    @Override
                                    public void onError() {
                                    }
                                });
                        //-------------------------
                        /*
                        accountBiz.uploadUserImage(MainActivity.this, new String(imgBytes), new AccountBiz.Callback<String>() {
                            @Override
                            public void onSuccess(CommonReturnType<String> returnType) {
                                String imgUrl = returnType.getData();
                                if (imgUrl != null) {
                                    Picasso.with(MainActivity.this)
                                            .load(imgUrl)
                                            .error(R.mipmap.ic_pic_error)
                                            .noFade()
                                            .fit()
                                            .into(imv_navigation_avatar, new com.squareup.picasso.Callback() {
                                                @Override
                                                public void onSuccess() {
                                                    Drawable drawable = imv_navigation_avatar.getDrawable();
                                                    drawable = DrawableUtils.zoomAndRadiusDrawable(drawable, 250, 250, 125);
                                                    toolbar.setNavigationIcon(drawable);
                                                }

                                                @Override
                                                public void onError() {
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFailed(Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                         */
                    } else {
                        Toast.makeText(this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    }




    private void initAccountData() {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
        int accountid = sharedPreferences.getInt(ACCOUNTID, -1);
        if (accountid == -1) {
            Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show();
            Log.e("findId", "id is -1");
        }
        Log.i("initAccountData", "initAccountData");
        accountBiz.getAccount(MainActivity.this, accountid, new AccountBiz.Callback<Account>() {
            @Override
            public void onSuccess(CommonReturnType<Account> returnType) {
                Account account = returnType.getData();
                if (account.getAvatarURL() != null) {
                    Picasso.with(MainActivity.this)
                            .load(account.getAvatarURL())
                            .placeholder(R.mipmap.ic_default_user_avatar)
                            .error(R.mipmap.ic_pic_error)
                            .noFade()
                            .fit()
                            .into(imv_navigation_avatar, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    Drawable drawable = imv_navigation_avatar.getDrawable();
                                    drawable = DrawableUtils.zoomAndRadiusDrawable(drawable, 250, 250, 125);
                                    toolbar.setNavigationIcon(drawable);
                                }

                                @Override
                                public void onError() {
                                }
                            });
                }
                txv_navigation_nickname.setText(account.getNickName());
                txv_navigation_signature.setText(account.getSignature());
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > MAX_EXIT_TIME) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
