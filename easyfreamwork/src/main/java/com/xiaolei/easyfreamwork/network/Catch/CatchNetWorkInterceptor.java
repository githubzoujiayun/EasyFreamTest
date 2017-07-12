package com.xiaolei.easyfreamwork.network.Catch;

import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.utils.Log;
import com.xiaolei.easyfreamwork.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设置连接器  设置缓存
 * Created by xiaolei on 2017/5/12.
 */

public class CatchNetWorkInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (NetWorkUtil.isNetworkAvailable(ApplicationBreage.getInstance().getContext()))
        {
            int maxAge = 0 * 60;
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
            Log.e("HttpRetrofit","在线");
        } else
        {
            // 无网络时，设置超时为1周
            int maxStale = 60 * 60 * 24 * 7;
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
            Log.e("HttpRetrofit","离线");
        }
        return response;
    }
}
