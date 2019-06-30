package com.bawei.demo02.holder.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:43
 *@Description:${DESCRIPTION}
 * */public class PzshHolder1 extends RecyclerView.ViewHolder {

    public final RecyclerView pzshrecy;
    public final TextView pzshname;

    public PzshHolder1(@NonNull View itemView) {
        super(itemView);
        pzshname = itemView.findViewById(R.id.pzshname);
        pzshrecy = itemView.findViewById(R.id.pzshrecy);
    }
}
