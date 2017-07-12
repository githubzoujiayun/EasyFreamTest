package com.xiaolei.easyfreamwork.Config;

import java.util.Map;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class Config
{
    // 是否是调试模式
    public static boolean DEBUG = true;
    public static Map<String,String> netHeadeMap;

    /**
     * 设置是否是DEBUG模式
     * @param debug
     */
    public void setDEBUG(boolean debug)
    {
        DEBUG = debug;
    }
    /**
     * 设置Retrofit的BaseUrl
     * @param url
     */
    public void setBaseUrl(String url)
    {
        Globals.SERVER_ADDRESS = url;
    }
    
    /**
     * 设置必须的请求头
     * @param header
     */
    public void setNetHeadeMap(Map<String,String> header)
    {
        netHeadeMap = header;
    }
}
