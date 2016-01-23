package com.zuowen.magic.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.zuowen.magic.bean.request.ArtListRequest;
import com.zuowen.magic.bean.request.MagicContributeRequset;
import com.zuowen.magic.bean.request.MagicLoginRequest;
import com.zuowen.magic.bean.request.MagicRegistRequset;
import com.zuowen.magic.bean.request.MagicSendCode;

/**
 * Created by worm on 2015/12/22.
 */
public class UrlJsonUtil {
    /**
     * url数据
     */
    public static final String URL="http://app.zuowen.com";
    public static final String ZWART="/zwart";
    public static final String ZWUSER="/zwuser";
    public static final String LIST="/list";
    public static final String ZW="/zw";
    public static final String DETAIL="/detail";
    public static final String LOGIN="/login";
    public static final String ADDARTPAGE="/addartpage";
    public static final String REGISTER="/register";
    public static final String SENDCODE="/sendcode";



    private static Gson mGson = new Gson();


    /**
     * 获取文章列表
     */
    public static String getArtListJson(String category,String grade,int page) {
        ArtListRequest request=new ArtListRequest();
        request.category=category;
        request.grade=grade;
        request.page=page;
        String json=mGson.toJson(request);
        Log.v("UrlJsonUtil",json);
        return json;
    }
    public  static String getContributeJson(String str1, MagicContributeRequset request) {

        request.auth=str1;

        String json = mGson.toJson(request);
        return json;
    }


    public  static String getLoginJson(String str1, String str2,String str3,MagicLoginRequest request) {
        try{
            request.userName=str1;
            request.password=str2;
            request.from=str3;
            request.sign=MD5Encoder.encode(str1);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            String json = mGson.toJson(request);
            return json;
        }
    }
    public  static String getRegisterJson(String str1,String str2,String str3,String str4,String str5,String str6,
                                          MagicRegistRequset request) {

        request.nickname=str1;
        request.email=str2;
        request.psd=str3;
        request.psd1=str4;
        request.grade=str5;
        request.ip=str6;

        String json = mGson.toJson(request);
        return json;
    }
    public  static String getSendcode(String str1,MagicSendCode request) {

        request.email=str1;

        String json = mGson.toJson(request);
        return json;
    }
//    public  static String getDetailJson(String str1, MagicDetailRequest request) {
//
//        request.setId(str1);
//
//        String json = mGson.toJson(request);
//        return json;
//    }
//

//    public  static String getCheckJson(String str1, MagicCheckRequest request) {
//
//        request.setAuth(str1);
//
//        String json = mGson.toJson(request);
//        return json;
//    }
}
