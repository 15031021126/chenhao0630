package com.bawei.demo02;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.bean.UserEntity;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;
import com.bawei.demo02.net.HttpUntil;
import com.bawei.demo02.net.UserApiService;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MyZlActivity extends AppCompatActivity implements IContract.IView {


    @BindView(R.id.my_img)
    ImageView myImg;
    @BindView(R.id.my_name)
    TextView myName;
    private ArrayList<UserEntity> userEntities;
    private String sessionId;
    private String userId;
    private PersenterImpl persenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zl);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        persenter = new PersenterImpl();
        persenter.attach(this);
        persenter.requestShop("http://172.17.8.100/", "small/user/verify/v1/getUserById", sessionId, userId);

        myImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
        myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Subscribe(sticky = true)
    public void even(IdBeans idBeans) {
        sessionId = idBeans.getSessionId();
        userId = idBeans.getUserId();
    }

    @Override
    public void shwoData(String s) {

    }

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
            myName.setText(nickName);
            Glide.with(this).load(headPic).apply(new RequestOptions().circleCrop()).into(myImg);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showHome(String str) {

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
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
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
                            myImg.setImageURI(selectedImage);
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
        Cursor actualimagecursor = managedQuery(uri, proj, null,
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
