package com.zuowen.magic.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.umeng.analytics.MobclickAgent;
import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.bean.request.MagicPassWordRequest;
import com.zuowen.magic.bean.request.MagicSendCode;
import com.zuowen.magic.bean.response.PassWordResponse;
import com.zuowen.magic.bean.response.SendCodeResponse;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.http.MagicHttpClient;
import com.zuowen.magic.utils.UrlJsonUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by worm on 2016/1/19.
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener {


    private ImageView activity_login_cacel;
    private EditText user_email;
    private LinearLayout ll_email;
    private EditText et_validate;
    private Button bt_send;
    private LinearLayout ll_validate;
    private Button bt_validate_psw;
    private EditText user_psw;
    private LinearLayout ll_psw;
    private EditText user_repsw;
    private LinearLayout ll_repsw;

    private long mLastClickTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initViews();

    }


    private void initViews() {
        activity_login_cacel = findView(R.id.activity_login_cacel);
        user_email = findView(R.id.user_email);
        ll_email = findView(R.id.ll_email);
        et_validate = findView(R.id.et_validate);
        bt_send = findView(R.id.bt_send);
        ll_validate = findView(R.id.ll_validate);
        bt_validate_psw = findView(R.id.bt_validate_psw);
        user_psw = findView(R.id.user_psw);
        ll_psw = findView(R.id.ll_psw);
        user_repsw = findView(R.id.user_repsw);
        ll_repsw = findView(R.id.ll_repsw);
        activity_login_cacel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickTime();
        switch (v.getId()) {
            case R.id.activity_login_cacel:
                 finish();
                break;
            case R.id.bt_send:
                MagicHttpClient.getInstance(this).post(UrlJsonUtil.URL + UrlJsonUtil.ZW + UrlJsonUtil.SENDCODE,
                        UrlJsonUtil.getSendcodeJson(user_email.getText().toString(), new MagicSendCode()),
                        new HttpLoadListener<SendCodeResponse>() {
                            @Override
                            public void onSuccess(SendCodeResponse model) {


                            }
                        });
                break;
            case R.id.bt_validate_psw:
                final SweetAlertDialog pDialog = new SweetAlertDialog(ForgetActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                MagicHttpClient.getInstance(this).post(UrlJsonUtil.URL + UrlJsonUtil.ZW + UrlJsonUtil.SETPWD,
                        UrlJsonUtil.getPassWordJson(user_email.getText().toString(), user_psw.getText().toString(),
                                user_repsw.getText().toString(), et_validate.getText().toString(),
                                new MagicPassWordRequest()),
                        new HttpLoadListener<PassWordResponse>() {
                            @Override
                            public void onStart() {
                                super.onStart();
                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Loading");
                                pDialog.setCancelable(false);
                                pDialog.show();
                            }

                            @Override
                            public void onSuccess(PassWordResponse model) {
                                pDialog.dismiss();

                                toast(model.msg);
                                finish();
                            }
                        });
                break;


        }
    }


    public void clickTime() {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }
    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("ForgetActivity");
        MobclickAgent.onResume(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("ForgetActivity");
        MobclickAgent.onPause(this);
    }

}
