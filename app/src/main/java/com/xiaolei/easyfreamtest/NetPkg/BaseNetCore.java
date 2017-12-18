package com.xiaolei.easyfreamtest.NetPkg;

import com.xiaolei.easyfreamtest.BuildConfig;

import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.utils.Log;

import com.xiaolei.okhttputil.Cookie.CookieJar;
import com.xiaolei.okhttputil.OkHttpUtilConfig;
import com.xiaolei.okhttputil.interceptor.CacheInterceptor;
import com.xiaolei.okhttputil.interceptor.SSessionInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by xiaolei on 2017/5/10.
 */

public class BaseNetCore
{
    private static BaseNetCore instance;
    private Retrofit retrofit;
    private CookieJar cookiemanager;
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor loggingInterceptor;

    private BaseNetCore()
    {
        Map<String, String> head = new HashMap<>();
        head.put("from", "xiaolei");
        
        OkHttpUtilConfig.DEBUG = BuildConfig.DEBUG;
        cookiemanager = new CookieJar(ApplicationBreage.getInstance().getApplicationContext());
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
                .addInterceptor(new SSessionInterceptor(head))
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)//失败重连
                .connectTimeout(30, TimeUnit.SECONDS)//网络请求超时时间单位为秒
                .cookieJar(cookiemanager)
                //.cache(provideCache())
                .build();
        retrofit = new Retrofit.Builder()  //01:获取Retrofit对象
                .baseUrl("http://gc.ditu.aliyun.com/") //02采用链式结构绑定Base url
                .addConverterFactory(ScalarsConverterFactory.create())//首先判断是否需要转换成字符串，简单类型
                .addConverterFactory(GsonConverterFactory.create())//再将转换成bean
                .client(okHttpClient)
                .build();//03执行操作
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

    public CookieJar getCookiemanager()
    {
        return cookiemanager;
    }
}
