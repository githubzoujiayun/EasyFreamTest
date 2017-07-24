package com.xiaolei.easyfreamtest;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.base.BaseActivity;
import com.xiaolei.easyfreamwork.network.BaseRetrofit;
import com.xiaolei.easyfreamwork.network.common.SCallBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    
    @Override
    public void loadData()
    {
        android.os.Message message = new android.os.Message();
        
        
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
                        android.os.Message message1 = new android.os.Message();
                        message1.what = 1;
                        message1.obj = "是啊";
                        EventBus.getDefault().post(message1);
                    }
                });
    }

    
    @Override
    public void onEvent(android.os.Message message)
    {
        Toast(message.obj+"");
    }

    public <T> T F(int id)
    {
        return (T) findViewById(id);
    }
}
