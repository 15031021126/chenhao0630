package com.bawei.chenhao0603.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bawei.chenhao0603.R;
import com.bawei.chenhao0603.bean.Bean;
import com.bawei.chenhao0603.holder.Recy01Holder;
import com.bawei.chenhao0603.holder.Recy022Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:14:18
 *@Description:${多条目适配器}
 * */public class SomeAdpter extends RecyclerView.Adapter {
    private ArrayList<Bean> beans;
    private Context context;

    public SomeAdpter(ArrayList<Bean> beans, Context context) {
        this.beans = beans;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        switch (getItemViewType(i)) {
            case 0:
                return new Recy01Holder(from.inflate(R.layout.lv1, viewGroup, false));
            case 1:
                return new Recy022Holder(from.inflate(R.layout.lv2, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                ((Recy01Holder) viewHolder).tvv1.setText(beans.get(i).getIntro());
                if(i+2<beans.size()) {
                    //配置占位和错误图及外部存储路径。
                    Glide.with(context).load(beans.get(i).getU()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.black_background).placeholder(R.drawable.ic_launcher_background)).into(((Recy01Holder) viewHolder).img11);
                    Glide.with(context).load(beans.get(i + 1).getU()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.black_background).placeholder(R.drawable.ic_launcher_background)).into(((Recy01Holder) viewHolder).img12);
                    Glide.with(context).load(beans.get(i + 2).getU()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.black_background).placeholder(R.drawable.ic_launcher_background)).into(((Recy01Holder) viewHolder).img13);
                }  break;
            case 1:
                ((Recy022Holder) viewHolder).tv21.setText(beans.get(beans.size()-i).getIntro());
                Glide.with(context).load(beans.get(beans.size()-i).getU()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.black_background).placeholder(R.drawable.ic_launcher_background).circleCrop()).into(((Recy022Holder) viewHolder).img022);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }
}
