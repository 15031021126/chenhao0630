package com.bawei.demo02.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.CardItemEntity;
import com.bawei.demo02.bean.CateGoryName;
import com.bawei.demo02.holder.ShopCarHolder;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/18
 *@Time:14:18
 *@Description:${DESCRIPTION}
 * */public class ShopCarAdpter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<CateGoryName> cateGoryNames;
    private ArrayList<CardItemEntity> itemEntities;

    public ShopCarAdpter(Context context, ArrayList<CateGoryName> cateGoryNames, ArrayList<CardItemEntity> itemEntities) {
        this.context = context;
        this.cateGoryNames = cateGoryNames;
        this.itemEntities = itemEntities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (getItemViewType(i)) {
            case 0:
                return new ShopCarHolder(inflater.inflate(R.layout.shop_father, viewGroup, false));
            case 1:
                return new ShopCarHolder(inflater.inflate(R.layout.shop_father, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((ShopCarHolder)viewHolder).cardhome.setText(cateGoryNames.get(i).getCategoryName());
                ((ShopCarHolder)viewHolder).recycar.setLayoutManager(new LinearLayoutManager(context));
                ((ShopCarHolder)viewHolder).recycar.setAdapter(new ShopItemAdpter(context,itemEntities));
                break;
            case 1:
                ((ShopCarHolder)viewHolder).cardhome.setText(cateGoryNames.get(i).getCategoryName());
                ((ShopCarHolder)viewHolder).recycar.setLayoutManager(new LinearLayoutManager(context));
                ((ShopCarHolder)viewHolder).recycar.setAdapter(new ShopItemAdpter(context,itemEntities));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cateGoryNames.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % cateGoryNames.size();
    }
}
