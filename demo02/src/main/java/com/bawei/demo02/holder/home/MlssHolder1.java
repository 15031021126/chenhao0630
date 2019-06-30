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
 * */public class MlssHolder1 extends RecyclerView.ViewHolder {

    public final RecyclerView mlssrecy;
    public final TextView mlssname;

    public MlssHolder1(@NonNull View itemView) {
        super(itemView);
        mlssname = itemView.findViewById(R.id.mlssname);
        mlssrecy = itemView.findViewById(R.id.mlssrecy);
    }
}
