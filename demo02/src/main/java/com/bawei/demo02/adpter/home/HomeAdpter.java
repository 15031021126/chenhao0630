package com.bawei.demo02.adpter.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.HomeBanner;
import com.bawei.demo02.bean.HomeNei;
import com.bawei.demo02.bean.HomeWai;
import com.bawei.demo02.holder.home.HomeXbannerHolder;
import com.bawei.demo02.holder.home.MlssHolder1;
import com.bawei.demo02.holder.home.PzshHolder1;
import com.bawei.demo02.holder.home.RxxpHolder1;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/20
 *@Time:17:27
 *@Description:${DESCRIPTION}
 * */public class HomeAdpter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<HomeBanner> banners;
    private ArrayList<HomeWai> homeWais;
    private ArrayList<HomeNei> homeNeis;

    public HomeAdpter(Context context, ArrayList<HomeBanner> banners, ArrayList<HomeWai> homeWais, ArrayList<HomeNei> homeNeis) {
        this.context = context;
        this.banners = banners;
        this.homeWais = homeWais;
        this.homeNeis = homeNeis;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        switch (getItemViewType(i)) {
            case 0:
                return new HomeXbannerHolder(from.inflate(R.layout.homexbannder, viewGroup, false));
            case 1:
                return new RxxpHolder1(from.inflate(R.layout.rxxp1, viewGroup, false));
            case 2:
                return new MlssHolder1(from.inflate(R.layout.mlss1, viewGroup, false));
            case 3:
                return new PzshHolder1(from.inflate(R.layout.pzsh1, viewGroup, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((HomeXbannerHolder) viewHolder).homexBanner.setBannerData(banners);
                ((HomeXbannerHolder) viewHolder).homexBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        HomeBanner model1 = (HomeBanner) model;
                        Glide.with(context).load(model1.getXBannerUrl()).into((ImageView) view);
                    }
                });
                /**
                 * 轮播点击事件
                 */
                ((HomeXbannerHolder) viewHolder).homexBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(XBanner banner, Object model, View view, int position) {
                        Toast.makeText(context, "" + banners.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case 1:
                ((RxxpHolder1) viewHolder).rxxpname.setText(homeWais.get(0).getRxxp());
                ((RxxpHolder1) viewHolder).rxxprecy.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false));
                ((RxxpHolder1) viewHolder).rxxprecy.setAdapter(new RxxpAdpter(context, homeNeis));
                break;
            case 2:
                ((MlssHolder1) viewHolder).mlssname.setText(homeWais.get(0).getMlss());
                ((MlssHolder1) viewHolder).mlssrecy.setLayoutManager(new LinearLayoutManager(context));
                ((MlssHolder1) viewHolder).mlssrecy.setAdapter(new MlssAdpter(context, homeNeis));
                break;
            case 3:
                ((PzshHolder1) viewHolder).pzshname.setText(homeWais.get(0).getPzsh());
                ((PzshHolder1) viewHolder).pzshrecy.setLayoutManager(new GridLayoutManager(context, 2));
                ((PzshHolder1) viewHolder).pzshrecy.setAdapter(new PzshAdpter(context, homeNeis));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }
}
