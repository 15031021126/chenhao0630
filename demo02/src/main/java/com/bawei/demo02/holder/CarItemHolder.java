package com.bawei.demo02.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/18
 *@Time:11:49
 *@Description:${DESCRIPTION}
 * */public class CarItemHolder extends RecyclerView.ViewHolder {

    public final CheckBox zchebox;
    public final ImageView cardimg;
    public final TextView cardname;
    public final TextView cardprice;
    public final TextView jian;
    public final TextView jia;
    public final EditText cardnum;

    public CarItemHolder(@NonNull View itemView) {
        super(itemView);
        zchebox = itemView.findViewById(R.id.zchebox);
        cardimg = itemView.findViewById(R.id.cardimg);
        cardname = itemView.findViewById(R.id.cardname);
        cardprice = itemView.findViewById(R.id.cardprice);
        jian = itemView.findViewById(R.id.jian);
        jia = itemView.findViewById(R.id.jia);
        cardnum = itemView.findViewById(R.id.cardnum);
    }
}
