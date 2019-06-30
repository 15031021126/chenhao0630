package com.bawei.demo02.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.demo02.R;
import com.bawei.demo02.adpter.ShopCarAdpter;
import com.bawei.demo02.bean.CardItemEntity;
import com.bawei.demo02.bean.CateGoryName;
import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/*
 *@Auther:陈浩
 *@Date: 2019/6/12
 *@Time:14:43
 *@Description:${DESCRIPTION}
 * */
public class Frag03 extends Fragment implements IContract.IView {
    @BindView(R.id.shop_recy)
    XRecyclerView shopRecy;
    Unbinder unbinder;
    @BindView(R.id.qx)
    CheckBox qx;
    @BindView(R.id.zj)
    TextView zj;
    @BindView(R.id.js)
    TextView js;
    private PersenterImpl persenter;
    private String sessionId;
    private String userId;
    private ArrayList<CateGoryName> cateGoryNames;
    private ArrayList<CardItemEntity> itemEntities;
    private ArrayList<CardItemEntity> itemEntities1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag03, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        shopRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        persenter = new PersenterImpl();
        persenter.attach(this);
        persenter.requestShop("http://172.17.8.100/", "small/order/verify/v1/findShoppingCart", sessionId, userId);

        //结算
        js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "去结算!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 接受ids
     *
     * @param idBeans
     */
    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void even(IdBeans idBeans) {
        Log.e("123", "even: " + idBeans);
        sessionId = idBeans.getSessionId();
        userId = idBeans.getUserId();
    }

    /**
     * 注册eventbus
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    /**
     * 总价
     *
     * @param c
     */
    @Subscribe
    public void EvZj(Integer c) {
        Log.e("123", "单条目价格--: " + c);
        zj.setText("￥" + c);
    }

    @Override
    public void shwoData(String s) {
        Log.e("123", "shwoData: " + s);
    }

    /**
     * 解析
     *
     * @param s
     */
    @Override
    public void shwoShop(String s) {
        Log.e("123", "shw----------------oShop: " + s);
        cateGoryNames = new ArrayList<>();
        itemEntities = new ArrayList<>();
        itemEntities1 = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(s);
            JSONArray result = object.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                JSONObject o = (JSONObject) result.get(i);
                String categoryName = o.getString("categoryName");
                cateGoryNames.add(new CateGoryName(categoryName));
                JSONArray shoppingCartList = o.getJSONArray("shoppingCartList");
                for (int j = 0; j < shoppingCartList.length(); j++) {
                    JSONObject o1 = (JSONObject) shoppingCartList.get(j);
                    String commodityId = o1.getString("commodityId");
                    String commodityName = o1.getString("commodityName");
                    String count = o1.getString("count");
                    String pic = o1.getString("pic");
                    String price = o1.getString("price");
                    ///添加进去
                    itemEntities.add(new CardItemEntity(commodityId, commodityName, count, pic, price, false));
                }
                //适配器
                Log.e("123", "showShopData: " + cateGoryNames + "\n" + itemEntities + "\n");
                ShopCarAdpter shopCarAdpter = new ShopCarAdpter(getActivity(), cateGoryNames, itemEntities);
                shopRecy.setAdapter(shopCarAdpter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showHome(String str) {

    }

    /**
     * 解绑销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
