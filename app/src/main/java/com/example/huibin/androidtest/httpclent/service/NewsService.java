package com.example.huibin.androidtest.httpclent.service;

import com.example.huibin.androidtest.bean.resp.NewRespBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface NewsService {

    @GET("index")
    Observable<NewRespBean> getNewList(@Query("type") String type, @Query("key") String key);

}
