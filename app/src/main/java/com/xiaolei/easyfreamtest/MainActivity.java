package com.xiaolei.easyfreamtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.Config.Globals;
import com.xiaolei.easyfreamwork.base.BaseActivity;
import com.xiaolei.easyfreamwork.network.BaseRetrofit;
import com.xiaolei.easyfreamwork.network.common.SCallBack;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity
{
    @BindView(R.id.text)
    TextView text;
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
        Log.e("MainActivity", "onSetContentView");
    }

    @Override
    public void initObj()
    {
        Log.e("MainActivity", "initObj");
        baidu = BaseRetrofit.create(Baidu.class);
    }

    @Override
    public void initData()
    {
        Log.e("MainActivity", "initData");
    }

    @Override
    public void initView()
    {
        Log.e("MainActivity", "initView:" + text);
    }

    @Override
    public void setListener()
    {
        Log.e("MainActivity", "setListener");
    }

    public void OnRefreshAction()
    {
        Alert("OnRefreshAction");
    }
    
    public void OnLoginAction()
    {
        Alert("OnLoginAction");
    }

    
    @Override
    public void loadData()
    {
        Log.e("MainActivity", "loadData");
        Observable<String> call = baidu.getIndex();
        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SCallBack<String>(this)
                {
                    @Override
                    public void onSuccess(String result) throws Exception
                    {
                        Log.e("MainActivity", "加载完成了？");
                        Intent intent = new Intent(Globals.Action.RefreshRecever_Action);
                        intent.putExtra("ACTION", Globals.Action.REFRESH.REFRESH);
                        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent);

                        Intent intent2 = new Intent(Globals.Action.RefreshRecever_Action);
                        intent2.putExtra("ACTION", Globals.Action.REFRESH.LOGIN);
                        LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(intent2);
                    }
                });
    }

    public <T> T F(int id)
    {
        return (T) findViewById(id);
    }
}
