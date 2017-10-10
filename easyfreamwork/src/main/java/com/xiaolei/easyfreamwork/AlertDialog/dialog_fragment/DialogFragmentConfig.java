package com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.FloatRange;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by xiaolei on 2017/10/9.
 */

public class DialogFragmentConfig
{
    private Context context;
    private int paddingLeft = -1, paddingTop = -1, paddingRight = -1, paddingBottom = -1;
    private DialogFragmentInterface dialog;
    
    public DialogFragmentConfig(DialogFragmentInterface dialog,Context context)
    {
        this.dialog = dialog;
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
    public DialogFragmentConfig InitView(DialogInitCallBack callBack)
    {
        dialog.setInitCallBack(callBack);
        return this;
    }
    
    /**
     * 初始化事件
     *
     * @param callBack
     * @return
     */
    public DialogFragmentConfig InitEvent(DialogEventCallBack callBack)
    {
        dialog.setEventCallBack(callBack);
        return this;
    }
    
    /**
     * 设置宽度，绝对的值
     *
     * @param width
     * @return
     */
    public DialogFragmentConfig setWidth(int width)
    {
        dialog.setWidth(width);
        return this;
    }

    /**
     * 设置宽度，屏幕的百分比的
     *
     * @param widthPercent
     * @return
     */
    public DialogFragmentConfig setWidth(@FloatRange(from = 0.0, to = 1.0) double widthPercent)
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = (int) (wm.getDefaultDisplay().getWidth() * widthPercent);
        dialog.setWidth(width);
        return this;
    }
    
    /**
     * 设置高度
     *
     * @param heigh
     * @return
     */
    public DialogFragmentConfig setHeigh(int heigh)
    {
        dialog.setHeight(heigh);
        return this;
    }

    /**
     * 设置背景透明度
     *
     * @param dimAmount
     * @return
     */
    public DialogFragmentConfig setDimAmount(@FloatRange(from = 0.0, to = 1.0) float dimAmount)
    {
        dialog.setDimAmount(dimAmount);
        return this;
    }

    /**
     * 设置是否可以点击侧边取消
     *
     * @param cancelable
     * @return
     */
    public DialogFragmentConfig setCancelable(boolean cancelable)
    {
        dialog.setCancelable(cancelable);
        return this;
    }
    
    /**
     * 设置位置
     *
     * @return
     */
    
    public DialogFragmentConfig setGravity(int gravity)
    {
        dialog.setGravity(gravity);
        return this;
    }
    
    /**
     * 显示出来
     *
     * @return 返回一个dialog，方便dismiss
     */
    public DialogFragmentInterface show()
    {
        return dialog.show();
    }
    
    public interface DialogInitCallBack
    {
        public void onInit(Dialog alertDialog, View view);
    }

    public interface DialogEventCallBack
    {
        public void initEvent(Dialog alertDialog, View view);
    }
}