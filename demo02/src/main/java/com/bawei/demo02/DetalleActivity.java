package com.bawei.demo02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.demo02.bean.DetalleEntity;
import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.bean.Pic;
import com.bawei.demo02.mvp.detalle.DetallePersenterImpl;
import com.bawei.demo02.mvp.detalle.IDetallecontract;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 详情页
 */
public class DetalleActivity extends AppCompatActivity implements IDetallecontract.IDetallView {


    @BindView(R.id.detallexbanner)
    XBanner detallexbanner;
    @BindView(R.id.detalleprice)
    TextView detalleprice;
    @BindView(R.id.detalleys)
    TextView detalleys;
    @BindView(R.id.detallname)
    TextView detallname;
    @BindView(R.id.detallweight)
    TextView detallweight;
    @BindView(R.id.detallwebview)
    WebView detallwebview;
    private DetallePersenterImpl detallePersenter;
    private String sessionId;
    private String userId;
    private String i = "";
    private ArrayList<DetalleEntity> detalleEntities;
    private ArrayList<Pic> pics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        detallePersenter = new DetallePersenterImpl();
        detallePersenter.attache(this);
        Log.e("123", "onCreate: " + sessionId);
        detallePersenter.requestDetall(userId, sessionId, i);
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

    @Subscribe(sticky = true)
    public void evenid(String id) {
        i = id;
    }

    @Override
    public void showDetall(String str) {
        detalleEntities = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(str);
            JSONObject result = object.getJSONObject("result");
            String categoryId = result.getString("categoryId");
            String categoryName = result.getString("categoryName");
            String commentNum = result.getString("commentNum");
            String commodityId = result.getString("commodityId");
            String commodityName = result.getString("commodityName");
            String describe = result.getString("describe");
            String details = result.getString("details");
            String picture = result.getString("picture");
            String price = result.getString("price");
            String saleNum = result.getString("saleNum");
            String stock = result.getString("stock");
            String weight = result.getString("weight");
            detalleEntities.add(new DetalleEntity(categoryId, categoryName, commentNum, commodityId, commodityName, describe, details, picture, price, saleNum, stock, weight));
            pics=new ArrayList<>();
            Log.e("123", "showDetall: "+picture+"\n"+pics );
            String[] split = picture.split(",");
            for (int j = 0; j < split.length; j++) {
                pics.add(new Pic(split[j]));
            }

            detallexbanner.setBannerData(pics);
            detallexbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(DetalleActivity.this).load(pics.get(position).getXBannerUrl()).into((ImageView) view);
                }
            });
            detalleprice.setText("￥"+price);
            detallname.setText(commodityName);
            detalleys.setText("已售"+saleNum+"件");
            detallweight.setText("重量\t"+weight+"kg");
            WebSettings settings = detallwebview.getSettings();
            settings.setJavaScriptEnabled(true);
            Log.e("123", "showDetall: "+ details);
            detallwebview.loadData(details,"text/html",null);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
