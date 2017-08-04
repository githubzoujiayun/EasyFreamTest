package com.xiaolei.easyfreamwork.network.regist;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.annotations.OnCallBack;
import com.xiaolei.easyfreamwork.common.listeners.Action;

import java.lang.reflect.Method;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by xiaolei on 2017/7/12.
 */

public abstract class Regist<T>
{
    private Method[] methods;
    private ArrayMap<String, Method> arrayMap = new ArrayMap<>();

    private Method[] getMethods()
    {
        if (methods == null)
        {
            methods = this.getClass().getDeclaredMethods();
        }
        return methods;
    }

    public Method getMethod(String callBackStr)
    {
        if (arrayMap.isEmpty())
        {
            Method[] methods = getMethods();
            if (methods != null)
            {
                for (Method method : methods)
                {
                    OnCallBack callBack = method.getAnnotation(OnCallBack.class);
                    if (callBack != null)
                    {
                        String defValue = callBack.value();
                        arrayMap.put(defValue, method);
                    }
                }
            }
        }
        return arrayMap.get(callBackStr);
    }

    /**
     * 过滤器，作为一个结果过滤器，返回的结果应该是个字符串
     * 如果不需要过滤，则返回的是一个 null
     *
     * @param t
     * @return
     */
    public abstract
    @Nullable
    String filter(T t);

    /**
     * 弹出提示框
     * @param context       上下文
     * @param obj           提示信息
     * @param rightListener 右边点击事件
     */
    public void Alert(Context context, Object obj, Action rightListener)
    {
        Alert(context, obj, null, null, "确定", rightListener);
    }

    /**
     * 弹出提示框
     * @param context       上下文
     * @param obj           提示内容
     * @param rightText     右边文字
     * @param rightListener 右边点击事件
     */
    public void Alert(Context context, Object obj, String rightText, Action rightListener)
    {
        Alert(context, obj, null, null, rightText, rightListener);
    }

    /**
     * 弹出提示框
     * @param context       上下下文
     * @param obj           显示内容 
     * @param leftText      左边文字
     * @param leftListener  左边点击事件
     * @param rightText     右边内容
     * @param rightListener 右边点击事件
     */
    public void Alert(
            final Context context
            , final Object obj
            , final String leftText
            , final Action leftListener
            , final String rightText
            , final Action rightListener)
    {
        Observable.just(context).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Action1<Context>()
                {
                    @Override
                    public void call(Context context)
                    {
                        AlertDialog.Builder builder;
                        if (Activity.class.isInstance(context) || FragmentActivity.class.isInstance(context))
                        {


                            builder = new AlertDialog.Builder(context);
                            builder.setCancelable(false);
                            builder.setTitle("提示信息");
                            builder.setMessage(obj + "");
                            builder.setNegativeButton("确定", null);
                            if (leftText != null)
                            {
                                builder.setNeutralButton(leftText, leftListener);
                            }
                            if (rightText != null)
                            {
                                builder.setNegativeButton(rightText, rightListener);
                            }
                            builder.show();
                        } else
                        {
                            builder = new AlertDialog.Builder(context,
                                    R.style.Base_Theme_AppCompat_Light_Dialog);
                            builder.setCancelable(false);
                            builder.setTitle("提示信息");
                            builder.setMessage(obj + "");
                            builder.setNegativeButton("确定", null);
                            if (leftText != null)
                            {
                                builder.setNeutralButton(leftText, leftListener);
                            }
                            if (rightText != null)
                            {
                                builder.setNegativeButton(rightText, rightListener);
                            }
                            AlertDialog dialog = builder.create();
                            if (dialog.getWindow() != null)
                            {
                                dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
                            }
                            dialog.show();
                        }
                    }
                }, new Action1<Throwable>()
                {
                    @Override
                    public void call(Throwable throwable)
                    {
                        Toast.makeText(context, obj + "", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
