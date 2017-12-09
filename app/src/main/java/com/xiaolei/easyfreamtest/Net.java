package com.xiaolei.easyfreamtest;


import com.xiaolei.OkhttpCacheInterceptor.Header.CacheHeaders;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xiaolei on 2017/7/9.
 */

public interface Net
{
    @Headers(CacheHeaders.PRIVATE)
    @FormUrlEncoded
    @POST("geocoding")
    Observable<DTBean> getIndex(@Field("a") String a);
}
