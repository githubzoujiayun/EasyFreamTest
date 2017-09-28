package com.xiaolei.easyfreamwork.alert_dialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.common.listeners.Action;
import com.xiaolei.easyfreamwork.network.common.SICallBack;
import com.xiaolei.easyfreamwork.utils.Log;

import java.lang.ref.SoftReference;

/**
 * 自定义的AlertDialog
 * Created by xiaolei on 2017/9/28.
 */

public class CustomAlertDialog
{
    private AlertDialog.Builder builder;
    @LayoutRes
    private int dialog_layout = -1;

    public CustomAlertDialog(Activity activity)
    {
        this(activity, -1);
    }

    public CustomAlertDialog(Activity activity, @LayoutRes int layout)
    {
        builder = new AlertDialog.Builder(activity);
        dialog_layout = layout;
        setListener(builder);
    }

    public CustomAlertDialog(FragmentActivity activity)
    {
        this(activity, -1);
    }

    public CustomAlertDialog(FragmentActivity activity, @LayoutRes int layout)
    {
        builder = new AlertDialog.Builder(activity);
        dialog_layout = layout;
        setListener(builder);
    }

    public CustomAlertDialog(Fragment fragment)
    {
        this(fragment, -1);
    }

    public CustomAlertDialog(Fragment fragment, @LayoutRes int layout)
    {
        builder = new AlertDialog.Builder(fragment.getActivity());
        dialog_layout = layout;
        setListener(builder);
    }

    public CustomAlertDialog(android.support.v4.app.Fragment fragment)
    {
        this(fragment, -1);
    }

    public CustomAlertDialog(android.support.v4.app.Fragment fragment, @LayoutRes int layout)
    {
        builder = new AlertDialog.Builder(fragment.getActivity());
        dialog_layout = layout;
        setListener(builder);
    }

    public CustomAlertDialog(SICallBack callBack)
    {
        this(callBack, -1);
    }

    public CustomAlertDialog(SICallBack callBack, @LayoutRes int layout)
    {
        dialog_layout = layout;
        SoftReference<Context> context = callBack.context;
        if (context.get() != null)
        {
            Context context1 = context.get();
            if (Activity.class.isInstance(context1)
                    || FragmentActivity.class.isInstance(context1))
            {
                if ((Activity.class.isInstance(context1) && !((Activity) context1).isFinishing())
                        || (FragmentActivity.class.isInstance(context1) && !((FragmentActivity) context1).isFinishing()))
                {
                    builder = new AlertDialog.Builder(context1);
                } else
                {
                    Log.e("HttpRetrofit", "不是activity，或者activity已关闭");
                }
            } else
            {
                builder = new AlertDialog.Builder(ApplicationBreage.getInstance().getContext(),
                        R.style.Base_Theme_AppCompat_Light_Dialog);
            }
        }
    }


    public CustomAlertDialog(Context context)
    {
        this(context, -1);
    }
    
    public CustomAlertDialog(Context context, @LayoutRes int layout)
    {
        dialog_layout = layout;
        if (Activity.class.isInstance(context)
                || FragmentActivity.class.isInstance(context))
        {
            if ((Activity.class.isInstance(context) && !((Activity) context).isFinishing())
                    || (FragmentActivity.class.isInstance(context) && !((FragmentActivity) context).isFinishing()))
            {
                builder = new AlertDialog.Builder(context);
            } else
            {
                Log.e("HttpRetrofit", "不是activity，或者activity已关闭");
            }
        } else
        {
            builder = new AlertDialog.Builder(ApplicationBreage.getInstance().getContext(),
                    R.style.Base_Theme_AppCompat_Light_Dialog);
        }
    }

    private void setListener(final AlertDialog.Builder builder)
    {
        builder.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialog)
            {
                builder.setTitle("提示信息");
                builder.setMessage("");
                builder.setNeutralButton("", null);
                builder.setNegativeButton("", null);
            }
        });
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

    public void Alert(Object obj, String leftText, Action leftListener, String rightText, Action rightListener)
    {
        Alert("提示信息", obj, leftText, leftListener, rightText, rightListener);
    }

    public void Alert(String title, Object obj, String leftText, final Action leftListener, String rightText, final Action rightListener)
    {
        if (builder == null)
        {
            return;
        }
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
        final AlertDialog alertDialog = builder.create();
        if (alertDialog == null)
        {
            return;
        }
        if (alertDialog.getWindow() != null)
        {
            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        }
        alertDialog.show();
        if (dialog_layout != -1)
        {
            try
            {
                View view = View.inflate(alertDialog.getContext(), dialog_layout, null);
                alertDialog.setContentView(view);
                TextView dialog_message = (TextView) view.findViewById(R.id.dialog_message);
                TextView dialog_title = (TextView) view.findViewById(R.id.dialog_title);
                TextView dialog_leftBtn = (TextView) view.findViewById(R.id.dialog_leftBtn);
                TextView dialog_rightBtn = (TextView) view.findViewById(R.id.dialog_rightBtn);

                if (dialog_title != null)
                {
                    dialog_title.setText("" + title + "");
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
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
