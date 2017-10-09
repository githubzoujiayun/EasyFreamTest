package com.xiaolei.easyfreamwork.AlertDialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.network.common.SICallBack;

/**
 * 提示框的自定义的AlertDialog
 * Created by xiaolei on 2017/9/28.
 */

public class CustomAlertDialog
{
    private DialogInitConfig builder;
    public CustomAlertDialog(Activity activity)
    {
        this(activity, Config.dialog_layout);
    }

    public CustomAlertDialog(Activity activity, @LayoutRes int layout)
    {
        this((Context) activity,layout);
    }

    public CustomAlertDialog(FragmentActivity activity)
    {
        this(activity, Config.dialog_layout);
    }

    public CustomAlertDialog(FragmentActivity activity, @LayoutRes int layout)
    {
        this((Context) activity,layout);
    }

    public CustomAlertDialog(Fragment fragment)
    {
        this(fragment, Config.dialog_layout);
    }

    public CustomAlertDialog(Fragment fragment, @LayoutRes int layout)
    {
        this(fragment.getActivity(),layout);
    }

    public CustomAlertDialog(android.support.v4.app.Fragment fragment)
    {
        this(fragment, Config.dialog_layout);
    }

    public CustomAlertDialog(android.support.v4.app.Fragment fragment, @LayoutRes int layout)
    {
        this(fragment.getActivity(),layout);
    }

    public CustomAlertDialog(SICallBack callBack)
    {
        this(callBack, Config.dialog_layout);
    }

    public CustomAlertDialog(SICallBack callBack, @LayoutRes int layout)
    {
        this((Context) callBack.context.get(),layout);
    }
    
    public CustomAlertDialog(Context context)
    {
        this(context, Config.dialog_layout);
    }

    public CustomAlertDialog(Context context, @LayoutRes int layout)
    {
        builder = CustomDialogBuilder.With(context).load(layout);
    }
    

    /**
     * 只弹出提示信息
     *
     * @param obj
     */
    public void Alert(Object obj)
    {
        Alert(obj, "确定", null);
    }

    /**
     * 弹出内容，自定义确定的点击事件
     *
     * @param obj           弹出的内容
     * @param rightListener 确定按钮的点击事件
     */
    public void Alert(Object obj, Action rightListener)
    {
        Alert(obj, null, null, "确定", rightListener);
    }

    /**
     * 只弹出内容，并且自定义按钮文字和点击事件
     *
     * @param obj           弹出的内容
     * @param rightText     按钮的问题
     * @param rightListener 相应的点击事件
     */
    public void Alert(Object obj, String rightText, Action rightListener)
    {
        Alert(obj, null, null, rightText, rightListener);
    }

    /**
     * 弹出内容，自定义两边文字，和两边的点击事件
     *
     * @param obj           弹出的内容
     * @param leftText      左边文字
     * @param leftListener  左边的点击事件
     * @param rightText     右边的文字
     * @param rightListener 右边的点击事件
     */
    public void Alert(Object obj, String leftText, Action leftListener, String rightText, Action rightListener)
    {
        Alert(null, obj, leftText, leftListener, rightText, rightListener);
    }

    /**
     * 弹出标题，内容，两边文字以及相应的点击事件
     *
     * @param title         标题
     * @param obj           弹出的内容
     * @param leftText      左边的文字
     * @param leftListener  左边的点击事件
     * @param rightText     右边的文字
     * @param rightListener 右边的点击事件
     */
    public void Alert(final String title, final Object obj, final String leftText, final Action leftListener, final String rightText, final Action rightListener)
    {
        builder.setWidth(0.84)
                .setCancelable(false)
                .setDimAmount(0.45f)
                .InitView(new DialogInitConfig.DialogInitCallBack()
                {
                    @Override
                    public void onInit(final AlertDialog alertDialog, View view)
                    {
                        TextView dialog_message = (TextView) view.findViewById(R.id.dialog_message);
                        TextView dialog_title = (TextView) view.findViewById(R.id.dialog_title);
                        TextView dialog_leftBtn = (TextView) view.findViewById(R.id.dialog_leftBtn);
                        TextView dialog_rightBtn = (TextView) view.findViewById(R.id.dialog_rightBtn);

                        Window window = alertDialog.getWindow();
                        if (window != null)
                        {
                            WindowManager.LayoutParams params = window.getAttributes();
                            window.setGravity(Gravity.CENTER);
                            DisplayMetrics dm = new DisplayMetrics();
                            window.getWindowManager().getDefaultDisplay().getMetrics(dm);
                            params.width = (int) (dm.widthPixels * 0.84);
                            window.setAttributes(params);
                        }

                        if (dialog_title != null)
                        {
                            if (title == null)
                            {
                                dialog_title.setVisibility(View.GONE);
                            } else
                            {
                                dialog_title.setVisibility(View.VISIBLE);
                                dialog_title.setText("" + title + "");
                            }
                        }
                        if (dialog_message != null)
                        {
                            dialog_message.setText("" + obj + "");
                        }
                        
                        if (dialog_leftBtn != null)
                        {
                            if (leftText == null)
                            {
                                dialog_leftBtn.setVisibility(View.GONE);
                            } else
                            {
                                dialog_leftBtn.setVisibility(View.VISIBLE);
                                dialog_leftBtn.setText("" + leftText + "");
                                dialog_leftBtn.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        try
                                        {
                                            if (leftListener != null)
                                                leftListener.action();
                                        } finally
                                        {
                                            alertDialog.dismiss();
                                        }
                                    }
                                });
                            }
                        }
                        if (dialog_rightBtn != null)
                        {
                            if (rightText == null)
                            {
                                dialog_rightBtn.setVisibility(View.GONE);
                            } else
                            {
                                dialog_rightBtn.setVisibility(View.VISIBLE);
                                dialog_rightBtn.setText("" + rightText + "");
                                dialog_rightBtn.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View v)
                                    {
                                        try
                                        {
                                            if (rightListener != null)
                                                rightListener.action();
                                        } finally
                                        {
                                            alertDialog.dismiss();
                                        }
                                    }
                                });
                            }
                        }
                        if (dialog_leftBtn == null
                                && dialog_rightBtn == null)
                        {
                            alertDialog.setCancelable(true);
                            alertDialog.setCanceledOnTouchOutside(true);
                        }
                        if (dialog_leftBtn == null
                                && (dialog_rightBtn != null
                                && dialog_rightBtn.getVisibility() == View.GONE))
                        {
                            alertDialog.setCancelable(true);
                            alertDialog.setCanceledOnTouchOutside(true);
                        }
                        if (dialog_rightBtn == null
                                && (dialog_leftBtn != null
                                && dialog_leftBtn.getVisibility() == View.GONE))
                        {
                            alertDialog.setCancelable(true);
                            alertDialog.setCanceledOnTouchOutside(true);
                        }
                        if (dialog_leftBtn != null
                                && dialog_leftBtn.getVisibility() == View.GONE
                                && dialog_rightBtn != null
                                && dialog_rightBtn.getVisibility() == View.GONE)
                        {
                            alertDialog.setCancelable(true);
                            alertDialog.setCanceledOnTouchOutside(true);
                        }

                    }
                }).show();
    }
}
