package com.xiaolei.easyfreamwork.base;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xiaolei on 2017/12/18.
 */

public abstract class SimpleAdapter<T> extends BaseAdapter<T,BaseAdapter.Holder>
{
    public SimpleAdapter(List<T> list)
    {
        super(list);
    }
    @Override
    public abstract Holder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull int viewType);
    @Override
    public abstract void onBindViewHolder(@NonNull Holder holder, @NonNull T data, @NonNull int position);
}
