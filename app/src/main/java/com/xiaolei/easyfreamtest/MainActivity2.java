package com.xiaolei.easyfreamtest;

import android.os.Bundle;

import com.xiaolei.easyfreamwork.Sliding.SlidingActivity;
import com.xiaolei.easyfreamwork.network.BaseRetrofit;
import com.xiaolei.easyfreamwork.network.common.SCallBack;
import com.xiaolei.easyfreamwork.utils.Log;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity2 extends SlidingActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    protected void onSetContentView()
    {

    }

    @Override
    public void initObj()
    {

    }

    @Override
    public void initData()
    {

    }

    @Override
    public void initView()
    {

    }

    @Override
    public void setListener()
    {

    }

    @Override
    public void loadData()
    {

        finish();
    }
}
