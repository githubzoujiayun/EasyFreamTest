package com.xiaolei.easyfreamtest;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by xiaolei on 2017/10/9.
 */

public class mService extends Service
{
    private mReceiver receiver = new mReceiver();
    @Override
    public void onCreate()
    {
        IntentFilter filter = new IntentFilter("com.xiaolei.hahaha");
        registerReceiver(receiver,filter);
        Log.d("mService", "注册");
        super.onCreate();
    }
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        unregisterReceiver(receiver);
        Log.d("mService", "取消注册");
        super.onDestroy();
    }
}