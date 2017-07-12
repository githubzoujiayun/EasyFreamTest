package com.xiaolei.easyfreamwork.network;


import com.xiaolei.easyfreamwork.application.ApplicationBreage;

/**
 * Created by xiaolei on 2017/3/1.
 */

public class BaseRetrofit
{
    public static <T> T create(Class<T> klass)
    {
        return ApplicationBreage.getInstance().getRetrofit().create(klass);
    }

    /**
     * COOKIE的操作类
     */
    public enum COOKIE
    {
        INSTANCE;
        /**
         * 清空cookie
         */
        public void clearAll()
        {
            ApplicationBreage.getInstance().getCookieStore().removeAll();
        }
    }
}
