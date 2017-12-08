package com.xiaolei.easyfreamtest;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.os.Handler;

import com.xiaolei.easyfreamwork.AlertDialog.CustomAlertDialog;
import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.application.IApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // http://gc.ditu.aliyun.com/geocoding?a=苏州市
        config.setBaseUrl("http://gc.ditu.aliyun.com/");
        config.regist(String.class, StringRegist.class);
        config.setUnifiedFailEventKlass(FailEvent.class);
        Map<String, String> head = new HashMap<>();
        head.put("from", "xiaolei");
        config.setNetHeadeMap(head);
        ApplicationBreage.getInstance().initApplication(this, config);
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
    public Context getContext()
    {
        return this;
    }
    
}
