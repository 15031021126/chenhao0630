package com.bawei.demo02.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.demo02.MyItemClick;
import com.bawei.demo02.R;
import com.bawei.demo02.bean.SearchEntity;
import com.bawei.demo02.holder.home.SearchHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/25
 *@Time:14:34
 *@Description:${DESCRIPTION}
 * */public class SearrchAdpter extends RecyclerView.Adapter<SearchHolder> {
    private Context context;
    private ArrayList<SearchEntity> searchEntities;
    private MyItemClick click;

    public SearrchAdpter(Context context, ArrayList<SearchEntity> searchEntities) {
        this.context = context;
        this.searchEntities = searchEntities;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new SearchHolder(from.inflate(R.layout.search_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder searchHolder, final int i) {
        searchHolder.searchcount.setText("已售"+searchEntities.get(i).getSaleNum()+"件");
        searchHolder.searchname.setText(searchEntities.get(i).getCommodityName());
        searchHolder.searchprice.setText("￥"+searchEntities.get(i).getPrice());
        Glide.with(context).load(searchEntities.get(i).getMasterPic()).into(searchHolder.searchimg);
       //点击事件
        searchHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onclick(searchEntities.get(i).getCommodityId());
            }
        });
    }

    public void setclick(MyItemClick click) {
        this.click=click;
    }

    @Override
    public int getItemCount() {
        return searchEntities.size();
    }
}
