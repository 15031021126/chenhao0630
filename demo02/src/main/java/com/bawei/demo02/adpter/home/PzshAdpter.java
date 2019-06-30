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
import com.bawei.demo02.holder.home.Pzsh11;
import com.bawei.demo02.holder.home.RxxpHolder11;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:20:49
 *@Description:${DESCRIPTION}
 * */public class PzshAdpter extends RecyclerView.Adapter<Pzsh11> {
    private Context context;
    private ArrayList<HomeNei> homeNeis;

    public PzshAdpter(Context context, ArrayList<HomeNei> homeNeis) {
        this.context = context;
        this.homeNeis = homeNeis;
    }


    @NonNull
    @Override
    public Pzsh11 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new Pzsh11(from.inflate(R.layout.pzsh11, viewGroup, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull Pzsh11 pzsh11, int i) {
            pzsh11.name3.setText(homeNeis.get(i).getCommodityName());
            pzsh11.pzshprice.setText("￥" + homeNeis.get(i).getPrice());
            Glide.with(context).load(homeNeis.get(i).getMasterPic()).into(pzsh11.pzshimg);
    }


    @Override
    public int getItemCount() {
        return homeNeis.size();
    }
}
