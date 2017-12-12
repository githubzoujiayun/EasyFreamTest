package com.xiaolei.easyfreamwork.network.common;

import android.content.Context;

import com.xiaolei.easyfreamwork.AlertDialog.CustomAlertDialog;
import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


/**
 * 默认的统一的失败提示接口
 * Created by xiaolei on 2017/11/14.
 */
public class DefaultUnifiedFailEvent implements IUnifiedFailEvent
{
    @Override
    public void onFail(SICallBack callBack, Throwable t, Context context)
    {
        String alertStr = t.toString();
        if (SocketTimeoutException.class.isInstance(t)
                || IOException.class.isInstance(t)
                || ConnectException.class.isInstance(t))
        {
            alertStr = ">_<||| 网络开小差，请稍后重试。";
            if (ApplicationBreage.networkErrorTimes == 1)
            {
                Alert(alertStr, callBack);
            }
        } else
        {
            Alert(alertStr, callBack);
        }
    }

    private void Alert(Object obj, final SICallBack callBack)
    {
        Observable.just(obj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        new CustomAlertDialog(callBack, Config.dialog_layout).Alert(o);
                    }
                });
    }
}
