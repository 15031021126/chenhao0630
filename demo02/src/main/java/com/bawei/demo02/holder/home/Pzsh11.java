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
 * */public class Pzsh11 extends RecyclerView.ViewHolder {
    /***
     * 里头
     */
    public final TextView pzshprice;
    public final TextView name3;
    public final ImageView pzshimg;

    public Pzsh11(@NonNull View itemView) {
        super(itemView);
        name3 = itemView.findViewById(R.id.name3);
        pzshimg = itemView.findViewById(R.id.pzshimg);
        pzshprice = itemView.findViewById(R.id.pzshprice);
    }
}
