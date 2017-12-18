package com.xiaolei.easyfreamtest;

import android.app.Application;
import android.content.Context;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.application.IApp;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class APP extends Application implements IApp
{
    @Override
    public void onCreate()
    {
        Config config = new Config();
        config.setDEBUG(true);
        com.xiaolei.exretrofitcallback.network.Config.registResponseBean(DTBean.class,DTBeanRegist.class);
        com.xiaolei.exretrofitcallback.network.Config.setFailedEventClass(FailEvent.class);
        ApplicationBreage.getInstance().initApplication(this, config);
        super.onCreate();
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
