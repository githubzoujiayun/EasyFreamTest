package com.xiaolei.easyfreamwork.network.common;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.Bean.ResBodyBean;
import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.common.callback.URIMethod;
import com.xiaolei.easyfreamwork.utils.Log;

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

/**
 * Created by xiaolei on 2017/7/9.
 */

public abstract class SICallBack<T> implements Callback<T>, Observer<T>
{
    SoftReference<Context> context;
    AlertDialog.Builder builder;
    private URIMethod uriMethod = URIMethod.getInstance();
    
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
            if (ResBodyBean.class.isInstance(bodyBean))
            {
                ResBodyBean body = (ResBodyBean)bodyBean;
                body.callback = "/aaaa";
                if (body.callback != null && !body.callback.isEmpty())
                {
                    Method method = uriMethod.getField(body.callback);
                    if (method != null)
                    {
                        if (!method.isAccessible()) 
                        {
                            method.setAccessible(true);
                        }
                        method.invoke(uriMethod, new Object[]{context.get(), this});
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

    protected void Alert(final Object obj)
    {
        Observable.just(obj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (context.get() != null)
                        {
                            Context context1 = context.get();
                            if (Activity.class.isInstance(context1)
                                    || FragmentActivity.class.isInstance(context1))
                            {
                                if ((Activity.class.isInstance(context1) && !((Activity) context1).isFinishing())
                                        || (FragmentActivity.class.isInstance(context1) && !((FragmentActivity) context1).isFinishing()))
                                {
                                    if (builder == null)
                                    {
                                        builder = new AlertDialog.Builder(context1);
                                        builder.setCancelable(false);
                                    }
                                    builder.setTitle("提示信息");
                                    builder.setMessage(o.toString() + "");
                                    builder.setNegativeButton("确定", null);
                                    builder.show();
                                } else
                                {
                                    Log.e("HttpRetrofit", "不是activity，或者activity已关闭");
                                }
                            } else
                            {
                                if (builder == null)
                                {
                                    builder = new AlertDialog.Builder(ApplicationBreage.getInstance().getContext(),
                                            R.style.Base_Theme_AppCompat_Light_Dialog);
                                    builder.setCancelable(false);
                                }
                                builder.setTitle("提示信息");
                                builder.setMessage(o.toString() + "");
                                builder.setNegativeButton("确定", null);

                                AlertDialog dialog = builder.create();
                                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
                                dialog.show();
                            }
                        }
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        Toast.makeText(context.get(), "" + obj, Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();
                    }
                });
    }
}
