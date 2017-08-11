package com.xiaolei.easyfreamtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.Sliding.SlidingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SlidingActivity
{
    @BindView(R.id.text)
    TextView text;
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
    
    @OnClick(R.id.text)
    public void setOnClick()
    {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected boolean enableSliding()
    {
        return false;
    }
}
