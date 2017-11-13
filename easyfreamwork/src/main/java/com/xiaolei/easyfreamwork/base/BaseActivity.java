package com.xiaolei.easyfreamwork.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.AlertDialog.CustomAlertDialog;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.utils.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.ButterKnife;

/**
 * Created by xiaolei on 2017/3/1.
 */

public abstract class BaseActivity extends Activity
{
    protected Handler handler = null;
    protected final String TAG = "BaseActivity";
    protected Toast toast = null;
    protected String klassName = this.getClass().getName();
    private CustomAlertDialog alertDialog;
    private boolean hasResume = false;
    private boolean hasPostCreate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        alertDialog = new CustomAlertDialog(this, Config.dialog_layout);
        ApplicationBreage.getInstance().addActivity(this);
        Log.d(TAG, klassName + ":onCreate");
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        if (!hasPostCreate)
        {
            initView();
            setListener();
            hasPostCreate = true;
        }
    }

    @Override
    public void setContentView(View view)
    {
        initObj();
        initData();
        super.setContentView(view);
        ButterKnife.bind(this);
        onSetContentView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params)
    {
        initObj();
        initData();
        super.setContentView(view, params);
        ButterKnife.bind(this);
        onSetContentView();
    }

    @Override
    public void setContentView(int layoutResID)
    {
        initObj();
        initData();
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        onSetContentView();
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (!hasResume)
        {
            loadData();
            hasResume = true;
        }
    }

    protected abstract void onSetContentView();

    public abstract void initObj();

    public abstract void initData();

    public abstract void initView();

    public abstract void setListener();

    public abstract void loadData();

    @Override
    protected void onDestroy()
    {
        Log.d(TAG, klassName + ":onDestroy");
        handler.removeCallbacksAndMessages(null);
        ApplicationBreage.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);

        super.onDestroy();
    }

    public void Alert(Object obj)
    {
        alertDialog.Alert(obj);
    }

    public void Alert(Object obj, Action rightListener)
    {
        alertDialog.Alert(obj, rightListener);
    }

    public void Alert(Object obj, String rightText, Action rightListener)
    {
        alertDialog.Alert(obj, rightText, rightListener);
    }

    public void Alert(Object obj, String leftText, Action leftListener, String rightText, Action rightListener)
    {
        alertDialog.Alert(obj, leftText, leftListener, rightText, rightListener);
    }

    public void Alert(String title, Object obj, String leftText, Action leftListener, String rightText, Action rightListener)
    {
        alertDialog.Alert(title, obj, leftText, leftListener, rightText, rightListener);
    }

    public void startActivity(Class<? extends Activity> klass)
    {
        Intent intent = new Intent(this, klass);
        startActivity(intent);
    }

    public void Toast(String msg)
    {
        if (toast == null)
        {
            toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    /**
     * 这个的存在，是为了,内置一个刷新机制
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPrivateEvent(Message message)
    {
        onEvent(message);
    }

    public void onEvent(@NonNull Message msg)
    {
    }

    /**
     * 发送一个或者多个消息
     * @param messages
     */
    public void post(@NonNull Message... messages)
    {
        if (messages != null)
        {
            for (int a = 0; a < messages.length; a++)
            {
                Message message = messages[a];
                EventBus.getDefault().post(message);
            }
        }
    }

    /**
     * 获取自定义的dialog，怕有别的没想到的需求
     *
     * @return
     */
    public CustomAlertDialog getAlertDialog()
    {
        return alertDialog;
    }
}
