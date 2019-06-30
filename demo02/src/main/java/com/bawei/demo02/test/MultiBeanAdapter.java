package com.bawei.demo02.test;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.holder.dd.HeandlerMyholder;
import com.gersion.library.adapter.MultiTypeAdapter;
import com.gersion.library.viewholder.BaseViewHolder;

/*
 *@Auther:陈浩
 *@Date: 2019/6/24
 *@Time:16:00
 *@Description:${DESCRIPTION}
 * */public class MultiBeanAdapter extends MultiTypeAdapter<Pedido.OrderListBean, HeandlerMyholder> {
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Pedido.OrderListBean orderListBean) {
        baseViewHolder.setText(R.id.headname,orderListBean.getOrderId());
    }
}
