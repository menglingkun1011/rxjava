package com.example.huibin.androidtest;

import android.os.Bundle;
import android.util.Log;

import com.example.huibin.androidtest.base.BaseActivity;
import com.example.huibin.androidtest.bean.resp.NewRespBean;
import com.example.huibin.androidtest.httpclent.HttpManager;
import com.example.huibin.androidtest.httpclent.service.NewsService;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ThirdActivity extends BaseActivity {

    private String TAG = ThirdActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        NewsService newsService = HttpManager
                .getInstance()
                .getRetrofit()
                .create(NewsService.class);

        newsService.getNewList("头条","8f8f5ff9be5e0e8c07a1e4a5c7e7b639")
//                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewRespBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewRespBean newRespBean) {
                        Log.d(TAG, "onNext: "+newRespBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.just(this)
                .compose(new ObservableTransformer<ThirdActivity, String>() {
                    @Override
                    public ObservableSource<String> apply(Observable<ThirdActivity> upstream) {
                        return null;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
