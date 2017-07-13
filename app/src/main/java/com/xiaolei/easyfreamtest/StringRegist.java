package com.xiaolei.easyfreamtest;

import android.content.Context;

import com.xiaolei.easyfreamwork.annotations.OnCallBack;
import com.xiaolei.easyfreamwork.network.common.SICallBack;
import com.xiaolei.easyfreamwork.network.regist.Regist;
import com.xiaolei.easyfreamwork.utils.Log;

/**
 * Created by admin on 2017/7/13.
 */

public class StringRegist extends Regist<String>
{
    @Override
    public String filter(String s)
    {
        return "/aaaa";
    }

    @OnCallBack("/aaaa")
    public void haveToLogin(Context context, SICallBack callBack)
    {
        Log.e("haveToLogin", "haveToLogin");
    }
}
