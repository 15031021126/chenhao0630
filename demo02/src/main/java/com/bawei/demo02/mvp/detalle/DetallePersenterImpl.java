package com.bawei.demo02.mvp.detalle;

import com.bawei.demo02.net.CallBackObject;
import com.bawei.demo02.net.CallBackStr;

/*
 *@Auther:陈浩
 *@Date: 2019/6/26
 *@Time:20:35
 *@Description:${DESCRIPTION}
 * */public class DetallePersenterImpl implements IDetallecontract.IDetallPersentre {
     private IDetallecontract.IDetallView view;

    private DetalleMoldeImpl detalleMolde;

    @Override
    public void attache(IDetallecontract.IDetallView view) {
        detalleMolde = new DetalleMoldeImpl();
        this.view=view;
    }

    @Override
    public void detache() {
        if(detalleMolde!=null){
            detalleMolde=null;
        }
        if(view!=null){
            view=null;
        }
    }

    @Override
    public void requestDetall(String userId, String sessionId,String id) {
    detalleMolde.doGetDetall(userId, sessionId,id, new CallBackStr() {
        @Override
        public void success(String obj) {
            view.showDetall(obj);
        }
    });
    }
}
