package com.xiaolei.easyfreamtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xiaolei.easyfreamtest.NetPkg.BaseRetrofit;
import com.xiaolei.easyfreamwork.AlertDialog.LoadingAlertDialog;
import com.xiaolei.easyfreamwork.base.BaseActivity;
import com.xiaolei.exretrofitcallback.network.common.SCallBack;

import retrofit2.Call;

public class MainActivity extends BaseActivity
{
    LoadingAlertDialog loading;
    private Net baidu;
    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading = new LoadingAlertDialog(this);
    }

    @Override
    protected void onSetContentView()
    {

    }

    @Override
    public void initObj()
    {
        baidu = BaseRetrofit.create(Net.class);
    }

    @Override
    public void initData()
    {
        
    }

    @Override
    public void initView()
    {
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
    }

    @Override
    public void setListener()
    {

    }

    @Override
    public void loadData()
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onclick(v);
            }
        });
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onclick(v);
            }
        });
    }

    public void onclick(View view)
    {
        Call<DTBean> call = baidu.getIndex("苏州市");
        call.enqueue(new SCallBack<DTBean>(this)
        {
            @Override
            public void onSuccess(DTBean result) throws Exception
            {
                Alert("好了" + result.getLon());
            }

            @Override
            public void onCache(DTBean result) throws Exception
            {
                Alert("来自缓存：" + result.getLon());
            }
        });
    }
}
