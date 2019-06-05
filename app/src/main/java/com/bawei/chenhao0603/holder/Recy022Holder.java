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
 *@Description:${第二个条目}
 * */public class Recy022Holder extends RecyclerView.ViewHolder {

    public final TextView tv21;
    public final TextView tv22;
    public final ImageView img022;

    public Recy022Holder(@NonNull View itemView) {
        super(itemView);
        tv21 = itemView.findViewById(R.id.tv21);
        tv22 = itemView.findViewById(R.id.tv22);
        img022 = itemView.findViewById(R.id.img022);
    }
}
