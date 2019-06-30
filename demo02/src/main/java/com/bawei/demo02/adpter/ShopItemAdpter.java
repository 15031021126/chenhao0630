package com.bawei.demo02.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.CardItemEntity;
import com.bawei.demo02.holder.CarItemHolder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


/*
 *@Auther:陈浩
 *@Date: 2019/6/15
 *@Time:10:12
 *@Description:${DESCRIPTION}
 * */public class ShopItemAdpter extends RecyclerView.Adapter<CarItemHolder> {
    private Context context;
    private ArrayList<CardItemEntity> itemEntities;
    int c = 0;
    private int allprice=0;

    public ShopItemAdpter(Context context, ArrayList<CardItemEntity> itemEntities) {
        this.context = context;
        this.itemEntities = itemEntities;
    }

    @NonNull
    @Override
    public CarItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new CarItemHolder(inflater.inflate(R.layout.shop_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CarItemHolder carItemHolder, int i) {
        final CardItemEntity bean = itemEntities.get(i);
        /**
         * 加
         */
        carItemHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = carItemHolder.cardnum.getText().toString().trim();
                int i1 = Integer.parseInt(trim);
                int i2 = i1 += 1;
                if (carItemHolder.zchebox.isChecked() == true) {
                    c = i2 * Integer.parseInt(bean.getPrice());
                    EventBus.getDefault().postSticky(c);
                }
                carItemHolder.cardnum.setText(i2 + "");
            }
        });
        /**
         * 减
         */
        carItemHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = carItemHolder.cardnum.getText().toString().trim();
                int i1 = Integer.parseInt(trim);
                if (i1 == 1) {
                    Toast.makeText(context, "不能减了", Toast.LENGTH_SHORT).show();
                } else {
                    int i2 = i1 -= 1;
                    if (carItemHolder.zchebox.isChecked() == true) {
                        c = i2 * Integer.parseInt(bean.getPrice());
                        EventBus.getDefault().postSticky(c);
                    }
                    carItemHolder.cardnum.setText(i2 + "");
                }
            }
        });
        carItemHolder.cardname.setText(bean.getPrice() + "$");
        Glide.with(context).load(bean.getPic()).into(carItemHolder.cardimg);
        carItemHolder.cardname.setText(bean.getCommodityName());
        carItemHolder.cardnum.setText(bean.getCount());
        carItemHolder.cardprice.setText(bean.getPrice());
        carItemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+bean.getCommodityId() ,Toast.LENGTH_SHORT).show();
            }
        });

/**
 * 总价
 */
        final String s = bean.getPrice();
        carItemHolder.zchebox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    final String trim = carItemHolder.cardnum.getText().toString().trim();
                    c = Integer.parseInt(s) * Integer.parseInt(trim);

                    EventBus.getDefault().post(c);
                } else {
                    final String trim = carItemHolder.cardnum.getText().toString().trim();
                    c=Integer.parseInt(s) * Integer.parseInt(trim);
                    //EventBus.getDefault().post(c);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return itemEntities.size();
    }
}
