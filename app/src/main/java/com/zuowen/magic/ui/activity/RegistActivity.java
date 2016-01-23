package com.zuowen.magic.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zuowen.magic.BaseActivity;
import com.zuowen.magic.R;
import com.zuowen.magic.bean.request.MagicRegistRequset;
import com.zuowen.magic.bean.response.RegistResponse;
import com.zuowen.magic.http.HttpLoadListener;
import com.zuowen.magic.http.MagicHttpClient;
import com.zuowen.magic.utils.UrlJsonUtil;

import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by worm on 2016/1/18.
 */
public class RegistActivity extends BaseActivity{


    private ImageView activity_login_cacel;
    private EditText user_name;
    private LinearLayout ll_name;
    private EditText user_email;
    private LinearLayout ll_email;
    private EditText user_psw;
    private LinearLayout ll_psw;
    private EditText user_repsw;
    private LinearLayout ll_repsw;
    private TextView user_grade;
    private RelativeLayout rl_grade;
    private Button btnlogin;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initViews();
        initEvents();

    }

    private void initEvents() {
        activity_login_cacel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTime();
                //TODO  网络链接判断
                if (TextUtils.isEmpty(user_name.getText().toString())) {
                    user_name.setHint("请输入笔名");
                    hintColor(user_name);
                    return;
                }
                if (TextUtils.isEmpty(user_email.getText().toString())) {
                    user_email.setHint("请输入邮箱");
                    hintColor(user_email);
                    return;
                }
                if (TextUtils.isEmpty(user_psw.getText().toString())) {
                    user_psw.setHint("请输入密码");
                    hintColor(user_psw);
                    return;
                }
                if (TextUtils.isEmpty(user_repsw.getText().toString())) {
                    user_repsw.setHint("请输入确认密码");
                    hintColor(user_repsw);
                    return;
                }

                final SweetAlertDialog pDialog = new SweetAlertDialog(RegistActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                //TODO 实际应该去发送账号密码去服务端根据返回的数据判断
                MagicHttpClient.getInstance(RegistActivity.this).post(UrlJsonUtil.URL + UrlJsonUtil.ZWUSER + UrlJsonUtil.REGISTER,
                        UrlJsonUtil.getRegisterJson(user_name.getText().toString(), user_email.getText().toString(),
                                user_psw.getText().toString(), user_repsw.getText().toString(),
                                user_grade.getText().toString(),"", new MagicRegistRequset()),
                        new HttpLoadListener<RegistResponse>() {
                            @Override
                            public void onStart() {
                                super.onStart();
                                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                pDialog.setTitleText("Loading");
                                pDialog.setCancelable(false);
                                pDialog.show();
                            }

                            @Override
                            public void onSuccess(RegistResponse model) {
                                pDialog.dismiss();
                                if ("200".equals(model.code)) {
                                    Toast.makeText(RegistActivity.this, model.msg + "!", Toast.LENGTH_SHORT).show();
                                    finish();
                                    Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                                    startActivity(intent);

                                } else if ("400".equals(model.code)) {
                                    Toast.makeText(RegistActivity.this, model.msg + "!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            private void hintColor(EditText editText) {
                editText.setHintTextColor(getResources().getColor(
                        R.color.login_red));
            }


        });

    }

    public void clickTime(){
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
    }
    private void initViews() {
        activity_login_cacel = findView(R.id.activity_login_cacel);
        user_name = findView(R.id.user_name);
        ll_name =findView(R.id.ll_name);
        user_email = findView(R.id.user_email);
        ll_email =findView(R.id.ll_email);
        user_psw = findView(R.id.user_psw);
        ll_psw = findView(R.id.ll_psw);
        user_repsw = findView(R.id.user_repsw);
        ll_repsw = findView(R.id.ll_repsw);
        user_grade = findView(R.id.user_grade);
        rl_grade = findView(R.id.rl_grade);
        btnlogin =findView(R.id.btnlogin);
    }




}
