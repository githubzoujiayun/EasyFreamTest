package com.xiaolei.easyfreamwork.common;


import android.support.v4.util.ArrayMap;
import java.util.Collection;
import java.util.Set;

/**
 * 单例缓存
 * Created by xiaolei on 2017/11/14.
 */

public class InstanceObjCatch
{
    private static InstanceObjCatch instance;
    private InstanceObjCatch(){}
    
    public synchronized static InstanceObjCatch getInstance()
    {
        if(instance == null)
        {
            instance = new InstanceObjCatch();
        }
        return instance;
    }
    
    private ArrayMap<Class<?>, Object> arrayMap = new ArrayMap<>();
    
    public <T> T get(Class<T> klass)
    {
        Object object = arrayMap.get(klass);
        if(object == null)
        {
            try
            {
                object = klass.newInstance();
                arrayMap.put(klass,object);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return (T) object;
    }
    
    public <T> T remove(Class<T> klass)
    {
        return (T) arrayMap.remove(klass);
    }

    public void clear()
    {
        arrayMap.clear();
    }

    public Set<Class<?>> keySet()
    {
        return arrayMap.keySet();
    }

    public Collection<Object> values()
    {
        return arrayMap.values();
    }
}
