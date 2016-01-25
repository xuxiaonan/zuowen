package com.zuowen.magic.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.umeng.analytics.MobclickAgent;
import com.zuowen.magic.BaseFragment;
import com.zuowen.magic.R;

/**
 * Created by tool on 16/1/15.
 */
public class HomeFindFragment extends BaseFragment{
    @Override
    public View setView() {
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_find,null);
    }

    @Override
    public void initView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initOthers() {

    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("HomeContributeFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("HomeContributeFragment");
    }
}
