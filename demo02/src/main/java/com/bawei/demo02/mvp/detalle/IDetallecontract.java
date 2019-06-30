package com.bawei.demo02.mvp.detalle;

import com.bawei.demo02.net.CallBackObject;
import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/26
 *@Time:20:14
 *@Description:${DESCRIPTION}
 * */public interface IDetallecontract {
    interface IDetallView {
        void showDetall(String str);
    }

    interface IDetallModel {
        void doGetDetall(String userId, String sessionId, String id,CallBackStr callBackObject);
    }

    interface IDetallPersentre {
        void attache(IDetallecontract.IDetallView view);
        void detache();
        void requestDetall(String userId, String sessionId,String id);
    }
}
