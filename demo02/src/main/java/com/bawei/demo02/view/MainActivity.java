package com.bawei.demo02.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.demo02.R;
import com.bawei.demo02.bean.IdBeans;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

public class MainActivity extends AppCompatActivity implements IContract.IView {
    private PersenterImpl persenter;
    private EditText edt1;
    private EditText edt2;
    private Button login;
    private Boolean bmm = true;
    @BindView(R.id.eye)
    ImageView eye;
    @BindView(R.id.bt_zc)
    TextView btzc;
    private Handler handler;
    private CheckBox remember_cb;
    private Boolean isChecked = false;
    private SharedPreferences one;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏应用程序标题栏    (能看到手机通知栏)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏            (手机通知栏也被隐藏)
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edt);
        handler = new Handler();
        remember_cb = findViewById(R.id.cb_remember);
        edt2 = findViewById(R.id.edt2);
        persenter = new PersenterImpl();
        persenter.attach(this);
        login = findViewById(R.id.login);
        one = getSharedPreferences("one", MODE_PRIVATE);
        edit = MainActivity.this.one.edit();
        boolean ischeck = one.getBoolean("ischeck", false);
        //记住密码
        if (ischeck) {
            String edtphone = one.getString("edtphone", "123");
            String password = one.getString("password", "123");
            edt1.setText(edtphone);
            edt2.setText(password);
            remember_cb.setChecked(ischeck);
        }
        //点击登录事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim1 = edt1.getText().toString().trim();
                String trim2 = edt2.getText().toString().trim();
                persenter.requestData("http://172.17.8.100/", "small/user/v1/login", trim1, trim2);
                if (remember_cb.isChecked()) {
                    persenter.requestData("http://172.17.8.100/", "small/user/v1/login", trim1, trim2);
                    edit.putBoolean("ischeck", true);
                    edit.putString("edtphone", trim1);
                    edit.putString("password", trim2);
                    edit.commit();
                } else {
                    edit.clear();
                    edit.commit();
                }
            }
        });
           /*
        注册事件
         */
        EventBus.getDefault().register(this);
        /*
         * 显示隐藏密码
         * */
        //绑定
        ButterKnife.bind(this);
    }

    /**
     * 密码显示隐藏
     *
     * @param view
     */
    @OnClick(R.id.eye)
    public void mmyc(View view) {
        bmm = !bmm;
        if (!bmm) {
            edt2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            eye.setImageResource(R.drawable.eyeselect);
        } else {
            edt2.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
            eye.setImageResource(R.drawable.login_icon_eye_n_xhdpi);
        }

    }

    @OnClick(R.id.bt_zc)
    public void setBtzc(View view) {
        startActivity(new Intent(this, ZcActivity.class));
        finish();
    }

    @Override
    public void shwoData(final String s) {
        Log.e("123", "shwoData: " + s);
        try {
            JSONObject object = new JSONObject(s);
            Log.e("123", "shwoData: " + object);
            String message = object.getString("message");
            Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
            Log.e("123", "shwoData: " + message);
            JSONObject result = object.getJSONObject("result");
            String sessionId = result.getString("sessionId");
            String userId = result.getString("userId");

            if (message.equals("登录成功")) {
                startActivity(new Intent(MainActivity.this, ThreeActivity.class));
                finish();
            }
            //Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();

            Log.e("123", "获取到了: ");
            //传值
            ArrayList<IdBeans> idBeans = new ArrayList<>();
            idBeans.add(new IdBeans(sessionId, userId));
            IdBeans idBeans1 = new IdBeans();
            idBeans1.setSessionId(sessionId);
            idBeans1.setUserId(userId);
            EventBus.getDefault().postSticky(idBeans1);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void shwoShop(String s) {

    }

    @Override
    public void showHome(String str) {

    }


    //接受值
    @Subscribe(threadMode = ThreadMode.PostThread, sticky = true)
    public void event(String name) {
        edt1.setText(name);
    }


}
