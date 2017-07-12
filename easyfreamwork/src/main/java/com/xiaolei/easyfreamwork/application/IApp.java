package com.xiaolei.easyfreamwork.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.net.CookieStore;

import retrofit2.Retrofit;

/**
 * Created by xiaolei on 2017/7/9.
 */

public interface IApp
{
    void addActivity(Activity activity);
    void removeActivity(Activity activity);
    void removeAllActivity();
    Application getApplication();
    Retrofit getRetrofit();
    CookieStore getCookieStore();
    Context getApplicationContext();
    Context getContext();
    public boolean hasNetworkError = false;
}
