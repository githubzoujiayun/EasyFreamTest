package com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;


/**
 * Created by xiaolei on 2017/10/10.
 */

public class DialogFragmentBuilder
{
    private Context context;
    private DialogFragmentInterface dialog;
    private DialogFragmentBuilder(FragmentManager fragmentManager)
    {
        dialog = new DialogFrag(fragmentManager);
    }
    private DialogFragmentBuilder(android.support.v4.app.FragmentManager fragmentManager)
    {
        dialog = new V4DialogFrag(fragmentManager);
    }

    public static DialogFragmentBuilder With(Activity activity)
    {
        return new DialogFragmentBuilder(activity.getFragmentManager()).setContext(activity);
    }
    
    public static DialogFragmentBuilder With(Fragment fragment)
    {
        return new DialogFragmentBuilder(fragment.getFragmentManager()).setContext(fragment.getActivity());
    }
    
    public static DialogFragmentBuilder With(FragmentActivity activity)
    {
        return new DialogFragmentBuilder(activity.getSupportFragmentManager()).setContext(activity);
    }
    
    public static DialogFragmentBuilder With(android.support.v4.app.Fragment fragment)
    {
        return new DialogFragmentBuilder(fragment.getFragmentManager()).setContext(fragment.getActivity());
    }
    
    /**
     * 加载布局文件
     * @param layout
     */
    public DialogFragmentConfig load(@LayoutRes int layout)
    {
        dialog.setLayout(layout);
        return new DialogFragmentConfig(dialog, context);
    }

    public Context getContext()
    {
        return context;
    }
    
    private DialogFragmentBuilder setContext(Context context)
    {
        this.context = context;
        return this;
    }
    
    
    public static class LayoutParams
    {
        public static int WRAP_CONTENT = WindowManager.LayoutParams.WRAP_CONTENT;
        public static int MATCH_PARENT = WindowManager.LayoutParams.MATCH_PARENT;
    }
    
}
