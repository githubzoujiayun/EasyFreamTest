package com.xiaolei.easyfreamtest;

import android.os.Bundle;
import android.util.Log;

import com.xiaolei.easyfreamwork.base.BaseActivity;
import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.network.BaseRetrofit;
import com.xiaolei.easyfreamwork.network.common.SCallBack;

import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity
{
    Baidu baidu;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSetContentView()
    {

    }

    @Override
    public void initObj()
    {
        baidu = BaseRetrofit.create(Baidu.class);
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

    }

    @OnClick(R.id.button)
    public void onclick()
    {
        startActivity(MainActivity2.class);
    }
}
