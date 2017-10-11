package com.xiaolei.easyfreamwork.network.common;

import android.app.Fragment;
import android.content.Context;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.AlertDialog.CustomAlertDialog;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.network.regist.Regist;
import com.xiaolei.easyfreamwork.network.regist.RegisteTable;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaolei on 2017/7/9.
 */

public abstract class SICallBack<T> implements Callback<T>, Observer<T>
{
    public SoftReference<Context> context;
    private CustomAlertDialog alertDialog;

    public SICallBack(Context context)
    {
        this.context = new SoftReference(context);
    }

    public SICallBack(Fragment fragment)
    {
        this(fragment.getActivity());
    }

    public SICallBack(android.support.v4.app.Fragment fragment)
    {
        this(fragment.getActivity());
    }

    abstract void onSuccess(T result) throws Exception;

    abstract void onField(Throwable t);

    abstract void onFinally();

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        try
        {
            if (response.isSuccessful())
            {
                onNext(response.body());
            } else
            {
                onFailure(call, new IOException(response.code() + ""));
            }
        } catch (Exception e)
        {
            onFailure(call, new IOException(e));
        } finally
        {
            onFinally();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        try
        {
            if (context.get() != null)
            {
                showExceptionAlert(t);
                t.printStackTrace();
            }
            onField(t);
        } finally
        {
            onFinally();
        }
    }

    @Override
    public void onCompleted()
    {
        onFinally();
    }

    @Override
    public void onError(Throwable e)
    {
        try
        {
            showExceptionAlert(e);
            onField(e);
            e.printStackTrace();
        } finally
        {
            onFinally();
        }
    }

    @Override
    public void onNext(T bodyBean)
    {
        try
        {
            Class<? extends Regist> regist = RegisteTable.getInstance().getRegistValue(bodyBean);
            if (regist != null)
            {
                Regist registObj = RegisteTable.getInstance().getRegistObj(regist);
                if (registObj != null)
                {
                    String callback = registObj.filter(bodyBean);
                    if (callback != null && !callback.isEmpty())
                    {
                        Method method = registObj.getMethod(callback);
                        if (method != null)
                        {
                            Class paramTypes[] = method.getParameterTypes();
                            Object objs[] = new Object[paramTypes.length];
                            for (int i = 0; i < paramTypes.length; i++)
                            {
                                Class paramtype = paramTypes[i];
                                if (paramtype == Context.class)
                                {
                                    objs[i] = context.get();
                                } else if (paramtype.isInstance(bodyBean))
                                {
                                    objs[i] = bodyBean;
                                } else
                                {
                                    objs[i] = null;
                                }
                            }
                            if (!method.isAccessible())
                            {
                                method.setAccessible(true);
                            }
                            method.invoke(registObj, objs);
                            return;
                        }
                    }
                }
            }
            onSuccess(bodyBean);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            ApplicationBreage.hasNetworkError = false;
        }
    }

    /**
     * 显示错误提示
     */
    private void showExceptionAlert(Throwable e)
    {
        String alertStr = e.toString();
        if (SocketTimeoutException.class.isInstance(e)
                || retrofit2.adapter.rxjava.HttpException.class.isInstance(e)
                || IOException.class.isInstance(e)
                || ConnectException.class.isInstance(e))
        {
            alertStr = ">_<||| 网络开小差，请稍后重试。";
            if (!ApplicationBreage.hasNetworkError)
            {
                ApplicationBreage.hasNetworkError = true;
                Alert(alertStr);
            }
        } else
        {
            Alert(alertStr);
        }
    }

    private void Alert(Object obj)
    {
        Observable.just(obj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object object)
                    {
                        if (alertDialog == null)
                        {
                            alertDialog = new CustomAlertDialog(SICallBack.this, Config.dialog_layout);
                        }
                        alertDialog.Alert(object);
                    }
                });
    }
}
