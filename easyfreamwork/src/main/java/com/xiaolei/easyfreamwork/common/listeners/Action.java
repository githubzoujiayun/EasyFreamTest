package com.xiaolei.easyfreamwork.common.listeners;

import android.content.DialogInterface;

/**
 * Created by xiaolei on 2017/5/2.
 */

public abstract class Action implements DialogInterface.OnClickListener
{
    public abstract void action();
    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        action();
    }
}
