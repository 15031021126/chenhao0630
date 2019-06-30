package com.bawei.demo02.adpter.home;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.HomeNei;
import com.bawei.demo02.holder.home.Mlss11;
import com.bawei.demo02.holder.home.MlssHolder1;
import com.bawei.demo02.holder.home.RxxpHolder11;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:49
 *@Description:${DESCRIPTION}
 * */public class MlssAdpter extends RecyclerView.Adapter<Mlss11> {
    private Context context;
    private ArrayList<HomeNei> homeNeis;

    public MlssAdpter(Context context, ArrayList<HomeNei> homeNeis) {
        this.context = context;
        this.homeNeis = homeNeis;
    }


    @NonNull
    @Override
    public Mlss11 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new Mlss11(from.inflate(R.layout.mlss11, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull Mlss11 mlss11, int i) {
        mlss11.mlssname.setText(homeNeis.get(i).getCommodityName());
        mlss11.mlssprice.setText("￥" + homeNeis.get(i).getPrice());
        Glide.with(context).load(homeNeis.get(i).getMasterPic()).into(mlss11.mlssimg);
    }


    @Override
    public int getItemCount() {
        return homeNeis.size();
    }
}
