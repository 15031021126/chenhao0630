package com.bawei.demo02.holder.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.demo02.R;
import com.stx.xhb.xbanner.XBanner;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:17:03
 *@Description:${DESCRIPTION}
 * */public class HomeXbannerHolder extends RecyclerView.ViewHolder {

    public final XBanner homexBanner;

    public HomeXbannerHolder(@NonNull View itemView) {
        super(itemView);
        homexBanner = itemView.findViewById(R.id.homexbanner);
    }
}
