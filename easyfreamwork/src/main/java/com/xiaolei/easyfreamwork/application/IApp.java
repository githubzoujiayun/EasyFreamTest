package com.xiaolei.easyfreamwork.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


/**
 * Created by xiaolei on 2017/7/9.
 */

public interface IApp
{
    Application getApplication();
    Context getApplicationContext();
    Context getContext();
}
