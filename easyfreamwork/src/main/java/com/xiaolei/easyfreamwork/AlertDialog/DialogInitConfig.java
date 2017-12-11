package com.xiaolei.easyfreamwork.AlertDialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.FloatRange;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by xiaolei on 2017/10/9.
 */

public class DialogInitConfig
{
    private DialogInitCallBack initCallBack;
    private DialogEventCallBack eventCallBack;
    private int width = -1, heigh = -1;
    private float dimAmount = 0.3f;
    @LayoutRes
    private int layout = -1;
    private AlertDialog.Builder builder;
    private boolean isBackNet = false;
    private boolean cancelable = true;
    private Context context;
    private int gravity = Gravity.CENTER;
    private int paddingLeft = -1, paddingTop = -1, paddingRight = -1, paddingBottom = -1;

    public DialogInitConfig(@LayoutRes int layout, AlertDialog.Builder builder, boolean isBackNet, Context context)
    {
        this.layout = layout;
        this.builder = builder;
        this.isBackNet = isBackNet;
        this.context = context;
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public Context getContext()
    {
        return context;
    }

    /**
     * 初始化UI
     *
     * @param callBack
     * @return
     */
    public DialogInitConfig InitView(DialogInitCallBack callBack)
    {
        initCallBack = callBack;
        return this;
    }

    /**
     * 初始化事件
     *
     * @param callBack
     * @return
     */
    public DialogInitConfig InitEvent(DialogEventCallBack callBack)
    {
        eventCallBack = callBack;
        return this;
    }

    /**
     * 设置宽度，绝对的值
     *
     * @param width
     * @return
     */
    public DialogInitConfig setWidth(int width)
    {
        this.width = width;
        return this;
    }

    /**
     * 设置宽度，屏幕的百分比的
     *
     * @param widthPercent
     * @return
     */
    public DialogInitConfig setWidth(@FloatRange(from = 0.0, to = 1.0) double widthPercent)
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        this.width = (int) (wm.getDefaultDisplay().getWidth() * widthPercent);
        return this;
    }

    /**
     * 设置高度
     *
     * @param heigh
     * @return
     */
    public DialogInitConfig setHeigh(int heigh)
    {
        this.heigh = heigh;
        return this;
    }

    /**
     * 设置背景透明度
     *
     * @param dimAmount
     * @return
     */
    public DialogInitConfig setDimAmount(@FloatRange(from = 0.0, to = 1.0) float dimAmount)
    {
        this.dimAmount = dimAmount;
        return this;
    }

    /**
     * 设置是否可以点击侧边取消
     *
     * @param cancelable
     * @return
     */
    public DialogInitConfig setCancelable(boolean cancelable)
    {
        this.cancelable = cancelable;
        return this;
    }

    /**
     * 设置位置
     *
     * @return
     */
    public DialogInitConfig setGravity(int gravity)
    {
        this.gravity = gravity;
        return this;
    }


    /**
     * 显示出来
     *
     * @return 返回一个dialog，方便dismiss
     */
    public AlertDialog show()
    {
        if (builder == null)
        {
            return null;
        }
        builder.setTitle("");
        builder.setMessage("");
        builder.setCancelable(cancelable);

        AlertDialog alertDialog = builder.create();
        if (alertDialog == null)
        {
            return null;
        }
        Window window = alertDialog.getWindow();
        if (window != null)
        {
            WindowManager.LayoutParams params = window.getAttributes();
            // 去除四角黑色背景  
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (isBackNet)
            {
                window.setType(WindowManager.LayoutParams.TYPE_TOAST);
            }
            window.setAttributes(params);
        }
        alertDialog.show();
        try
        {
            View view = View.inflate(alertDialog.getContext(), layout, null);
            alertDialog.setContentView(view);
            if (initCallBack != null)
            {
                initCallBack.onInit(alertDialog, view);
            }
            if (eventCallBack != null)
            {
                eventCallBack.initEvent(alertDialog, view);
            }

            window = alertDialog.getWindow();
            if (window != null)
            {
                WindowManager.LayoutParams params = window.getAttributes();
                window.setGravity(gravity);
                DisplayMetrics dm = new DisplayMetrics();
                window.getWindowManager().getDefaultDisplay().getMetrics(dm);
                if (width != -1)
                {
                    params.width = width;
                } else
                {
                    setWidth(0.8);
                    params.width = width;
                }
                if (heigh != -1)
                {
                    params.height = heigh;
                }
                // 设置周围的暗色系数  
                params.dimAmount = dimAmount;
                window.setAttributes(params);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return alertDialog;
    }

    public interface DialogInitCallBack
    {
        public void onInit(AlertDialog alertDialog, View view);
    }

    public interface DialogEventCallBack
    {
        public void initEvent(AlertDialog alertDialog, View view);
    }
}