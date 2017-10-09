package com.xiaolei.easyfreamtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xiaolei.easyfreamwork.AlertDialog.CustomAlertDialog;

/**
 * Created by xiaolei on 2017/10/9.
 */

class mReceiver extends BroadcastReceiver
{
    CustomAlertDialog customAlertDialog;
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(customAlertDialog == null)
        {
            customAlertDialog = new CustomAlertDialog(context);
        }
        customAlertDialog.Alert("123");
    }
}