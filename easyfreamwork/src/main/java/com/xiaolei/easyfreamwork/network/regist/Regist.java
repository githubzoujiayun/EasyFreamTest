package com.xiaolei.easyfreamwork.network.regist;

import android.support.v4.util.ArrayMap;

import com.xiaolei.easyfreamwork.annotations.OnCallBack;

import java.lang.reflect.Method;

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
    
    public abstract String filter(T t);
    
    
}
