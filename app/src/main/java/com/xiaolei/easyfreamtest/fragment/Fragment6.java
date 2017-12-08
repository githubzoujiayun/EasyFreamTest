package com.xiaolei.easyfreamtest.fragment;

import android.widget.TextView;

import com.xiaolei.easyfreamtest.R;
import com.xiaolei.easyfreamwork.base.BaseV4Fragment;


/**
 * Created by xiaolei on 2017/9/12.
 */

public class Fragment6 extends BaseV4Fragment
{
    TextView textview;
    @Override
    public int contentViewId()
    {
        return R.layout.fragment;
    }

    @Override
    public void initObj()
    {

    }

    @Override
    public void initData()
    {

    }

    @Override
    public void initView()
    {
        textview = getView().findViewById(R.id.textview);
    }

    @Override
    public void setListener()
    {

    }

    @Override
    public void loadData()
    {
        textview.setText(getClass().getSimpleName());
    }

    @Override
    protected void onSetContentView()
    {

    }
}
