package com.bawei.demo02.holder.dd;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.demo02.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:21:49
 *@Description:${DESCRIPTION}
 * */public class DdHolder1 extends RecyclerView.ViewHolder {

    public final TextView orderId;
    public final RecyclerView dditemrecy;
    public final Button qzf;
    public final Button candeldd;

    public DdHolder1(@NonNull View itemView) {
        super(itemView);
        orderId = itemView.findViewById(R.id.orderId);
        dditemrecy = itemView.findViewById(R.id.dditemrecy);
        candeldd = itemView.findViewById(R.id.cancledd);
        qzf = itemView.findViewById(R.id.qzf);
    }
}
