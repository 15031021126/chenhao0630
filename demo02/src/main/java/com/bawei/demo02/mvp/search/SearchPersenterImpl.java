package com.bawei.demo02.mvp.search;

import com.bawei.demo02.net.CallBackStr;
import com.bawei.demo02.net.HttpUntil;

/*
 *@Auther:陈浩
 *@Date: 2019/6/25
 *@Time:14:24
 *@Description:${DESCRIPTION}
 * */public class SearchPersenterImpl implements ISearchContract.ISearchPersenter {

    private SearchModelImpl model;
    private ISearchContract.ISearchView view;

    @Override
    public void attache(ISearchContract.ISearchView view) {
        model = new SearchModelImpl();
        this.view=view;
    }

    @Override
    public void dettache() {
        if(view!=null){
            view=null;
        }
        if(model!=null){
            model =null;
        }

    }

    @Override
    public void requestSearch(String url) {
        model.getSearch(url, new CallBackStr() {
            @Override
            public void success(String s) {
                view.showSearch(s);
            }
        });
    }
}
