package com.bawei.demo02.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/18
 *@Time:11:44
 *@Description:${DESCRIPTION}
 * */public class ShopCarHolder extends RecyclerView.ViewHolder {

    public final CheckBox fccb;
    public final RecyclerView recycar;
    public final TextView cardhome;

    public ShopCarHolder(@NonNull View itemView) {
        super(itemView);
        fccb = itemView.findViewById(R.id.chekfather);
        recycar = itemView.findViewById(R.id.carrecy);
        cardhome = itemView.findViewById(R.id.cardhome);
    }
}
