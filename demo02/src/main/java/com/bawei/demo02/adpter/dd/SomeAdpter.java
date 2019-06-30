package com.bawei.demo02.adpter.dd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.holder.dd.ButtonMyholder;
import com.bawei.demo02.holder.dd.DdHolder2;
import com.bawei.demo02.holder.dd.HeandlerMyholder;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/22
 *@Time:10:39
 *@Description:${DESCRIPTION}
 * */public class SomeAdpter extends RecyclerView.Adapter {
    private ArrayList<Pedido.OrderListBean> orderListBeans;
    private Context context;
    private int ITEM_HEADER = 1, ITEM_CONTENT = 2, ITEM_FOOTER = 3;

    public SomeAdpter(ArrayList<Pedido.OrderListBean> orderListBeans, Context context) {
        this.orderListBeans = orderListBeans;
        this.context = context;
        Log.e("123", "SomeAdpter: " + orderListBeans);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        switch (getItemViewType(i)) {
            case 0:
                return new HeandlerMyholder(from.inflate(R.layout.goodsheand, viewGroup, false));
            case 1:
                return new DdHolder2(from.inflate(R.layout.dd2, viewGroup, false));
            case 2:
                return new ButtonMyholder(from.inflate(R.layout.gooditem, viewGroup, false));
        }
//        if (i == ITEM_HEADER) {
//            LayoutInflater from = LayoutInflater.from(context);
//            return new HeandlerMyholder(from.inflate(R.layout.goodsheand, viewGroup, false));
//        } else if (i == ITEM_CONTENT) {
//            LayoutInflater from = LayoutInflater.from(context);
//            return new DdHolder2(from.inflate(R.layout.dd2, viewGroup, false));
//        } else if (i == ITEM_FOOTER) {
//            LayoutInflater from = LayoutInflater.from(context);
//
//            return new ButtonMyholder(from.inflate(R.layout.gooditem, viewGroup, false));
//        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((HeandlerMyholder) viewHolder).headname.setText("订单号\t"+orderListBeans.get(i/3).getOrderId());
                break;
            case 1:

                if(i<=5){
                    for (int j = 0; j <orderListBeans.get(i).getDetailList().size() ; j++) {
                        Pedido.OrderListBean.DetailListBean detailListBean = orderListBeans.get(i / 3).getDetailList().get(j);
                        String commodityName = detailListBean.getCommodityName();
                        Log.e("1234", "onBindViewHolder: "+commodityName );
                        ((DdHolder2) viewHolder).ddname.setText(commodityName);
                    }
                }
                break;
            case 2:
                ((ButtonMyholder) viewHolder).good1.setText("00");
                break;

        }
//        if (viewHolder instanceof HeandlerMyholder) {
//            ((HeandlerMyholder) viewHolder).headname.setText(orderListBeans.get(i / 3).getOrderId());
//        } else if (viewHolder instanceof DdHolder2) {
//            ((DdHolder2) viewHolder).ddname.setText(orderListBeans.get(i / 3).getDetailList().get(0).getCommodityName());
//        } else if (viewHolder instanceof ButtonMyholder) {
//
//            ((ButtonMyholder) viewHolder).good1.setText("00");
//        }
    }

    @Override
    public int getItemCount() {
        return orderListBeans.size()*3;
    }

    @Override
    public int getItemViewType(int position) {

        return position%3;

    }
}
