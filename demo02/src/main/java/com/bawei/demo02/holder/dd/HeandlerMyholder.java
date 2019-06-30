package com.bawei.demo02.holder.dd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/22
 *@Time:16:12
 *@Description:${DESCRIPTION}
 * */public class HeandlerMyholder extends RecyclerView.ViewHolder {

    public final TextView headname;

    public HeandlerMyholder(@NonNull View itemView) {
        super(itemView);
        headname = itemView.findViewById(R.id.headname);
    }
}
