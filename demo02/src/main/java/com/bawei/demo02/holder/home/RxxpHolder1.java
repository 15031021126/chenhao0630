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
 * */public class RxxpHolder1 extends RecyclerView.ViewHolder {

    public final RecyclerView rxxprecy;
    public final TextView rxxpname;

    public RxxpHolder1(@NonNull View itemView) {
        super(itemView);
        rxxpname = itemView.findViewById(R.id.rxxpname);
        rxxprecy = itemView.findViewById(R.id.rxxprecy);
    }
}
