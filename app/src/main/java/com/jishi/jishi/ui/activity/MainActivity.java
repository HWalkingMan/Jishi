package com.jishi.jishi.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jishi.jishi.R;
import com.jishi.jishi.business.AccountBiz;
import com.jishi.jishi.business.impl.AccountBizImpl;
import com.jishi.jishi.entity.account.Account;
import com.jishi.jishi.entity.response.BusinessException;
import com.jishi.jishi.entity.response.CommonReturnType;
import com.jishi.jishi.util.DrawableUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Method;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountBiz = new AccountBizImpl();
        initView();

        initHeaderView();
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

        initNavigationView();
        initAccountData();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void initHeaderView() {
        imv_navigation_avatar = navigationView.getHeaderView(0).findViewById(R.id.imv_navigation_avatar);
        txv_navigation_nickname = navigationView.getHeaderView(0).findViewById(R.id.txv_navigation_nickname);
        txv_navigation_signature = navigationView.getHeaderView(0).findViewById(R.id.txv_navigation_signature);
    }

    private void initNavigationView() {
        LinearLayout nav_menu_nightmode = findViewById(R.id.nav_menu_nightmode);
        nav_menu_nightmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(TOKEN);
                editor.remove(ACCOUNTID);
                editor.apply();
                Toast.makeText(MainActivity.this, "已退出登录", Toast.LENGTH_SHORT).show();
                MainActivity.this.startActivity(new Intent(MainActivity.this, CoverActivity.class));
                MainActivity.this.finish();
            }
        });
    }

    private void initAccountData() {
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(MY_PRE_NAME, Context.MODE_PRIVATE);
        int accountid = sharedPreferences.getInt(ACCOUNTID, -1);
        if (accountid == -1) {
            Toast.makeText(this, "error!", Toast.LENGTH_SHORT).show();
            Log.e("findId", "id is -1");
        }
        if (imv_navigation_avatar == null) {
            Log.i("imv_avatar", "imv");
            imv_navigation_avatar = findViewById(R.id.imv_navigation_avatar);
        }
        Log.i("initAccountData", "initAccountData");
        accountBiz.getAccount(MainActivity.this, accountid, new AccountBiz.OnGetSuccessListener() {
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
                            .into(imv_navigation_avatar, new Callback() {
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

                // FIXME: 2020/6/2 toolbar.setNavigationIcon();
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
