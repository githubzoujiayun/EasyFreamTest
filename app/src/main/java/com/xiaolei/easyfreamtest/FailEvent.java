package com.xiaolei.easyfreamtest;

import android.content.Context;
import android.widget.Toast;

import com.xiaolei.exretrofitcallback.network.common.IFailEvent;
import com.xiaolei.exretrofitcallback.network.common.SICallBack;

/**
 * Created by xiaolei on 2017/11/14.
 */

public class FailEvent implements IFailEvent
{
    long lastTime = 0;
    @Override
    public void onFail(SICallBack callBack, Throwable t, Context context)
    {
        if(System.currentTimeMillis() - lastTime >= 10 * 1000)
        {
            Toast.makeText(context, "哇哦~网络有问题耶~", Toast.LENGTH_SHORT).show();
            lastTime = System.currentTimeMillis();
        }
    }
}
