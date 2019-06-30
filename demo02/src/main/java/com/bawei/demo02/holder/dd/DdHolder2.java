package com.bawei.demo02.holder.dd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:21:49
 *@Description:${DESCRIPTION}
 * */public class DdHolder2 extends RecyclerView.ViewHolder {

    public final TextView ddname;
    public final TextView ddprice;
    public final ImageView ddimg;
    public final EditText ddnum;
    public final TextView ddjia;
    public final TextView ddjian;

    public DdHolder2(@NonNull View itemView) {
        super(itemView);
        ddname = itemView.findViewById(R.id.ddname);
        ddimg = itemView.findViewById(R.id.ddimg);
        ddprice = itemView.findViewById(R.id.ddprice);
        ddnum = itemView.findViewById(R.id.ddnum);
        ddjia = itemView.findViewById(R.id.ddjia);
        ddjian = itemView.findViewById(R.id.ddjian);
    }
}
