package com.bawei.lxewm;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CaptureResult;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt;
    private ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        edt = findViewById(R.id.edt);
        imgview = findViewById(R.id.img);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.bt2:
                Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                intent1.setType("image/*");
                startActivityForResult(intent1, 2);
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                String trim = edt.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap image = CodeUtils.createImage(trim, 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imgview.setImageBitmap(image);
                break;
            case R.id.bt5:
                String trim2 = edt.getText().toString().trim();
                if (TextUtils.isEmpty(trim2)) {
                    Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bitmap image2 = CodeUtils.createImage(trim2, 500, 500, null);
                imgview.setImageBitmap(image2);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (null != data) {
                    Bundle extras = data.getExtras();
                    if (extras == null) {
                        return;
                    }
                    if (extras.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String string = extras.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "" + string, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
                if (data != null) {
                    Uri uri = data.getData();
                    //获得内容解析器
                    ContentResolver resolver = getContentResolver();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
                        CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                            @Override
                            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                                Toast.makeText(MainActivity.this, "结果:" + result, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onAnalyzeFailed() {
                                Toast.makeText(MainActivity.this, "失败！！！！", Toast.LENGTH_SHORT).show();
                            }
                        });
                        if (bitmap != null) {
                            bitmap.recycle();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                break;
            case 3:
                break;
        }
    }
}
