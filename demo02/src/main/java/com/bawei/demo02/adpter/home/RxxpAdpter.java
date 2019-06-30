package com.bawei.demo02.adpter.home;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.demo02.DetalleActivity;
import com.bawei.demo02.MyItemClick;
import com.bawei.demo02.R;
import com.bawei.demo02.bean.HomeNei;
import com.bawei.demo02.holder.home.RxxpHolder11;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:49
 *@Description:${DESCRIPTION}
 * */public class RxxpAdpter extends RecyclerView.Adapter<RxxpHolder11> {
    private Context context;
    private ArrayList<HomeNei> homeNeis;
    private MyItemClick click;

    public RxxpAdpter(Context context, ArrayList<HomeNei> homeNeis) {
        this.context = context;
        this.homeNeis = homeNeis;
    }

    @NonNull
    @Override
    public RxxpHolder11 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new RxxpHolder11(from.inflate(R.layout.rxxp11, viewGroup, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RxxpHolder11 rxxpHolder11, final int i) {
        if (rxxpHolder11.name.getMaxLines() > 1) {
            //设置超出的店
            rxxpHolder11.name.setText(homeNeis.get(i).getCommodityName());
        } else {
            rxxpHolder11.name.setText(homeNeis.get(i).getCommodityName());
            rxxpHolder11.rxxpprice.setText("￥" + homeNeis.get(i).getPrice());
            Glide.with(context).load(homeNeis.get(i).getMasterPic()).into(rxxpHolder11.rxxpimg);
     }

        //点击事件
        rxxpHolder11.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click.onclick(homeNeis.get(i).getCommodityId());
                Intent intent = new Intent(context, DetalleActivity.class);
                EventBus.getDefault().postSticky(homeNeis.get(i).getCommodityId());
                context.startActivity(intent);
            }
        });
    }

    private void setClick(MyItemClick click) {
        this.click=click;
    }

    @Override
    public int getItemCount() {
        return homeNeis.size();
    }
}
