package com.xiaolei.easyfreamwork.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import com.xiaolei.easyfreamwork.Config.Globals;
import com.xiaolei.easyfreamwork.base.UIDataDelegate;
import com.xiaolei.easyfreamwork.common.RefreshActionMapping;
import com.xiaolei.easyfreamwork.utils.Log;

import java.lang.annotation.Annotation;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;

/**
 * Created by xiaolei on 2017/4/12.
 */

public class RefreshRecever extends BroadcastReceiver
{
    private static final String TAG = "RefreshRecever";
    private SoftReference<UIDataDelegate> uiDataInterface;
    public static final String ACTION = Globals.Action.RefreshRecever_Action;

    public RefreshRecever(UIDataDelegate uiDataDelegate)
    {
        this.uiDataInterface = new SoftReference<>(uiDataDelegate);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(ACTION) && uiDataInterface != null && uiDataInterface.get() != null)
        {
            String action = intent.getStringExtra("ACTION");
            if (action != null)
            {
                Class<? extends Annotation> klass = RefreshActionMapping.getInstance().get(action);
                if (klass != null)
                {
                    executeSomething(klass);
                }
            }
        }
    }

    private void executeSomething(Class<? extends Annotation> annotationKlass)
    {
        UIDataDelegate delegate = uiDataInterface.get();
        Class<?> klass = delegate.getClass();
        Method methods[] = klass.getDeclaredMethods();
        if (methods != null)
        {
            for (Method method : methods)
            {
                if (method.isAnnotationPresent(annotationKlass) && method.getParameterTypes().length == 0)
                {
                    try
                    {
                        Log.e(TAG, "RefreshRecever:" + klass.getSimpleName() + ":" + annotationKlass + ":" + method.getName());
                        if (!method.isAccessible())
                        {
                            method.setAccessible(true);
                        }
                        method.invoke(delegate, new Object[]{});
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
