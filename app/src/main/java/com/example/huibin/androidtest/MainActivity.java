package com.example.huibin.androidtest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.huibin.androidtest.adapter.MenuAdapter;
import com.example.huibin.androidtest.bean.MenuItemBean;
import com.example.huibin.androidtest.view.DownMenuView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DownMenuView mDownMenuView;
    private List<MenuItemBean> data = new ArrayList<>();
    private MenuAdapter mMenuAdapter;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initMenu();

        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mDownMenuView.startAnim();
//                startActivity(new Intent(MainActivity.this,SecondActivity.class));
                startActivity(new Intent(MainActivity.this,ThirdActivity.class));
            }
        });

        Drawable rightDrawable = getResources().getDrawable(R.drawable.ic_launcher_background);
        rightDrawable.setBounds(0,0,rightDrawable.getMinimumWidth(),rightDrawable.getMinimumHeight());
        mTv.setCompoundDrawablePadding(100);
        mTv.setCompoundDrawables(null,null,rightDrawable,null);



    }

    private void initMenu() {
        mDownMenuView = (DownMenuView) findViewById(R.id.downMenu);
        mMenuAdapter = new MenuAdapter(data,this);
        mDownMenuView.setAdapter(mMenuAdapter);
        mDownMenuView.setOnItemSelectListener(new DownMenuView.OnItemSelectListener() {
            @Override
            public void onItemSelect(MenuItemBean itemBean) {
                mTv.setText(itemBean.getTitle());
                if(AntiShakeUtils.isFingerShake(1500)){
                    Log.d(TAG, "onCreate: AAAAAAAAAAAAAAa");
                }
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            if(i == 0){
                data.add(new MenuItemBean("标题"+i,true));
                continue;
            }
            data.add(new MenuItemBean("标题"+i,false));
        }
    }
}
