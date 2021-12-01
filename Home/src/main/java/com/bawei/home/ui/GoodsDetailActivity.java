package com.bawei.home.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.home.R;
import com.bawei.home.entity.Goods_sql;
import com.bawei.resource.GoodsSql;
import com.blankj.utilcode.util.ToastUtils;


import java.util.ArrayList;


public class GoodsDetailActivity extends AppCompatActivity {

    private WebView goodsWeb;
    ArrayList<String> imgs;
    String[] strings;
    String name;
    String price;
    SQLiteDatabase db;
    int id;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initView();

        GoodsSql goodsSql = new GoodsSql(this, "goods.db", null, 1);

        db = goodsSql.getWritableDatabase();


        Intent intent = getIntent();

        imgs = intent.getStringArrayListExtra("imgs");

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        int position = intent.getIntExtra("position", 0);

        id = intent.getIntExtra("id", 0);


        strings = new String[4];


        for (int i = 0; i < imgs.size(); i++) {


            strings[i] = imgs.get(i);
        }


        goodsWeb.loadUrl("file:/android_asset/detail.html");

        WebSettings settings = goodsWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);

        goodsWeb.addJavascriptInterface(this,"MyAndroid");

        goodsWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(GoodsDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                result.cancel();
                return true;//super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });


    }

    @JavascriptInterface
    public String getimg(){
        return strings[0];
    }

    @JavascriptInterface
    public String getname(){
        return name;
    }

    @JavascriptInterface
    public String getprice(){
        return price;
    }

    @JavascriptInterface
    public void addShoping(){
        ToastUtils.showLong("加入购物车");
        db.execSQL("insert into goods values(?,?,?,?)",new Object[]{name,strings[0],price,id});

        Cursor cursor = db.rawQuery("select * from goods", null);

    }

    private void initView() {
        goodsWeb = findViewById(R.id.goods_web);
    }
}