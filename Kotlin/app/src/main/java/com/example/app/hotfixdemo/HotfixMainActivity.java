package com.example.app.hotfixdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.app.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

public class HotfixMainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private Button showTextBtn,loadPluginBtn,removePluginBtn,killSelfBtn;
    File apk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotfix);

        textView = findViewById(R.id.textView);
        showTextBtn = findViewById(R.id.showTextBtn);
        loadPluginBtn = findViewById(R.id.loadPluginBtn);
        removePluginBtn = findViewById(R.id.removePluginBtn);
        killSelfBtn = findViewById(R.id.killSelfBtn);

        apk = new File(getCacheDir() + "/hotfix.dex");

        showTextBtn.setOnClickListener(this);
        loadPluginBtn.setOnClickListener(this);
        removePluginBtn.setOnClickListener(this);
        killSelfBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.showTextBtn:
                Title title = new Title();
                textView.setText(title.getTitle());
                break;
            case R.id.loadPluginBtn:
//                loadNetPlugin(v);
                loadAssetPlugin(v);
                break;
            case R.id.removePluginBtn:
                if (apk.exists()) {
                    apk.delete();
                }
                break;
            case R.id.killSelfBtn:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;
        }
    }
    private void loadAssetPlugin(View v) {
        File apk = new File(getCacheDir()+"/hotfix.dex");
        if (!apk.exists()){
            Toast.makeText(HotfixMainActivity.this, "补丁加载成功", Toast.LENGTH_SHORT).show();
            try(Source source = Okio.source(getAssets().open("apk/hotfix.dex"));
                BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
                sink.writeAll(source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void loadNetPlugin(View v) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("https://api.hencoder.com/patch/upload/hotfix.dex")
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HotfixMainActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        try (BufferedSink sink = Okio.buffer(Okio.sink(apk))) {
                            sink.write(response.body().bytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        v.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HotfixMainActivity.this, "补丁加载成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }
}