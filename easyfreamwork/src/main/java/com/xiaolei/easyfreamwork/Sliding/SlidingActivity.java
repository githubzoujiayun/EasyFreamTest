package com.xiaolei.easyfreamwork.Sliding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.utils.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * 侧边滑动关闭Activity
 */
public abstract class SlidingActivity extends AppCompatActivity
{
    protected Handler handler = null;
    protected final String TAG = "BaseActivity";
    protected Toast toast = null;
    protected String klassName = this.getClass().getName();
    private AlertDialog.Builder builder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (enableSliding())
        {
            SlidingLayout rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
        handler = new Handler();
        builder = new AlertDialog.Builder(this);
        ApplicationBreage.getInstance().addActivity(this);
        Log.d(TAG, klassName + ":onCreate");
        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView(View view)
    {
        initObj();
        initData();
        super.setContentView(view);
        ButterKnife.bind(this);
        onSetContentView();
        initView();
        setListener();
        loadData();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params)
    {
        initObj();
        initData();
        super.setContentView(view, params);
        ButterKnife.bind(this);
        onSetContentView();
        initView();
        setListener();
        loadData();
    }

    @Override
    public void setContentView(int layoutResID)
    {
        initObj();
        initData();
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        onSetContentView();
        initView();
        setListener();
        loadData();
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
        Alert(obj, "确定", null);
    }

    public void Alert(Object obj, Action rightListener)
    {
        Alert(obj, null, null, "确定", rightListener);
    }

    public void Alert(Object obj, String rightText, Action rightListener)
    {
        Alert(obj, null, null, rightText, rightListener);
    }

    public void Alert(Object obj
            , String leftText
            , Action leftListener
            , String rightText
            , Action rightListener)
    {
        Alert("提示信息", obj, leftText, leftListener, rightText, rightListener);
    }

    public void Alert(String title
            , Object obj
            , String leftText
            , Action leftListener
            , String rightText
            , Action rightListener)
    {
        builder.setTitle(title);
        builder.setMessage(obj + "");
        builder.setCancelable(false);
        if (leftText != null)
        {
            builder.setNeutralButton(leftText, leftListener);
        }
        if (rightText != null)
        {
            builder.setNegativeButton(rightText, rightListener);
        }
        builder.show();
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

    public void onEvent(Message msg)
    {
    }

    /**
     * 发送一个消息
     *
     * @param message
     */
    public void post(Message message)
    {
        EventBus.getDefault().post(message);
    }

    protected boolean enableSliding()
    {
        return true;
    }
}