package com.bawei.home.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bawei.home.R;
import com.bawei.home.entity.Goods_sql;
import com.bawei.home.pay.PayResult;
import com.bawei.home.pay.util.OrderInfoUtil2_0;
import com.bawei.resource.Order_goods;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity {

    private ImageView imgOrder;
    private TextView numOrder;
    private TextView paySumOrder;
    private Button pay;
    SQLiteDatabase db;
    int index;

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000118622185";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     * pkcs8 格式的商户私钥。
     * <p>
     * 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * RSA2_PRIVATE。
     * <p>
     * 建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMFXrLLuorvFZYNR1I2AFqlvdbmH4/tix/NsruBGyfUkvsIMtq793fGIiCVbUrb+hQiEfaospaBGqch7wxn33mKY65elY7GXyY/gAmp8VekCNLMtuP964RBoUV7TLlA0QuKsA6tlfzbpmCmIMAVhUfaxbbJ3PRu4idgYwmmCOy3U4mJGsghBdJLWsypzUb0tOxPBZpg0c3j6WYiAOFzRyT+lifouVasznPq2x89iqFB50utg49vWCgbeNBBrpLZjOroF2Ofy2ogIqRFkYmy00leDby/b9XZwiwOA71x8UU1m3B+2GT+UG2oUHvYymjewQRevbb8mChjGIe2Pe4iYoZAgMBAAECggEAC/UBaDp27ojC7gCKoV8er9R3BLB9hj6siadJpwtloivKIzuhYV1cGODlNgrOzghdUhJv36+pigl6eCX14F52xqCheB+DIMRKKFz18la7uBihipequh0wFG/0CC0Y/QdMWIx5oAlUdIjqPYV7GRo/71CawNYbQrm5LYiTR/AQjAHKs3xt50Vr6hqz3hik1GoLCH89cgyJDXFCeeeaAnk4QH2BilnE8EMNBC5drST6SI1h2zL68nbGKvyRaIXrHaHfoN9wb0h3NPvSwEY6lKjzNWpCYNGymHMLHBQLFu05hmSRiAwdwUqhQM8Qpw2cb+AVWEaStDPJZBsD35r2MKZ7oQKBgQDtZQMUrRKGhrofg/Ochz8XF+nG4whe2Upg5utlRDNZLo9DKEoLhGMICbhmtUpIQpeYHOFARLvwXopog74j/hSEOs2sbEw2hx2rsOH2Xi1dUxOQ9dCTMClG+MxZBgyPIY7YJW+Pvdd1hB7Ku6thjLdY/4uC8G8AE1n6TBIkixN+9QKBgQCXEBDkDm3Zou/dWzSynfbipjba8/JkHP9v7r9+Zsu0H95/XnvBTnz75jHobB1nLX5Hfcte4oC5H5+7Tqiddi+r1kb2Yt4BlvNnPvso74mISdwZoRjhXooznaYkjDo6ortee/qTcUCGkjtfxAsYG1Ec0jyQn7TpZIif5pmHUdugFQKBgGn2jaJ8Amnj105b8rBquiGPvfzVuBXGPen1CRuBjr3/B1l9sVdkt2OOGy5VO5v3hX0TXwP16kHHoVY3f/bG4deH++g7yuUH+Jl7cdErhmpCu9gXsQsSE0qnoWcXSSHWY9FdHGf34CWHXMs3a682RVzHbDl9Ir7Q5HfR3sNLBN/FAoGAU+zPFYSWrwUA8h/LxThRg1YIv9gr4Ebj+xHPPbBql6Xk/SpG4vxyH5iiPZhjNYFm2N0gcZSxIyhYm711nBWfIn6nahc/c+6jR8cLzpBV/mvaZjrEEeFuMoLxXPizGQRzyGhkrCmNSbZQWkIWT8xNYg/DKi4wWkJKTVvWDAIEXIkCgYEAsZPFQ5qNAgePJwqOEocJZv9HQJK1ZoRoXpCHvcvpvKPjniY1O37tZ3eoXBmHFgGZSLzyhGHt8CFSZAnzjxUthTzrVJ4KR3PVNNkOhqKhng+qRUpfh7OJ8UkVdp6cWtYf+CpiZ7wgVspglq9eFTByn+L8oKh6tfIkMaYMXRlXL3A=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showLong("支付成功");

                        db.execSQL("update order_ set ispay = ? where id = ?",new Object[]{1,String.valueOf(index)});

                        Intent intent = new Intent(OrderActivity.this, Order_detailActivity.class);
                        startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showLong("支付失败");
                        Intent intent = new Intent(OrderActivity.this, Order_detailActivity.class);
                        startActivity(intent);
                    }
                    break;
                }


            }
        }

        ;
    };

    /**
     * 支付宝支付业务示例
     */
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {

            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();

        Intent intent = getIntent();


        index = intent.getIntExtra("index", 0);

        Order_goods order_goods = new Order_goods(this, "order.db", null, 1);


        db = order_goods.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from order_ where id = ?", new String[]{String.valueOf(index)});

        List<Goods_sql> goods_sqls = new ArrayList<>();
        while (cursor.moveToNext()) {


            String goods = cursor.getString(cursor.getColumnIndex("goods"));

            float paysum = cursor.getFloat(cursor.getColumnIndex("paysum"));

            paySumOrder.setText(paysum + "");


            JSONArray objects = JSONArray.parseArray(goods);


            goods_sqls = objects.toJavaList(Goods_sql.class);
        }


        numOrder.setText("" + goods_sqls.size());

        Glide.with(this).load(goods_sqls.get(0).getPictUrl()).into(imgOrder);


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

                payV2();
            }
        });


    }

    private void initView() {
        imgOrder = findViewById(R.id.img_order);
        numOrder = findViewById(R.id.num_order);
        paySumOrder = findViewById(R.id.paySum_order);
        pay = findViewById(R.id.pay);
    }
}