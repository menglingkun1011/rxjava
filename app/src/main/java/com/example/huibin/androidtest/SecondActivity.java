package com.example.huibin.androidtest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private GridView mGridView;
    private RecyclerView mRecyclerView;
    private List<String> data = new ArrayList<>();
    private MyGridViewAdapter mMyGridViewAdapter;
    private NestedScrollView mNestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activitym);

        for (int i = 0; i < 50; i++) {
            data.add(i,"标题"+i);
        }

        mGridView = (GridView) findViewById(R.id.gridView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        mMyGridViewAdapter = new MyGridViewAdapter(this,data);
        mGridView.setAdapter(mMyGridViewAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(new MyRecyAdapter(this,data));

        mRecyclerView.setNestedScrollingEnabled(false);
    }

    class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.ViewHoler>{

        private Context context;
        private List<String> data;

        public MyRecyAdapter(Context context, List<String> data) {
            this.data = data;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View rootView = View.inflate(context,R.layout.item_layout,null);
            return new ViewHoler(context,rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
           holder.mTv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHoler extends RecyclerView.ViewHolder{

            private final TextView mTv;

            public ViewHoler(Context context, View itemView) {
                super(itemView);
                mTv = itemView.findViewById(R.id.tv);
            }
        }
    }

    class MyGridViewAdapter extends BaseAdapter{

        private Context context;
        private List<String> data;

        public MyGridViewAdapter(Context context,List<String> data) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if(convertView == null){
                vh = new ViewHolder();
                convertView = View.inflate(context,R.layout.item_layout,null);
                vh.tv = (TextView)convertView.findViewById(R.id.tv);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv.setText(data.get(position));
            return convertView;
        }

        class ViewHolder{
            public TextView tv;
        }
    }
}
