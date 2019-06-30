package com.bawei.demo02.holder.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/25
 *@Time:14:35
 *@Description:${DESCRIPTION}
 * */public class SearchHolder extends RecyclerView.ViewHolder {

    public final ImageView searchimg;
    public final TextView searchcount;
    public final TextView searchname;
    public final TextView searchprice;

    public SearchHolder(@NonNull View itemView) {
        super(itemView);
        searchimg = itemView.findViewById(R.id.search_img);
        searchcount = itemView.findViewById(R.id.search_count);
        searchname = itemView.findViewById(R.id.search_name);
        searchprice = itemView.findViewById(R.id.search_price);
    }
}
