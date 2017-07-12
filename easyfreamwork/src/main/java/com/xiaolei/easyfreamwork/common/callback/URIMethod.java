package com.xiaolei.easyfreamwork.common.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;

import com.xiaolei.easyfreamwork.annotations.OnCallBack;
import com.xiaolei.easyfreamwork.network.common.SICallBack;

import java.lang.reflect.Method;

/**
 * Created by xiaolei on 2017/5/24.
 */

public class URIMethod
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

    private URIMethod()
    {
    }

    private static URIMethod uriMethod;

    public static URIMethod getInstance()
    {
        if (uriMethod == null)
        {
            uriMethod = new URIMethod();
        }
        return uriMethod;
    }
    
    // -----------------------切记切记，必须，这里说的是必须！！是两个参数，第一个是上下文第二个是callback------------
    // -----------------------我特么也不知道有什么卵用，先加上，预防万一，反正以后万一有用呢。-----------------------
    
    
    /**
     * 登录的callback
     *
     * @param context
     * @param callBack
     */
    @OnCallBack("/aaaa")
    public void haveToLogin(Context context, SICallBack callBack)
    {
        
    }
    
    
}
