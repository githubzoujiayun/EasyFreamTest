package com.xiaolei.easyfreamtest;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.AlertDialog.CustomDialogBuilder;
import com.xiaolei.easyfreamwork.AlertDialog.LoadingAlertDialog;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentBuilder;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentConfig;
import com.xiaolei.easyfreamwork.base.BaseActivity;
import com.xiaolei.easyfreamwork.network.BaseRetrofit;
import com.xiaolei.easyfreamwork.network.common.SCallBack;
import com.xiaolei.easyfreamwork.utils.DensityUtil;

import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity
{
    LoadingAlertDialog loading;
    private Baidu baidu;
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
        baidu = BaseRetrofit.create(Baidu.class);
    }

    @Override
    public void initData()
    {
        Intent intent = new Intent(this,mService.class);
        startService(intent);
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

    @OnClick({R.id.button,R.id.button2})
    public void onclick(View view)
    {
        baidu.getIndex()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SCallBack<String>(this)
                {
                    @Override
                    public void onSuccess(String result) throws Exception
                    {
                        Alert("好了");
                    }
                });
        // switch (view.getId())
        // {
        //     case R.id.button:
        //         loading.Alert("哈哈哈");
        //         break;
        //     case R.id.button2:
        //         CustomDialogBuilder.With(this)//上下文
        //                 .load(R.layout.dialog_loading)//自定义的布局文件
        //                 .setDimAmount(0.5f)//设置背景透明度
        //                 .setCancelable(true)//是否点击区域外隐藏
        //                 .setGravity(Gravity.CENTER)
        //                 .setHeigh(DensityUtil.dip2px(this,100))//设置高度
        //                 .setWidth(DensityUtil.dip2px(this,100))//设置宽度
        //                 .InitView(null)//初始化View
        //                 .InitEvent(null)//初始化事件
        //                 .show();//显示
        //         break;
        // }
        startActivity(MainActivity2.class);
    }
}
