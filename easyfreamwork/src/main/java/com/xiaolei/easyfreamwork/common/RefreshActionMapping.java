package com.xiaolei.easyfreamwork.common;

import com.xiaolei.easyfreamwork.Config.Globals;
import com.xiaolei.easyfreamwork.annotations.OnLoginAction;
import com.xiaolei.easyfreamwork.annotations.OnRefreshAction;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/**
 * ACTION 和 Annotation的映射
 * Created by xiaolei on 2017/5/3.
 */

public class RefreshActionMapping extends HashMap<String,Class<? extends Annotation>>
{
    private RefreshActionMapping(){}
    private static RefreshActionMapping mapping;
    public static RefreshActionMapping getInstance()
    {
        if(mapping == null)
        {
            mapping = new RefreshActionMapping();
        }
        return mapping;
    }
    
    
    static {
        getInstance().put(Globals.Action.REFRESH.LOGIN, OnLoginAction.class);
        getInstance().put(Globals.Action.REFRESH.REFRESH, OnRefreshAction.class);
    }
    
}
