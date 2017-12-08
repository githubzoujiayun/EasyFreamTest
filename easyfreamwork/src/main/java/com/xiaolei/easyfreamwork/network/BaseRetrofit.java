package com.xiaolei.easyfreamwork.network;


/**
 * Created by xiaolei on 2017/3/1.
 */

public class BaseRetrofit
{
    public static <T> T create(Class<T> klass)
    {
        return BaseNetCore.getInstance().getRetrofit().create(klass);
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
            BaseNetCore.getInstance().getCookiemanager().getCookieStore().removeAll();
        }
    }
}
