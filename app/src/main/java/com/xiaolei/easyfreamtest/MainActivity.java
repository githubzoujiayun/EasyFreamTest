package com.xiaolei.easyfreamtest;

import android.os.Bundle;
import com.xiaolei.easyfreamwork.Sliding.SlidingActivity;

import butterknife.OnClick;

public class MainActivity extends SlidingActivity
{
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
    
    @Override
    protected boolean enableSliding()
    {
        return false;
    }
}
