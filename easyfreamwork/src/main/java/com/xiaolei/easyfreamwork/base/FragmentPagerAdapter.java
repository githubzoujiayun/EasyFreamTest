package com.xiaolei.easyfreamwork.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.LinkedList;
import java.util.List;

/**
 * 统一的 FragmentPagerAdapter
 * Created by admin on 2017/7/19.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter
{
    private List<BaseV4Fragment> fragmentList;
    private List<Fragment> pages = new LinkedList<>();
    public FragmentPagerAdapter(FragmentManager fm, List<BaseV4Fragment> fragmentList)
    {
        super(fm);
        this.fragmentList = fragmentList;
    }
    @Override
    public Fragment getItem(int position)
    {
        Fragment page = null;
        if (pages.size() > position)
        {
            page = pages.get(position);
            if (page != null)
            {
                return page;
            }
        }
        while (position >= pages.size())
        {
            pages.add(null);
        }
        page = fragmentList.get(position);
        pages.set(position, page);
        //return fragmentList.get(position);
        return page;
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }
}
