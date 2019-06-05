package com.bawei.chenhao0603.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bawei.chenhao0603.R;
import com.bawei.chenhao0603.js.JavaScriptInter;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:13:45
 *@Description:${交互}
 * */public class Frag02 extends Fragment {

    private WebView web;
    private String a;
    private String b;
    private SharedPreferences one;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag02, container, false);
        view.findViewById(R.id.fh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.loadUrl("https://ming723.github.io/moni/abnerming.html");
                //Toast.makeText(getActivity(), "aa", Toast.LENGTH_SHORT).show();
            }
        });
        //设置webview
        web = view.findViewById(R.id.web);
        web.setWebViewClient(new WebViewClient());
        web.setWebChromeClient(new WebChromeClient());
        //配置
        WebSettings settings = web.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptEnabled(true);
        //加载
        web.loadUrl("https://ming723.github.io/moni/abnerming.html");
        web.addJavascriptInterface(new JavaScriptInter(getActivity()), "android");
        //读取sp
        SharedPreferences e = getActivity().getSharedPreferences("e", Context.MODE_PRIVATE);
        String mm = e.getString("mm", "888888");
        String zh = e.getString("zh", "666666");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            web.evaluateJavascript("createUser("+mm+zh+")",null);
        }
        return view;
    }
}
