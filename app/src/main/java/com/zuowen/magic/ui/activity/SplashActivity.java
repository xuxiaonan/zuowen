package com.zuowen.magic.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;

import com.umeng.analytics.MobclickAgent;
import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.MagicApplication;
import com.zuowen.magic.R;

public class SplashActivity extends BaseActivity {

    private Runnable runnable;
    private boolean guide;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MobclickAgent.setDebugMode( true );


        //延迟两秒进入主页面
        handler.postDelayed(runnable = new Runnable() {

            @Override
            public void run() {
                enterHome();

            }
        }, 1000);
//

    }

    //引导页
    public boolean getGuide() {
        guide = PreferenceManager.getDefaultSharedPreferences(
                this).getBoolean("guide", true);
        return guide;
    }

    /**
     * 进入主页面
     */
    protected void enterHome() {
        Intent intent =new Intent();
        if (getGuide()) {
            PreferenceManager.getDefaultSharedPreferences(
                    SplashActivity.this).edit().putBoolean("guide", false).commit();

            intent.setClass(SplashActivity.this,
                    SplashPageActivity.class);

        }else if(PreferenceManager.getDefaultSharedPreferences(SplashActivity.this).getBoolean(
                "login_state",false)){
            intent.setClass(SplashActivity.this, MainActivity.class);
        }else{
            intent.setClass(SplashActivity.this, LoginActivity.class);
        }



        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
            SplashActivity.this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashActivity");
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashActivity");
        MobclickAgent.onPause(this);
    }
}
