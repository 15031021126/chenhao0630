package com.bawei.demo02.adpter.dd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.holder.dd.DdHolder2;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/6/22
 *@Time:9:09
 *@Description:${DESCRIPTION}
 * */public class Dd2Adpter extends RecyclerView.Adapter<DdHolder2> {
    private Context context;
    private ArrayList<Pedido.OrderListBean> orderListBeans;

    public Dd2Adpter(Context context, ArrayList<Pedido.OrderListBean> orderListBeans) {
        this.context = context;
        this.orderListBeans = orderListBeans;
    }

    @NonNull
    @Override
    public DdHolder2 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new DdHolder2(from.inflate(R.layout.dd2, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DdHolder2 ddHolder2, int i) {

        List<Pedido.OrderListBean.DetailListBean> detailList = orderListBeans.get(i).getDetailList();
        for (int j = 0; j < detailList.size(); j++) {
            Pedido.OrderListBean.DetailListBean detailListBean = detailList.get(j);
            Log.e("123", "showDd: " + detailListBean.getCommodityName() + "\n");
            ddHolder2.ddname.setText(detailListBean.getCommodityName());
            ddHolder2.ddprice.setText("￥" + detailListBean.getCommodityPrice());
            ddHolder2.ddnum.setText("" + detailListBean.getCommodityCount());
        }
        String commodityPic = detailList.get(i).getCommodityPic();
        String[] split = commodityPic.split(",");
        Glide.with(context).load(split[i]).into(ddHolder2.ddimg);
        //ddHolder2.ddname.setText(orderListBean.getCommodityName());
    }

    @Override
    public int getItemCount() {
        return orderListBeans.get(0).getDetailList().size();
    }
}
