package com.xiaolei.easyfreamwork.AlertDialog;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.xiaolei.easyfreamwork.R;
import com.xiaolei.easyfreamwork.application.ApplicationBreage;
import com.xiaolei.easyfreamwork.utils.Log;

/**
 * Created by xiaolei on 2017/10/9.
 */

public class CustomDialogBuilder
{
    private boolean isBackNet = false;
    private AlertDialog.Builder builder;
    private Context context;
    private CustomDialogBuilder(Context context)
    {
        this.context = context;
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
            isBackNet = true;
        }
    }

    public static CustomDialogBuilder With(Activity activity)
    {
        return new CustomDialogBuilder(activity);
    }

    public static CustomDialogBuilder With(FragmentActivity activity)
    {
        return new CustomDialogBuilder(activity);
    }

    public static CustomDialogBuilder With(Fragment fragment)
    {
        return new CustomDialogBuilder(fragment.getActivity());
    }

    public static CustomDialogBuilder With(android.support.v4.app.Fragment fragment)
    {
        return new CustomDialogBuilder(fragment.getActivity());
    }

    public static CustomDialogBuilder With(Context context)
    {
        return new CustomDialogBuilder(context);
    }

    /**
     * 加载布局文件
     *
     * @param layout
     */
    public DialogInitConfig load(@LayoutRes int layout)
    {
        return new DialogInitConfig(layout,builder,isBackNet,context);
    }

    public Context getContext()
    {
        return context;
    }
}
