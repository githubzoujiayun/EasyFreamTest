package com.xiaolei.easyfreamwork.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.eventbus.Message;
import com.xiaolei.easyfreamwork.utils.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * Created by xiaolei on 2017/3/7.
 */

public abstract class BaseV4Fragment extends Fragment
{
    protected Handler handler = null;
    protected final String TAG = "BaseV4Fragment";
    protected Toast toast = null;
    private View contentView = null;
    private AlertDialog.Builder builder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.e(TAG, this.getClass().getName() + ":onCreate");
        super.onCreate(savedInstanceState);
        builder = new AlertDialog.Builder(getActivity());
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.e(TAG, this.getClass().getName() + ":onCreateView");
        if (contentView == null)
        {
            contentView = inflater.inflate(contentViewId(), null);
        }
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, this.getClass().getName() + ":onActivityCreated");
        handler = new Handler();
        onSetContentView();
        initObj();
        initData();
        ButterKnife.bind(this, getView());
        initView();
        setListener();
        loadData();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        if (!hidden)
        {
            onSetContentView();
        }
        super.onHiddenChanged(hidden);
    }

    protected abstract void onSetContentView();

    @Override
    public void onDestroyView()
    {
        Log.d(TAG, this.getClass().getName() + ":onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy()
    {
        Log.d(TAG, this.getClass().getName() + ":onDestroy");
        handler.removeCallbacksAndMessages(null);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public abstract @LayoutRes int contentViewId();

    public abstract void initObj();

    public abstract void initData();

    public abstract void initView();

    public abstract void setListener();

    public abstract void loadData();

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
        Intent intent = new Intent(getActivity(), klass);
        startActivity(intent);
    }

    public void Toast(String msg)
    {
        if (toast == null)
        {
            toast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    /**
     * 这个的存在，是为了,内置一个刷新机制
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ReloadData(Message message)
    {
        if(Message.TYPE.FRESH.equals(message.Type))
        {
            Log.e(TAG,"ReloadData");
            loadData();
        }
    }
}
