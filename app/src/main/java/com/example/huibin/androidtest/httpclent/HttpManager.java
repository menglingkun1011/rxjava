package com.example.huibin.androidtest.httpclent;

import com.example.huibin.androidtest.constant.Constant;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {

    private Retrofit mRetrofit;
    private static HttpManager httpManager;

    public static HttpManager getInstance(){
        if(httpManager == null){
            synchronized (HttpManager.class){
                if(httpManager == null){
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    public Retrofit getRetrofit() {
        if(mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.baseUrl)
                    .client(getOkhttpClient(null))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
       return mRetrofit;
    }

    private OkHttpClient getOkhttpClient(InputStream[] certificates) {
        HttpsUtils.SSLParams sslParams  = new HttpsUtils().getSslSocketFactory(certificates,null,null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
                .build();
        return okHttpClient;
    }


}
