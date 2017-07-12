package com.xiaolei.easyfreamwork.network.Catch;

import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.utils.NetWorkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存
 * Created by xiaolei on 2017/5/12.
 */

public class CatchInterceptor implements Interceptor
{
//    @Override
//    public Response intercept(Chain chain) throws IOException
//    {
//        Request request = chain.request();
//        if (!NetWorkUtil.isNetworkAvailable(TJSApplication.getInstance()))//无网络状态，读取缓存
//        {
//            request = request.newBuilder()
//                    .cacheControl(CacheControl.FORCE_CACHE)
//                    .build();
//        }
//        return chain.proceed(request);
//    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        boolean netAvailable = NetWorkUtil.isNetworkAvailable(ApplicationBreage.getInstance().getContext());

        if (netAvailable)
        {
            request = request.newBuilder()
                    //网络可用 强制从网络获取数据
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
        } else
        {
            request = request.newBuilder()
                    //网络不可用 从缓存获取
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response response = chain.proceed(request);
        if (netAvailable)
        {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    // 有网络时 设置缓存超时时间1个小时
                    .header("Cache-Control", "public, max-age=" + 60 * 60)
                    .build();
        } else
        {
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    // 无网络时，设置超时为1周
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 7 * 24 * 60 * 60)
                    .build();
        }
        return response;
    }


}
