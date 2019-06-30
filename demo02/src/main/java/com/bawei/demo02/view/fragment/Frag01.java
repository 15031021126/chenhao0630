package com.bawei.demo02.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.demo02.DetalleActivity;
import com.bawei.demo02.MyItemClick;
import com.bawei.demo02.R;
import com.bawei.demo02.adpter.SearrchAdpter;
import com.bawei.demo02.adpter.home.HomeAdpter;
import com.bawei.demo02.bean.HomeBanner;
import com.bawei.demo02.bean.HomeNei;
import com.bawei.demo02.bean.HomeWai;
import com.bawei.demo02.bean.SearchEntity;
import com.bawei.demo02.mvp.HomeIcontract;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;
import com.bawei.demo02.mvp.search.ISearchContract;
import com.bawei.demo02.mvp.search.SearchPersenterImpl;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/*
 *@Auther:陈浩
 *@Date: 2019/6/12
 *@Time:14:43
 *@Description:${DESCRIPTION}
 * */
public class Frag01 extends Fragment implements IContract.IView, HomeIcontract.IHomeView, ISearchContract.ISearchView {
    @BindView(R.id.homerecy)
    RecyclerView homerecy;
    @BindView(R.id.san)
    ImageView san;
    @BindView(R.id.homesearch)
    ImageView homesearch;
    @BindView(R.id.edt_search)
    EditText edtsearch;
    @BindView(R.id.qx_home)
    TextView homeqx;
    Unbinder unbinder;
    private PersenterImpl persenter;
    private ArrayList<HomeBanner> banners;
    private ArrayList<HomeWai> homeWais;
    private ArrayList<HomeNei> homeNeis;
    private HomeAdpter homeAdpter;
    private SearchPersenterImpl searchPersenter;
    private ArrayList<SearchEntity> searchEntities;
    private SearrchAdpter searrchAdpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag01, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        persenter = new PersenterImpl();
        persenter.attach(this);
        persenter.requestHome("http://172.17.8.100/", "small/commodity/v1/commodityList");
        persenter.requestShop("http://172.17.8.100/", "small/commodity/v1/bannerShow", null, null);
        homerecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchPersenter = new SearchPersenterImpl();
        searchPersenter.attache(this);

        //分类点击事件
        san.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "topwindwos", Toast.LENGTH_SHORT).show();
            }
        });
        homesearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                //文本框显示
                edtsearch.setVisibility(View.VISIBLE);
                //取消按钮显示
                homeqx.setVisibility(View.VISIBLE);
                //隐藏分类
                san.setVisibility(View.GONE);
                //清除适配器
                homerecy.setAdapter(null);
                //不为空设置适配器数据
                if (edtsearch.getText().toString().length() != 0) {
                    //请求数据
                    homerecy.setAdapter(null);
                    homerecy.setVisibility(View.VISIBLE);
                    String trim = edtsearch.getText().toString().trim();
                    searchPersenter.requestSearch("/small/commodity/v1/findCommodityByKeyword?keyword="+ URLEncoder.encode(trim) +"&page=1&count=10");
                    //设置适配器
                    homerecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    homerecy.setAdapter(searrchAdpter);
                }
            }
        });
        //取消监听
        homeqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //适配器清除
                homerecy.setAdapter(null);
                //取消隐藏
                homeqx.setVisibility(View.GONE);
                //文本框隐藏
                edtsearch.setText("");
                edtsearch.setVisibility(View.GONE);
                //显示分类
                san.setVisibility(View.VISIBLE);
                //适配器显示
                homerecy.setVisibility(View.VISIBLE);
                //设置适配器
                homerecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                homerecy.setAdapter(homeAdpter);
            }
        });
    }

    /**
     * 销毁
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void shwoData(String s) {

    }

    @Override
    public void shwoShop(String s) {
        //轮播数据
        banners = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(s);
            JSONArray result = object.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                JSONObject o = (JSONObject) result.get(i);
                String imageUrl = o.getString("imageUrl");
                String jumpUrl = o.getString("jumpUrl");
                String rank = o.getString("rank");
                String title = o.getString("title");
                banners.add(new HomeBanner(imageUrl, jumpUrl, rank, title));
                //设值配器
                homeAdpter = new HomeAdpter(getActivity(), banners, homeWais, homeNeis);
                homerecy.setAdapter(homeAdpter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showHome(String str) {
        homeWais = new ArrayList<>();
        homeNeis = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(str);
            JSONObject result = object.getJSONObject("result");
            JSONObject mlss = result.getJSONObject("mlss");
            String mlssname = mlss.getString("name");
            JSONArray commodityList = mlss.getJSONArray("commodityList");
            for (int i = 0; i < commodityList.length(); i++) {
                JSONObject o = (JSONObject) commodityList.get(i);
                String commodityId = o.getString("commodityId");
                String commodityName = o.getString("commodityName");
                String masterPic = o.getString("masterPic");
                String price = o.getString("price");
                String saleNum = o.getString("saleNum");
                homeNeis.add(new HomeNei(commodityId, commodityName, masterPic, price, null));
            }
            JSONObject pzsh = result.getJSONObject("pzsh");
            String pzshname = pzsh.getString("name");
            JSONArray commodityList2 = pzsh.getJSONArray("commodityList");
            for (int i = 0; i < commodityList2.length(); i++) {
                JSONObject o = (JSONObject) commodityList2.get(i);
                String commodityId = o.getString("commodityId");
                String commodityName = o.getString("commodityName");
                String masterPic = o.getString("masterPic");
                String price = o.getString("price");
                String saleNum = o.getString("saleNum");
                homeNeis.add(new HomeNei(commodityId, commodityName, masterPic, price, null));

            }
            JSONObject rxxp = result.getJSONObject("rxxp");
            String rxxpname = rxxp.getString("name");
            JSONArray commodityList3 = rxxp.getJSONArray("commodityList");
            for (int i = 0; i < commodityList3.length(); i++) {
                JSONObject o = (JSONObject) commodityList3.get(i);
                String commodityId = o.getString("commodityId");
                String commodityName = o.getString("commodityName");
                String masterPic = o.getString("masterPic");
                String price = o.getString("price");
                String saleNum = o.getString("saleNum");
                homeNeis.add(new HomeNei(commodityId, commodityName, masterPic, price, saleNum));
            }
            homeWais.add(new HomeWai(mlssname, pzshname, rxxpname));
            homeAdpter = new HomeAdpter(getActivity(), banners, homeWais, homeNeis);
            homerecy.setAdapter(homeAdpter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showSearch(String str) {
        searchEntities = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(str);
            JSONArray result = object.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                JSONObject o = (JSONObject) result.get(i);
                String commodityId = o.getString("commodityId");
                String commodityName = o.getString("commodityName");
                String masterPic = o.getString("masterPic");
                String price = o.getString("price");
                String saleNum = o.getString("saleNum");
                searchEntities.add(new SearchEntity(commodityId,commodityName,masterPic,price,saleNum));
            }
            Log.e("321", "showSearch: "+searchEntities );
            searrchAdpter = new SearrchAdpter(getActivity(),searchEntities);
            homerecy.setAdapter(searrchAdpter);
            //搜索列表点击事件跳转详情
            searrchAdpter.setclick(new MyItemClick() {
                @Override
                public void onclick(String id) {
                    Intent intent = new Intent(getActivity(), DetalleActivity.class);
                    EventBus.getDefault().postSticky(id);
                    startActivity(intent);
                }

                @Override
                public void onLongclick(String id) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
