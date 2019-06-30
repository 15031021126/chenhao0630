package com.bawei.demo02.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.demo02.MyZlActivity;
import com.bawei.demo02.R;
import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.bean.UserEntity;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;
import com.bawei.demo02.net.HttpUntil;
import com.bawei.demo02.net.UserApiService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/6/12
 *@Time:14:43
 *@Description:${DESCRIPTION}
 * */
public class Frag05 extends Fragment implements IContract.IView, View.OnClickListener {

    @BindView(R.id.userback)
    ImageView userback;
    Unbinder unbinder;
    @BindView(R.id.nikename)
    TextView nikename;
    @BindView(R.id.userimage)
    ImageView userimage;
    @BindView(R.id.myinformation)
    TextView myinformation;
    @BindView(R.id.mycircle)
    TextView mycircle;
    @BindView(R.id.myfoot)
    TextView myfoot;
    @BindView(R.id.mywallet)
    TextView mywallet;
    @BindView(R.id.myaddress)
    TextView myaddress;
    private PersenterImpl persenter;
    private String sessionId;
    private String userId;
    private ArrayList<UserEntity> userEntities;
    private IdBeans idBeans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag05, container, false);
        persenter = new PersenterImpl();
        persenter.attach(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
        this.idBeans=idBeans;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //请求用户信息
        persenter.requestShop("http://172.17.8.100/", "small/user/verify/v1/getUserById", sessionId, userId);
        //点击事件
        myaddress.setOnClickListener(this);
        mycircle.setOnClickListener(this);
        myfoot.setOnClickListener(this);
        mywallet.setOnClickListener(this);
        myinformation.setOnClickListener(this);
        /**
         * 点击头像上传
         */
        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
         persenter = new PersenterImpl();
        persenter.attach(this);
        persenter.requestShop("http://172.17.8.100/", "small/user/verify/v1/getUserById", sessionId, userId);

    }

    @Override
    public void onPause() {
        super.onPause();
        persenter.dettach();
    }

    @Override
    public void shwoData(String s) {

    }

    /**
     * 用户数据
     *解析
     * @param s
     */
    @Override
    public void shwoShop(String s) {
        userEntities = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(s);
            JSONObject result = object.getJSONObject("result");
            String createTime = result.getString("createTime");//创建时间
            String headPic = result.getString("headPic");//头像
            String nickName = result.getString("nickName");//昵称
            String password = result.getString("password");//密码
            String phone = result.getString("phone");//手机号
            String sex = result.getString("sex");//性别
            String userId = result.getString("userId");//ids
            userEntities.add(new UserEntity(createTime, headPic, nickName, password, phone, sex, userId));
            Glide.with(getActivity()).load(headPic).into(userback);
            Glide.with(getActivity()).load(headPic).apply(new RequestOptions().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL)).into(userimage);
            nikename.setText(nickName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showHome(String str) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        persenter.dettach();
    }

    /**
     * 点击事件条目
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myinformation:
                Log.e("123", "onClick: " + userEntities);
                Toast.makeText(getActivity(), "我的资料", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MyZlActivity.class);
                EventBus.getDefault().postSticky(idBeans);
                startActivity(intent);
                break;
            case R.id.myfoot:
                Toast.makeText(getActivity(), "我的足记", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mywallet:
                Toast.makeText(getActivity(), "我的钱包", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myaddress:
                Toast.makeText(getActivity(), "我的地址", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mycircle:
                Toast.makeText(getActivity(), "我的圈子", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //头像上传
            case 0:

                try {
                    if (data != null) {
                        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
                            Uri selectedImage = data.getData();
                            File file = uri2File(selectedImage);
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            String picPath = cursor.getString(columnIndex);
                            //转码
                            String encode = URLEncoder.encode(picPath, "utf-8");

                            cursor.close();
                            if (picPath.equals("")) return;
                            Log.e("123", "onActivityResult: 路径:--" + encode);
                            //上传图片
                            addimg(encode, file);
                            //设置头像
                            userimage.setImageURI(selectedImage);
                            break;
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
     * 上传到接口
     *
     * @param picPath
     * @param file
     */
    private void addimg(String picPath, File file) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.e("123", "图片文件: " + file.toString());
            //创建文件请求对象
            //设置content-type类型
            RequestBody responseBody = RequestBody.create(MediaType.parse("image/*"), file);
            //多表单上传的工具类
            MultipartBody.Part image = MultipartBody.Part.createFormData("image", picPath, responseBody);
            HttpUntil.getUntil().getService(UserApiService.class).onepic("small/user/verify/v1/modifyHeadPic", sessionId, userId, image)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBody>() {
                        @Override
                        public void accept(ResponseBody responseBody) throws Exception {
                            Log.e("123", "上传图片: " + responseBody.string());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("123", "上传图片-: " + throwable);
                        }
                    });
        }
    }

    /**
     * user转换为file文件
     *
     * @param uri
     * @return
     */
    private File uri2File(Uri uri) {
        String img_path;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null,
                null, null);
        if (actualimagecursor == null) {
            img_path = uri.getPath();
        } else {
            int actual_image_column_index = actualimagecursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualimagecursor.moveToFirst();
            img_path = actualimagecursor
                    .getString(actual_image_column_index);
        }
        File file = new File(img_path);
        return file;
    }
}
