package com.bawei.demo02.net;

import com.bawei.demo02.bean.DetalleEntity;
import com.bawei.demo02.bean.Pedido;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

/*
 *@Auther:陈浩
 *@Date: 2019/6/12
 *@Time:20:23
 *@Description:${DESCRIPTION}
 * */
public interface UserApiService {

    @POST//登录
    @FormUrlEncoded
    Observable<ResponseBody> login(@Url String url, @Field("phone") String phone, @Field("pwd") String pwd);

    @GET
    Observable<ResponseBody> getShop(@Url String url, @Header("sessionId")String sessionId,@Header("userId")String userId);
    /**
     * 单图
     *
     * @param file
     * @return
     */
    @POST
    @Multipart
    Observable<ResponseBody> onepic(@Url String url, @Header("sessionId") String sessionId, @Header("userId") String userId, @Part MultipartBody.Part file);

    /**
     * 多图
     *
     * @return
     */
//    @POST()
//    @Multipart
//    Observable<这> uploadPics(, @Part List<MultipartBody.Part> file);
    @POST
    @Multipart
    Observable<ResponseBody> somePic(@Url String url, @Header("sessionId") String sessionId, @Header("userId") String userId, List<MultipartBody.Part> fils);

    /**
     * 订单查询
     * @param url
     * @param sessionId
     * @param userId
     * @param status
     * @param page
     * @param count
     * @return
     */
    @GET
    Observable<Pedido> getDD(@Url String url, @Header("sessionId") String sessionId, @Header("userId") String userId, @Query("status")String status, @Query("page")String page, @Query("count")String count);

    /**
     * 详情
     * @param userId
     * @param sessionId
     * @return
     */
    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<ResponseBody> getDetalle(@Header("userId") String userId, @Header("sessionId") String sessionId,@Query("commodityId")String id);
    /**
     * 删除订单
     * @param
     * @return
     */
    @DELETE("small/order/verify/v1/deleteOrder")
    Observable<ResponseBody> deleteOrder(@Header("sessionId") String sessionId, @Header("userId") String userId,
                  @Query("orderId") String orderId);

    //    @POST//请求方式
//    @FormUrlEncoded
//    Call<String> login(@Url String url, @FieldMap HashMap<String, String> params);
//
//    @GET
////请求方式
//    Call<String> getUserInfo(@Url String url, @Header("userId") String uid, @Header("sessionId") String sessionid);
//
//    @GET
////请求方式
//    Call<String> searchProducts(@Url String url, @Query("keyword") String key, @Query("count") int count, @Query("page") int page);
//
//    @GET
////请求方式
//    Call<String> searchProducts(@Url String url, @QueryMap HashMap<String, Object> params);
//
//
//    //传输raw数据
//    @POST//请求方式
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
////需要添加头
//    Call<String> login(@Url String url, @Body String json);
}
