package com.bawei.demo02.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.holder.dd.HeandlerMyholder;

import java.util.ArrayList;

import me.drakeet.multitype.ItemViewProvider;

/*
 *@Auther:陈浩
 *@Date: 2019/6/24
 *@Time:14:34
 *@Description:${DESCRIPTION}
 * */public class CategoryViewProvider extends ItemViewProvider<Pedido.OrderListBean,HeandlerMyholder> {
    private ArrayList<Pedido.OrderListBean> orderListBeans;
    private Context context;

    public CategoryViewProvider(ArrayList<Pedido.OrderListBean> orderListBeans, Context context) {
        this.orderListBeans = orderListBeans;
        this.context = context;
    }

    @NonNull
    @Override
    protected HeandlerMyholder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        LayoutInflater from = LayoutInflater.from(context);
        return new HeandlerMyholder(from.inflate(R.layout.goodsheand,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull HeandlerMyholder holder, @NonNull Pedido.OrderListBean orderListBean) {
        holder.headname.setText(orderListBean.getOrderId());
    }
}
