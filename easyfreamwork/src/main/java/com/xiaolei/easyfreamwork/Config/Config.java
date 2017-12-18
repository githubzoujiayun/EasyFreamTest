package com.xiaolei.easyfreamwork.Config;

import android.support.annotation.LayoutRes;

import com.xiaolei.easyfreamwork.R;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class Config
{
    // 是否是调试模式
    public static boolean DEBUG = true;
    @LayoutRes
    public static int dialog_layout = R.layout.dialog_alert;
    @LayoutRes
    public static int dialog_loading_layout = R.layout.dialog_loading;
        
    /**
     * 设置是否是DEBUG模式
     *
     * @param debug
     */
    public void setDEBUG(boolean debug)
    {
        DEBUG = debug;
    }
}
