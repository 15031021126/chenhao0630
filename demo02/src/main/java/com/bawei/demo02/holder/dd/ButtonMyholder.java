package com.bawei.demo02.holder.dd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/22
 *@Time:16:13
 *@Description:${DESCRIPTION}
 * */public class ButtonMyholder extends RecyclerView.ViewHolder {
    public final Button good1;
    public final Button good2;

    public ButtonMyholder(@NonNull View itemView) {
        super(itemView);
        good1 = itemView.findViewById(R.id.good1);
        good2 = itemView.findViewById(R.id.good2);
    }
}
