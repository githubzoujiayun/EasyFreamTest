package com.xiaolei.easyfreamwork.AlertDialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.xiaolei.easyfreamwork.Config.Config;
import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.utils.DensityUtil;

/**
 * 加载中的自定义的AlertDialog
 * Created by xiaolei on 2017/9/28.
 */

public class LoadingAlertDialog
{
    private DialogInitConfig builder;
    public LoadingAlertDialog(Activity activity)
    {
        this(activity, Config.dialog_loading_layout);
    }

    public LoadingAlertDialog(Activity activity, @LayoutRes int layout)
    {
        this((Context) activity, layout);
    }

    public LoadingAlertDialog(FragmentActivity activity)
    {
        this(activity, Config.dialog_loading_layout);
    }

    public LoadingAlertDialog(FragmentActivity activity, @LayoutRes int layout)
    {
        this((Context) activity, layout);
    }

    public LoadingAlertDialog(Fragment fragment)
    {
        this(fragment.getActivity(), Config.dialog_loading_layout);
    }

    public LoadingAlertDialog(Fragment fragment, @LayoutRes int layout)
    {
        this(fragment.getActivity(), layout);
    }

    public LoadingAlertDialog(android.support.v4.app.Fragment fragment)
    {
        this(fragment.getActivity(), Config.dialog_loading_layout);
    }

    public LoadingAlertDialog(android.support.v4.app.Fragment fragment, @LayoutRes int layout)
    {
        this(fragment.getActivity(), layout);
    }

    public LoadingAlertDialog(Context context)
    {
        this(context, Config.dialog_loading_layout);
    }

    public LoadingAlertDialog(Context context, @LayoutRes int layout)
    {
        builder = CustomDialogBuilder
                .With(context)
                .load(layout);
    }


    public AlertDialog Alert()
    {
        return Alert(null);
    }

    /**
     * 弹出加载框，并且提示文字
     */
    public AlertDialog Alert(final String title)
    {
        return builder.InitView(new DialogInitConfig.DialogInitCallBack()
        {
            @Override
            public void onInit(AlertDialog alertDialog, View view)
            {
                TextView textView = (TextView) view.findViewById(R.id.dialog_message);
                if (textView != null)
                {
                    if (title == null)
                    {
                        textView.setVisibility(View.GONE);
                    } else
                    {
                        textView.setText(title);
                    }
                }
            }
        })
        .setWidth(DensityUtil.dip2px(builder.getContext(), 110))
        .setHeigh(DensityUtil.dip2px(builder.getContext(), 110))
        .setCancelable(false)
        .show();
    }
}
