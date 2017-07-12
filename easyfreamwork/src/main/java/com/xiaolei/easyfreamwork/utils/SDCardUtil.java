package com.xiaolei.easyfreamwork.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by xiaolei on 2017/4/18.
 */

public class SDCardUtil
{
    public static File getSDPath()
    {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir;
    }
}
