package com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentConfig.DialogInitCallBack;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentConfig.DialogEventCallBack;

/**
 * Created by xiaolei on 2017/10/10.
 */

@SuppressLint("ValidFragment")
public class V4DialogFrag extends android.support.v4.app.DialogFragment implements DialogFragmentInterface
{
    private FragmentManager fragmentManager;
    private int gravity = Gravity.CENTER;
    private float dimAmount = 0.5f;
    private DialogInitCallBack initCallBack;
    private DialogEventCallBack eventCallBack;
    private int width = WindowManager.LayoutParams.WRAP_CONTENT, height = WindowManager.LayoutParams.WRAP_CONTENT;
    
    public V4DialogFrag(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }
    private View rootView;
    @LayoutRes
    private int layout = -1;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        if(rootView == null)
        {
            rootView = inflater.inflate(layout, container, false);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return rootView;
    }

    @Override
    public void onStart()
    {
        if(initCallBack != null)
        {
            initCallBack.onInit(getDialog(),rootView);
            initCallBack = null;
        }
        Window window = getDialog().getWindow();
        if(window != null)
        {
            View decorView = window.getDecorView();
            decorView.setBackgroundColor(Color.TRANSPARENT);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = gravity;
            layoutParams.dimAmount = dimAmount;
            layoutParams.width = width;
            layoutParams.height = height;
            window.setAttributes(layoutParams);
        }
        if(eventCallBack != null)
        {
            eventCallBack.initEvent(getDialog(),rootView);
            eventCallBack = null;
        }
        super.onStart();
    }

    @Override
    public void setLayout(@LayoutRes int layout)
    {
        this.layout = layout;
    }
    @Override
    public void setDimAmount(float dimAmount)
    {
        this.dimAmount = dimAmount;
    }
    @Override
    public void setInitCallBack(DialogInitCallBack initCallBack)
    {
        this.initCallBack = initCallBack;
    }
    @Override
    public void setEventCallBack(DialogEventCallBack eventCallBack)
    {
        this.eventCallBack = eventCallBack;
    }

    @Override
    public void setWidth(int width)
    {
        this.width = width;
    }
    
    @Override
    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public DialogFragmentInterface show()
    {
        show(fragmentManager,"V4DialogFrag");
        return this;
    }

    @Override
    public void setGravity(int gravity)
    {
        this.gravity = gravity;
    }
}
