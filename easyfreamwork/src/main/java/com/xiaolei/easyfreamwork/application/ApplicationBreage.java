package com.xiaolei.easyfreamwork.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.network.OKhttp.PersistentCookieStore;

import retrofit2.Retrofit;

/**
 * 这个的存在，是为了与用户的application彻底分离开来
 * 可能用户有超过65535的情况出现，或者需要继承别的第三方的application
 * Created by xiaolei on 2017/7/9.
 */

public class ApplicationBreage implements IApp
{
    private IApp application;
    private static ApplicationBreage instance;
    /**
     * 是否已经显示了一次网络错误的提示
     */
    public static boolean hasNetworkError = false;
    private ApplicationBreage()
    {
    }
    public synchronized static ApplicationBreage getInstance()
    {
        if(instance == null)
        {
            instance = new ApplicationBreage();
        }
        return instance;
    }
    public void initApplication(IApp iApp, Config config)
    {
        instance.application = iApp;
    }
    
    
    @Override
    public void addActivity(Activity activity)
    {
        application.addActivity(activity);
    }

    @Override
    public void removeActivity(Activity activity)
    {
        application.removeActivity(activity);
    }

    @Override
    public void removeAllActivity()
    {
        application.removeAllActivity();
    }

    @Override
    public Application getApplication()
    {
        return application.getApplication();
    }

    @Override
    public Context getApplicationContext()
    {
        return application.getApplicationContext();
    }

    @Override
    public Context getContext()
    {
        return application.getContext();
    }
}
