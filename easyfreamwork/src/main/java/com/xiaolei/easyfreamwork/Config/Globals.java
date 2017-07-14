package com.xiaolei.easyfreamwork.Config;

import com.xiaolei.easyfreamwork.BuildConfig;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;

/**
 * Created by xiaolei on 2017/7/9.
 */

public class Globals
{
    public static String SERVER_ADDRESS = "http://www.baidu.com/";

    public static class FILE
    {
        public static String HTTP_CATCH_DIR = "Android/TMP/" + (BuildConfig.DEBUG ? "" : ".") + "COM."+ ApplicationBreage.getInstance().getContext().getPackageName()+".HTTP.CATCH";
    }
}
