package com.xiaolei.easyfreamwork.application;

import android.app.Application;
import android.content.Context;
import com.xiaolei.easyfreamwork.Config.Config;


/**
 * 这个的存在，是为了与用户的application彻底分离开来
 * 可能用户有超过65535的情况出现，或者需要继承别的第三方的application
 * Created by xiaolei on 2017/7/9.
 */

public class ApplicationBreage implements IApp
{
    private IApp application;
    private static ApplicationBreage instance;
    
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
