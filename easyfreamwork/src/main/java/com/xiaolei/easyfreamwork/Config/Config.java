package com.xiaolei.easyfreamwork.Config;

import android.support.annotation.LayoutRes;

import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.network.common.DefaultUnifiedFailEvent;
import com.xiaolei.easyfreamwork.network.common.IUnifiedFailEvent;
import com.xiaolei.easyfreamwork.network.regist.Regist;
import com.xiaolei.easyfreamwork.network.regist.RegisteTable;

import java.util.Map;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class Config
{
    // 是否是调试模式
    public static boolean DEBUG = true;
    public static Map<String, String> netHeadeMap;
    @LayoutRes
    public static int dialog_layout = R.layout.dialog_alert;
    @LayoutRes
    public static int dialog_loading_layout = R.layout.dialog_loading;
    public static Class<? extends IUnifiedFailEvent> UnifiedFailEventKlass = DefaultUnifiedFailEvent.class;
    
    /**
     * 设置是否是DEBUG模式
     *
     * @param debug
     */
    public void setDEBUG(boolean debug)
    {
        DEBUG = debug;
    }

    /**
     * 设置Retrofit的BaseUrl
     *
     * @param url
     */
    public void setBaseUrl(String url)
    {
        Globals.SERVER_ADDRESS = url;
    }

    /**
     * 设置必须的请求头
     *
     * @param header
     */
    public void setNetHeadeMap(Map<String, String> header)
    {
        netHeadeMap = header;
    }

    /**
     * 注册网络请求的过滤器
     *
     * @param klass
     * @param registClass
     * @param <T>
     */
    public <T> void regist(Class<T> klass, Class<? extends Regist<T>> registClass)
    {
        RegisteTable.getInstance().regist(klass, registClass);
    }
    
    public void setCustomAlertDialogLayout(@LayoutRes int alertdialog_layout)
    {
        dialog_layout = alertdialog_layout;
    }
    
    public void setUnifiedFailEventKlass(Class<? extends IUnifiedFailEvent> klass)
    {
        UnifiedFailEventKlass = klass;
    }
}
