package com.bawei.chenhao0603.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.chenhao0603.R;

/*
 *@Auther:陈浩
 *@Date: 2019/6/3
 *@Time:14:54
 *@Description:${自定义标题栏}
 * */public class MyView extends LinearLayout {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyViews);
        int color = typedArray.getColor(R.styleable.MyViews_bcolor, Color.RED);
        String string = typedArray.getString(R.styleable.MyViews_texta);
        typedArray.recycle();
        setBackgroundColor(color);//背景颜色
        //设置自定义标题
        View inflate = LayoutInflater.from(context).inflate(R.layout.myview, this, true);
        TextView tv = inflate.findViewById(R.id.jh);
        tv.setText(string);
    }
    //测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
