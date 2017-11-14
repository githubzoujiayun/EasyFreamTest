package com.xiaolei.easyfreamtest;

import android.content.Context;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.network.common.IUnifiedFailEvent;
import com.xiaolei.easyfreamwork.network.common.SICallBack;

/**
 * Created by xiaolei on 2017/11/14.
 */

public class FailEvent implements IUnifiedFailEvent
{
    @Override
    public void onFail(SICallBack callBack, Throwable t, Context context)
    {
        Toast.makeText(context,"网络开小差啦！！",Toast.LENGTH_SHORT).show();
    }
}
