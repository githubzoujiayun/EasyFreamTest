package com.xiaolei.easyfreamtest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.annotations.OnCallBack;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.application.IApp;
import com.xiaolei.easyfreamwork.network.BaseNetCore;
import com.xiaolei.easyfreamwork.network.common.SICallBack;
import com.xiaolei.easyfreamwork.network.regist.Regist;
import com.xiaolei.easyfreamwork.network.regist.RegisteTable;
import com.xiaolei.easyfreamwork.utils.Log;

import java.net.CookieStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class APP extends Application implements IApp
{
    List<Activity> activities = new ArrayList<>();

    @Override
    public void onCreate()
    {
        Config config = new Config();
        config.setDEBUG(true);
        config.setBaseUrl("http://www.baidu.com/");
        Map<String, String> head = new HashMap<>();
        head.put("from", "xiaolei");
        config.setNetHeadeMap(head);
        ApplicationBreage.getInstance().initApplication(this, config);
        RegisteTable.getInstance().regist(String.class, StringRegist.class);
        super.onCreate();
    }

    @Override
    public void addActivity(Activity activity)
    {
        activities.add(activity);
    }

    @Override
    public void removeActivity(Activity activity)
    {
        activities.remove(activity);
    }

    @Override
    public void removeAllActivity()
    {
        for (Activity activity : activities)
        {
            activity.finish();
        }
    }

    @Override
    public Application getApplication()
    {
        return this;
    }

    @Override
    public Retrofit getRetrofit()
    {
        return BaseNetCore.getInstance().getRetrofit();
    }

    @Override
    public CookieStore getCookieStore()
    {
        return null;
    }

    @Override
    public Context getContext()
    {
        return this;
    }
    
    
    
    public static class StringRegist extends Regist<String>
    {
        @Override
        public String filter(String s)
        {
            return s;
        }
        @OnCallBack("/aaaa")
        public void haveToLogin(Context context, SICallBack callBack)
        {
            Log.e("haveToLogin", "haveToLogin");
        }
    }

}
