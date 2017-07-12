package com.xiaolei.easyfreamwork.network.OKhttp;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by xiaolei on 2017/3/14.
 */

public class CookieManger implements CookieJar
{
    private Context mContext;
    private PersistentCookieStore cookieStore;
    public CookieManger(Context context)
    {
        mContext = context;
        if (cookieStore == null)
        {
            cookieStore = new PersistentCookieStore(mContext);
        }
    }
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        if (cookies != null && cookies.size() > 0)
        {
            for (Cookie item : cookies)
            {
                cookieStore.add(url, item);
            }
        }
    }
    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    public PersistentCookieStore getCookieStore()
    {
        return cookieStore;
    }
}