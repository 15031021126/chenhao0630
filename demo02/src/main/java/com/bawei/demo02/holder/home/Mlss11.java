package com.bawei.demo02.holder.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:43
 *@Description:${DESCRIPTION}
 * */public class Mlss11 extends RecyclerView.ViewHolder {

    public final TextView mlssprice;
    public final TextView mlssname;
    public final ImageView mlssimg;

    public Mlss11(@NonNull View itemView) {
        super(itemView);
        mlssname = itemView.findViewById(R.id.name2);
        mlssimg = itemView.findViewById(R.id.mlssimg);
        mlssprice = itemView.findViewById(R.id.mlssprice);
    }
}
