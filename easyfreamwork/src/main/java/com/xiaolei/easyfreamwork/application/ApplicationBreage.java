package com.xiaolei.easyfreamwork.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.Config.Globals;

import java.net.CookieStore;

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
    /**
     * 发送刷新消息
     *
     * @param ACTION 消息类型 Globals.Action.REFRESH.*
     */
    public void sendRefreshAction(String ACTION)
    {
        sendRefreshAction(ACTION, true);
    }

    /**
     * 发送刷新消息
     *
     * @param ACTION    消息类型 Globals.Action.REFRESH.*
     * @param condition 刷新条件
     */
    public void sendRefreshAction(String ACTION, boolean condition)
    {
        if (condition)
        {
            Intent intent = new Intent(Globals.Action.RefreshRecever_Action);
            intent.putExtra("ACTION", ACTION);
            LocalBroadcastManager.getInstance(getInstance().getContext()).sendBroadcast(intent);
        }
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
    public Retrofit getRetrofit()
    {
        return application.getRetrofit();
    }

    @Override
    public CookieStore getCookieStore()
    {
        return application.getCookieStore();
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
