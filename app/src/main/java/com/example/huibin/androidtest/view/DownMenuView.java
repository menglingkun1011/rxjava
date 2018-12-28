package com.example.huibin.androidtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.huibin.androidtest.R;
import com.example.huibin.androidtest.adapter.MenuAdapter;
import com.example.huibin.androidtest.bean.MenuItemBean;

public class DownMenuView extends FrameLayout {

    private GridView mGridView;
    private View mMarkView;
    private View mRootView;

    public DownMenuView(Context context) {
        this(context,null);
    }

    public DownMenuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DownMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mRootView = LayoutInflater.from(context).inflate(R.layout.down_menu_layout, null);
        mGridView = mRootView.findViewById(R.id.gridView);
        mMarkView = mRootView.findViewById(R.id.markView);
        mMarkView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnim();
            }
        });
        addView(mRootView);

        mGridView.setVisibility(GONE);
        mMarkView.setVisibility(GONE);
    }

    public void startAnim() {
        mMarkView.setVisibility(VISIBLE);
        mMarkView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
        mGridView.setVisibility(VISIBLE);
        mGridView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
    }

    public void endAnim() {
        mGridView.setVisibility(GONE);
        mGridView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
        mMarkView.setVisibility(GONE);
        mMarkView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
    }

    public void setAdapter(final MenuAdapter menuAdapter){
        mGridView.setAdapter(menuAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < menuAdapter.getCount(); i++) {
                    if(i == position){
                        ((MenuItemBean)menuAdapter.getItem(i)).setSelect(true);
                        continue;
                    }
                    ((MenuItemBean)menuAdapter.getItem(i)).setSelect(false);
                }
                menuAdapter.notifyDataSetChanged();
                if(mOnItemSelectListener != null) mOnItemSelectListener.onItemSelect(((MenuItemBean)menuAdapter.getItem(position)));

                endAnim();
            }
        });
    }


    public void setOnItemSelectListener(OnItemSelectListener onItemSelectListener) {
        mOnItemSelectListener = onItemSelectListener;
    }

    private OnItemSelectListener mOnItemSelectListener;

    public interface OnItemSelectListener{

        void onItemSelect(MenuItemBean itemBean);

    }

}
