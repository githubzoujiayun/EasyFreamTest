package com.xiaolei.easyfreamwork.network;

import com.xiaolei.OkhttpCacheInterceptor.CacheInterceptor;
import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.Config.Globals;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.network.OKhttp.CookieManger;
import com.xiaolei.easyfreamwork.network.interceptor.SSessionInterceptor;
import com.xiaolei.easyfreamwork.utils.Log;
import com.xiaolei.easyfreamwork.utils.SDCardUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by xiaolei on 2017/5/10.
 */

public class BaseNetCore
{
    private static BaseNetCore instance;
    private Retrofit retrofit;
    private CookieManger cookiemanager;
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor loggingInterceptor;

    private BaseNetCore()
    {
        com.xiaolei.OkhttpCacheInterceptor.Config.Config.DEBUG = Config.DEBUG;
        cookiemanager = new CookieManger(ApplicationBreage.getInstance().getApplicationContext());
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
        {
            @Override
            public void log(String message)
            {
                Log.d("HttpRetrofit", message + "");
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new CacheInterceptor(ApplicationBreage.getInstance().getContext()))
                .addInterceptor(new SSessionInterceptor(Config.netHeadeMap))
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)//失败重连
                .connectTimeout(30, TimeUnit.SECONDS)//网络请求超时时间单位为秒
                .cookieJar(cookiemanager)
                //.cache(provideCache())
                .build();
        retrofit = new Retrofit.Builder()  //01:获取Retrofit对象
                .baseUrl(Globals.SERVER_ADDRESS) //02采用链式结构绑定Base url
                .addConverterFactory(ScalarsConverterFactory.create())//首先判断是否需要转换成字符串，简单类型
                .addConverterFactory(GsonConverterFactory.create())//再将转换成bean
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();//03执行操作
    }

    //设置缓存目录和缓存空间大小
    private static Cache provideCache()
    {
        Cache cache = null;
        try
        {
            File catchFile = new File(SDCardUtil.getSDPath(), Globals.FILE.HTTP_CATCH_DIR);
            if(!catchFile.exists())
            {
                catchFile.mkdirs();
            }
            cache = new Cache(catchFile,10 * 1024 * 1024); // 10 MB
        } catch (Exception e)
        {
            Log.e("cache", "Could not create Cache!");
        }
        return cache;
    }


    public static BaseNetCore getInstance()
    {
        if (instance == null)
        {
            instance = new BaseNetCore();
        }
        return instance;
    }

    public Retrofit getRetrofit()
    {
        return retrofit;
    }

    public CookieManger getCookiemanager()
    {
        return cookiemanager;
    }
}
