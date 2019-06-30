package com.bawei.demo02.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.demo02.R;
import com.bawei.demo02.mvp.IContract;
import com.bawei.demo02.mvp.PersenterImpl;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class ZcActivity extends AppCompatActivity implements IContract.IView {

    private PersenterImpl persenter;
    private EditText editText4;
    private EditText editText3;
    private Button regbt;
    private ImageView eye2;
    private Boolean bmm = true;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_zc);
        editText3 = findViewById(R.id.edt3);
        editText4 = findViewById(R.id.edt4);
        eye2 = findViewById(R.id.eye2);
        regbt = findViewById(R.id.reg);
        persenter = new PersenterImpl();
        persenter.attach(this);

        regbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim1 = editText3.getText().toString().trim();
                String trim2 = editText4.getText().toString().trim();
                persenter.requestData("http://172.17.8.100/","small/user/v1/register", trim1, trim2);
            }
        });
        findViewById(R.id.bt_zhyou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZcActivity.this, MainActivity.class);
                startActivity(intent);
                    finish();
            }
        });
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bmm = !bmm;
                if (!bmm) {
                    editText4.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye2.setImageResource(R.drawable.eyeselect);
                } else {
                    editText4.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏
                    eye2.setImageResource(R.drawable.login_icon_eye_n_xhdpi);
                }
            }
        });

    }


    @Override
    public void shwoData(String s) {
        try {
            JSONObject object = new JSONObject(s);
            Log.e("123", "shwoData: " + object);
            final String message = object.getString("message");
            Log.e("123", "shwoData: " + message);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(ZcActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                    if (message.equals("注册成功")) {
                        Intent intent = new Intent(ZcActivity.this, MainActivity.class);
                        //传值
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().postSticky(editText3.getText().toString());
                            }
                        }).start();
                        startActivity(intent);
                        finish();
                    }
                }
            });

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

    /**
     * 回收资源
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
