package com.bawei.chenhao0603.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.chenhao0603.R;
import com.bawei.chenhao0603.adpter.SomeAdpter;
import com.bawei.chenhao0603.bean.Bean;
import com.bawei.chenhao0603.mvp.Ionecontract;
import com.bawei.chenhao0603.mvp.OnePersenterImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:45
 *@Description:${主页多条目}
 * */public class Frag01 extends Fragment implements Ionecontract.IoneView {

    private RecyclerView recy;
    private OnePersenterImpl persenter;
    private ArrayList<Bean> beans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag01, container, false);
        recy = view.findViewById(R.id.recy);
        persenter = new OnePersenterImpl();
        persenter.atttach(this);
        persenter.requestData("https://feed.mix.sina.com.cn/api/roll/get?pageid=153&lid=2509&k=&num=20&page=1");
        return view;
    }

    @Override
    public void showData(String s) {
        //Log.e("123", "showData: "+s );
        //解析
        try {
            JSONObject object = new JSONObject(s);
            JSONObject result = object.getJSONObject("result");
            JSONArray data = result.getJSONArray("data");
            beans = new ArrayList<>();
            for (int i = 0; i < data.length(); i++) {
                JSONObject o = (JSONObject) data.get(i);
                String intro = o.getString("intro");
                String title = o.getString("title");
                JSONArray images = o.getJSONArray("images");
                for (int j = 0; j < images.length(); j++) {
                    JSONObject o1 = (JSONObject) images.get(j);
                    String u = o1.getString("u");
                    beans.add(new Bean(intro, u, title));
                }
            }
            Log.e("123", "解析完成");
            recy.setLayoutManager(new LinearLayoutManager(getActivity()));
            recy.setAdapter(new SomeAdpter(beans, getActivity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        persenter.detach();
    }
}
