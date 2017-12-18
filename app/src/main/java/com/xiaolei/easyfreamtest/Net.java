package com.xiaolei.easyfreamtest;

import com.xiaolei.okhttputil.Catch.CacheHeaders;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by xiaolei on 2017/7/9.
 */

public interface Net
{
    @Headers(CacheHeaders.PRIVATE)
    @FormUrlEncoded
    @POST("geocoding")
    Call<DTBean> getIndex(@Field("a") String a);
}
