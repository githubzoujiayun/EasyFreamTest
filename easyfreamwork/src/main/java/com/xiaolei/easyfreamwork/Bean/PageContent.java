package com.xiaolei.easyfreamwork.Bean;

import java.util.List;

/**
 * 翻页所需要的
 * Created by xiaolei on 2017/3/10.
 */
public class PageContent<T>
{
    /**
     * current : 1
     * size : 10
     * total : 113
     * pageCount : 12
     * list : []
     */
    private int current;
    private int size;
    private int total;
    private int pageCount;
    private List<T> list;

    public int getCurrent()
    {
        return current;
    }

    public void setCurrent(int current)
    {
        this.current = current;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getPageCount()
    {
        return pageCount;
    }

    public void setPageCount(int pageCount)
    {
        this.pageCount = pageCount;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }
}
