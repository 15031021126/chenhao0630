package com.bawei.demo02.adpter.dd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.demo02.MyItemClick;
import com.bawei.demo02.R;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.holder.dd.DdHolder1;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/21
 *@Time:21:52
 *@Description:${DESCRIPTION}
 * */public class Dd1Adpter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Pedido.OrderListBean> orderListBeans;
    private MyItemClick click;

    public Dd1Adpter(Context context, ArrayList<Pedido.OrderListBean> orderListBeans) {
        this.context = context;
        this.orderListBeans = orderListBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        switch (getItemViewType(i)) {
            case 0:
                return new DdHolder1(from.inflate(R.layout.dd1, viewGroup, false));

            case 1:
                return new DdHolder1(from.inflate(R.layout.dd1, viewGroup, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((DdHolder1) viewHolder).orderId.setText("订单号\t"+orderListBeans.get(i).getOrderId()+"\t\t"+orderListBeans.get(i).getExpressSn());
                Log.e("123", "onBindViewHolder: "+ orderListBeans);
                ((DdHolder1) viewHolder).dditemrecy.setLayoutManager(new LinearLayoutManager(context));
                ((DdHolder1) viewHolder).dditemrecy.setAdapter(new Dd2Adpter(context, orderListBeans));
                /**
                 * 取消订单
                 */
                ((DdHolder1) viewHolder).candeldd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onclick(orderListBeans.get(i).getOrderId());
                    }
                });
                ((DdHolder1) viewHolder).qzf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onclick(orderListBeans.get(i).getOrderId());
                    }
                });
                break;
            case 1:
                ((DdHolder1) viewHolder).orderId.setText("订单号\t"+orderListBeans.get(i).getOrderId()+"\t\t"+orderListBeans.get(i).getExpressSn());
                Log.e("123", "onBindViewHolder: "+ orderListBeans);
                ((DdHolder1) viewHolder).dditemrecy.setLayoutManager(new LinearLayoutManager(context));
                ((DdHolder1) viewHolder).dditemrecy.setAdapter(new Dd2Adpter(context, orderListBeans));
                /**
                 * 取消订单
                 */
                ((DdHolder1) viewHolder).candeldd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        click.onclick(orderListBeans.get(i).getOrderId());
                    }
                });
                break;

        }
    }

    public void setClick(MyItemClick click) {
        this.click=click;
    }

    @Override
    public int getItemCount() {
        return orderListBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

}
