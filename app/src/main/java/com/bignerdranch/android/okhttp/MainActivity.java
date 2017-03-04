package com.bignerdranch.android.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLoginButton;
    private Button mCookieView;
    private TextView mCookieTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLoginButton = (Button) findViewById(R.id.Login_Button);
        mCookieView = (Button) findViewById(R.id.Cookie_Button);
        mCookieTextView = (TextView) findViewById(R.id.OKhttp_text);
        mLoginButton.setOnClickListener(this);
        mCookieView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_Button:
                getRequest();
                break;
            case R.id.Cookie_Button:
                postRequset();
                break;
            default:
                break;

        }
    }

    public void getRequest() {
        final Request request = new Request.Builder()
                .url("https://www.baidu.com")
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCookieTextView.setText(res);
                    }
                });

            }


        });

    }
    private void postRequset()
    {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder fromBodyBuild = new FormBody.Builder();
        fromBodyBuild.add("mb","13571750862");
        fromBodyBuild.add("pwp","23333");
        Request request = new Request.Builder().url("https://www.baidu.com").post(fromBodyBuild.build()).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mCookieTextView.setText(res);
                    }
                });


            }
        });
    }
}