package com.bawei.demo02.view.fragment;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.demo02.MyItemClick;
import com.bawei.demo02.R;
import com.bawei.demo02.adpter.dd.Dd1Adpter;
import com.bawei.demo02.adpter.dd.SomeAdpter;
import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.bean.Pedido;
import com.bawei.demo02.mvp.dd.DdIcontract;
import com.bawei.demo02.mvp.dd.DdPsersenterImpl;
import com.bawei.demo02.net.Api;
import com.bawei.demo02.test.MultiBeanAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

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
public class Frag04 extends Fragment implements View.OnClickListener, DdIcontract.IHomeView {
    @BindView(R.id.dd1)
    ImageView dd1;
    @BindView(R.id.dd2)
    ImageView dd2;
    @BindView(R.id.dd3)
    ImageView dd3;
    @BindView(R.id.dd4)
    ImageView dd4;
    @BindView(R.id.dd5)
    ImageView dd5;
    @BindView(R.id.ddrecy)
    XRecyclerView ddrecy;
    Unbinder unbinder;
    private DdPsersenterImpl psersenter;
    private String baseurl = Api.baseuser;
    private String url = "small/order/verify/v1/findOrderListByStatus";
    private String sessionId;
    private String userId;
    private ArrayList<Pedido.OrderListBean> orderListBeans;
    private Dd1Adpter dd1Adpter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag04, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //默认展示全部
        psersenter = new DdPsersenterImpl();
        psersenter.attache(this);
        psersenter.requestDd(baseurl, url, userId, sessionId, "0", "1", "5");
        //点击回调
        dd1.setOnClickListener(this);
        dd2.setOnClickListener(this);
        dd3.setOnClickListener(this);
        dd4.setOnClickListener(this);
        dd5.setOnClickListener(this);
        //设置布局管理器
        ddrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        ddrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                psersenter.requestDd(baseurl, url, sessionId, userId, "0", "1", "5");
                dd1Adpter.notifyDataSetChanged();
                ddrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                psersenter.requestDd(baseurl, url, sessionId, userId, "0", "1", "5");
                dd1Adpter.notifyDataSetChanged();
                ddrecy.loadMoreComplete();
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
        psersenter.dettache();
    }


    /**
     * 注册evecbus传值
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    /**
     * d点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dd1:
                psersenter.requestDd(baseurl, url, sessionId, userId, "0", "1", "5");
                break;
            case R.id.dd2:
                break;
            case R.id.dd3:
                break;
            case R.id.dd4:
                break;
            case R.id.dd5:
                Toast.makeText(getActivity(), "" + 5, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 接受ids
     *
     * @param idBeans
     */
    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void even(IdBeans idBeans) {
        sessionId = idBeans.getSessionId();
        userId = idBeans.getUserId();
    }

    @Override
    public void showDd(Object str) {
        orderListBeans = (ArrayList<Pedido.OrderListBean>) str;
        //Log.e("123", "showDd: "+ orderListBeans);
        dd1Adpter = new Dd1Adpter(getActivity(), orderListBeans);
        if(orderListBeans.size()!=0){
            ddrecy.setAdapter(dd1Adpter);
        }

        /**
         * 点击事件
         */
        dd1Adpter.setClick(new MyItemClick() {
            @Override
            public void onclick(String id) {
                psersenter.requestDelect(baseurl, url, sessionId, userId, id);
                dd1Adpter.notifyDataSetChanged();
                Log.e("123", "删除---------------------------: " );
            }
            @Override
            public void onLongclick(String id) {
            }
        });
        /**
         * 适配器
         */
        Log.e("31", "sho----------****wDd: ");
    }

    @Override
    public void showDdDelect(Object str) {
        try {
            JSONObject object = new JSONObject((String) str);
            String message = object.getString("message");
            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
            if(message.equals("删除成功")&&orderListBeans.size()!=0){
                psersenter.requestDd(baseurl, url, sessionId, userId, "0", "1", "5");
                dd1Adpter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
