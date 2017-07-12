package com.xiaolei.easyfreamtest;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by xiaolei on 2017/7/9.
 */

public interface Baidu
{
    @GET("s")
    Observable<String> getIndex();
}
