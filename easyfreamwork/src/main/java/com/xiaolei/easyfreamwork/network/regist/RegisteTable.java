package com.xiaolei.easyfreamwork.network.regist;

import android.support.v4.util.ArrayMap;

import com.xiaolei.easyfreamwork.Bean.ResBodyBean;
import com.xiaolei.easyfreamwork.common.callback.URIMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册表，关于
 * 网络请求得到的数据，与解析结果的执行器
 * Created by xiaolei on 2017/7/12.
 */

public class RegisteTable
{
    private static RegisteTable instance;
    private Map<Class, Class<? extends Regist>> map = new ArrayMap<>();
    private Map<Class<? extends Regist>, Object> registObj = new ArrayMap<>();

    private RegisteTable()
    {
    }

    public static synchronized RegisteTable getInstance()
    {
        if (instance == null)
        {
            instance = new RegisteTable();
        }
        return instance;
    }

    public <T> void regist(Class<T> klass, Class<? extends Regist<T>> registClass)
    {
        Object object = null;
        try
        {
            object = registClass.newInstance();
            map.put(klass, registClass);
            registObj.put(registClass, object);
        } catch (Exception e)
        {
            new Exception(registClass + "类需要至少一个Public无参构造函数").printStackTrace();
        }
    }

    public Class[] getRegistKeys()
    {
        List<Class> list = new ArrayList<>();
        for (Map.Entry<Class, Class<? extends Regist>> entry : map.entrySet())
        {
            list.add(entry.getKey());
        }
        return list.toArray(new Class[list.size()]);
    }

    public Class<? extends Regist> getRegistValue(Object object)
    {
        for (Map.Entry<Class, Class<? extends Regist>> entry : map.entrySet())
        {
            Class key = entry.getKey();
            if (key.isInstance(object))
            {
                return entry.getValue();
            }
        }
        return null;
    }

    public Regist getRegistObj(Class<? extends Regist> klass)
    {
        return (Regist) registObj.get(klass);
    }
}
