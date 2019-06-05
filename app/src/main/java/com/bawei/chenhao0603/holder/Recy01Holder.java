package com.bawei.chenhao0603.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chenhao0603.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:14:20
 *@Description:${第一个条目}
 * */public class Recy01Holder extends RecyclerView.ViewHolder {

    public final TextView tvv1;
    public final ImageView img11;
    public final ImageView img12;
    public final ImageView img13;

    public Recy01Holder(@NonNull View itemView) {
        super(itemView);
        img11 = itemView.findViewById(R.id.img11);
        img12 = itemView.findViewById(R.id.img12);
        img13 = itemView.findViewById(R.id.img13);
        tvv1 = itemView.findViewById(R.id.tvv1);
    }
}
