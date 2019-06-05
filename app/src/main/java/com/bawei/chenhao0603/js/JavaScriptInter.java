package com.bawei.chenhao0603.js;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:15:17
 *@Description:${js方法}
 * */public class JavaScriptInter {
    private Context context;

    public JavaScriptInter(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public String login(String user, String pass) {
        //sp
        SharedPreferences one = context.getSharedPreferences("one", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = one.edit();
        edit.putBoolean("boo", true);
        edit.commit();

        Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor e = context.getSharedPreferences("e", Context.MODE_PRIVATE).edit();
        e.putString("mm", user);
        e.putString("zh", pass);
        e.commit();
        return user + pass;
    }
}
