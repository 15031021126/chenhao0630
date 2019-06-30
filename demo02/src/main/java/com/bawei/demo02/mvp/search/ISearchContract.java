package com.bawei.demo02.mvp.search;

import com.bawei.demo02.mvp.HomeIcontract;
import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/25
 *@Time:14:18
 *@Description:${DESCRIPTION}
 * */public interface ISearchContract {

    interface ISearchView {
        void showSearch(String str);
    }

    interface ISearchMoldel {
        void getSearch( String url, CallBackStr callBackStr);
    }

    interface ISearchPersenter {
        void attache(ISearchView view);

        void dettache();

        void requestSearch(String url);
    }
}
