package com.bawei.home.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONArray;
import com.bawei.home.R;
import com.bawei.home.entity.Goods_sql;
import com.bawei.home.entity.OrderEntity;
import com.bawei.resource.Order_goods;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Order_detailActivity extends AppCompatActivity {

    private RecyclerView rvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initView();


        Order_goods order_goods = new Order_goods(this, "order.db", null, 1);


        SQLiteDatabase db = order_goods.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from order_", null);

        ArrayList<OrderEntity> orderEntities = new ArrayList<>();

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String goods = cursor.getString(cursor.getColumnIndex("goods"));
            JSONArray objects = JSONArray.parseArray(goods);
            List<Goods_sql> goods_sqls = objects.toJavaList(Goods_sql.class);
            float paysum = cursor.getFloat(cursor.getColumnIndex("paysum"));
            int ispay = cursor.getInt(cursor.getColumnIndex("ispay"));

            OrderEntity orderEntity = new OrderEntity(id, goods_sqls, ispay, paysum);

            orderEntities.add(orderEntity);
        }


        MyAdapter_order myAdapter_order = new MyAdapter_order(orderEntities);

        rvOrder.setAdapter(myAdapter_order);

    }

    private void initView() {
        rvOrder = findViewById(R.id.rv_order);
        rvOrder.setLayoutManager(new LinearLayoutManager(this));
    }

    public class MyAdapter_order extends BaseQuickAdapter<OrderEntity, BaseViewHolder>{

        public MyAdapter_order( @Nullable List<OrderEntity> data) {
            super(R.layout.item_order, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, OrderEntity orderEntity) {

            Glide.with(Order_detailActivity.this).load(orderEntity.getOrder_goods().get(0).getPictUrl()).into((ImageView) baseViewHolder.findView(R.id.img_item_order));

            baseViewHolder.setText(R.id.price_item_order,String.valueOf(orderEntity.getPaySum()));

            baseViewHolder.setText(R.id.num_item_order,String.valueOf(orderEntity.getOrder_goods().size()));

            if (orderEntity.isPay()==1){

                baseViewHolder.setText(R.id.pay_item_order,"卖了换钱");
            }else {
                baseViewHolder.setText(R.id.pay_item_order,"去支付");
            }


        }
    }
}