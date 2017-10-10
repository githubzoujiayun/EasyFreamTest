package com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment;

import android.support.annotation.LayoutRes;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentConfig.DialogInitCallBack;
import com.xiaolei.easyfreamwork.AlertDialog.dialog_fragment.DialogFragmentConfig.DialogEventCallBack;
/**
 * Created by xiaolei on 2017/10/10.
 */

public interface DialogFragmentInterface
{
    public void setLayout(@LayoutRes int layout);
    public DialogFragmentInterface show();
    public void setCancelable(boolean cancelable);
    public void setGravity(int gravity);
    public void setDimAmount(float dimAmount);
    public void setInitCallBack(DialogInitCallBack initCallBack);
    public void setEventCallBack(DialogEventCallBack eventCallBack);
    public void setWidth(int width);
    public void setHeight(int height);
}
