package com.xiaolei.easyfreamtest;

import android.content.Context;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.utils.Log;
import com.xiaolei.exretrofitcallback.network.regist.OnCallBack;
import com.xiaolei.exretrofitcallback.network.regist.ResponseBeanRegister;

/**
 * Created by admin on 2017/7/13.
 */

public class DTBeanRegist extends ResponseBeanRegister<DTBean>
{
    @Override
    public String filter(DTBean s)
    {
        return "/aaaa";
    }

    @OnCallBack(value = "/aaaa", stopNextStep = false)
    public void haveToLogin(Context context, DTBean bean)
    {
        android.util.Log.d("DTBeanRegist", "无情拦截！");
    }
}
