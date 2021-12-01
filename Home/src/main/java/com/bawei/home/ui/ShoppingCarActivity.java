package com.bawei.home.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.home.R;
import com.bawei.home.entity.Goods_sql;
import com.bawei.resource.GoodsSql;
import com.bawei.resource.Order_goods;
import com.blankj.utilcode.util.GsonUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends AppCompatActivity {

    private RecyclerView ScarRv;
    int num = 1;
    private CheckBox checkAll;
    boolean qx = false;
    int count = 0;
    ArrayList<Goods_sql> goods_sqls;
    GoodsSqlAdapter goodsSqlAdapter;
    float PriceSum;
    private TextView sum;
    boolean issd = false;
    private Button clearing;
    private static  int order_index;
    float sumValue2 = 0.0f;

    SQLiteDatabase db_order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initView();


        GoodsSql goodsSql = new GoodsSql(this, "goods.db", null, 1);

        SQLiteDatabase db = goodsSql.getWritableDatabase();

        Order_goods order_goods = new Order_goods(this, "order.db", null, 1);

        db_order = order_goods.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from goods", null);


        goods_sqls = new ArrayList<>();

        while (cursor.moveToNext()) {

            String ShortTitle = cursor.getString(cursor.getColumnIndex("ShortTitle"));

            String PictUrl = cursor.getString(cursor.getColumnIndex("PictUrl"));
            String ReservePrice = cursor.getString(cursor.getColumnIndex("ReservePrice"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));


            Goods_sql goods_sql = new Goods_sql(ShortTitle, PictUrl, ReservePrice,id);

            goods_sqls.add(goods_sql);
        }

        int size = goods_sqls.size();


        goodsSqlAdapter = new GoodsSqlAdapter(goods_sqls);

        ScarRv.setAdapter(goodsSqlAdapter);


        checkAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Object tag = buttonView.getTag();

                if (tag != null && (Boolean) tag) {
                    return;
                }
                if (isChecked) {
                    checkdAll(true);
                } else {
                    checkdAll(false);
                }
            }
        });

        clearing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Goods_sql> order_g = new ArrayList<>();
                for (int i = 0; i < ShoppingCarActivity.this.goods_sqls.size() ; i++) {


                    if (ShoppingCarActivity.this.goods_sqls.get(i).isCheck()){

                        String shortTitle = ShoppingCarActivity.this.goods_sqls.get(i).getShortTitle();
                        String pictUrl = ShoppingCarActivity.this.goods_sqls.get(i).getPictUrl();
                        String reservePrice = ShoppingCarActivity.this.goods_sqls.get(i).getReservePrice();
                        Integer id = ShoppingCarActivity.this.goods_sqls.get(i).getId();

                        Goods_sql goods_sql = new Goods_sql(shortTitle, pictUrl, reservePrice, id);

                        order_g.add(goods_sql);

                        db.execSQL("delete from goods where id = ?",new Object[]{id});
                    }
                }


                String s = GsonUtils.toJson(order_g);

                order_index++;

                db_order.execSQL("insert into order_ values(?,?,?,?)",new Object[]{order_index,s,sumValue2,0});


                Intent intent = new Intent(ShoppingCarActivity.this, OrderActivity.class);
                intent.putExtra("index",order_index);
                startActivity(intent);

            }
        });

    }

    public class GoodsSqlAdapter extends BaseQuickAdapter<Goods_sql, BaseViewHolder> {


        public GoodsSqlAdapter(@Nullable List<Goods_sql> data) {
            super(R.layout.item_goodssql, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, Goods_sql goods_sql) {

            Glide.with(ShoppingCarActivity.this).load(goods_sql.getPictUrl()).into((ImageView) baseViewHolder.findView(R.id.img_car));
            baseViewHolder.setText(R.id.name_car, goods_sql.getShortTitle());
            baseViewHolder.setText(R.id.price_car, goods_sql.getReservePrice());
            baseViewHolder.setText(R.id.num_item, goods_sql.getNum() + "");


            int itemPosition = getItemPosition(goods_sql);


            TextView add = baseViewHolder.findView(R.id.add_item);
            TextView del = baseViewHolder.findView(R.id.del_item);
            CheckBox checkBox = baseViewHolder.findView(R.id.checked_car);

            checkBox.setChecked(goods_sql.isCheck());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    goods_sql.setCheck(isChecked);

                    notifyPropChanged();
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goods_sql.setNum(goods_sql.getNum() + 1);
                    //局部刷新
                    notifyItemChanged(itemPosition);

                    if (checkBox.isChecked()) {
                        notifyPropChanged();
                    }
                }
            });

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goods_sql.setNum(goods_sql.getNum() - 1);
                    //局部刷新
                    notifyItemChanged(itemPosition);

                    if (checkBox.isChecked()) {
                        notifyPropChanged();
                    }

                }
            });
        }
    }

    private void initView() {
        ScarRv = findViewById(R.id.Scar_rv);

        ScarRv.setLayoutManager(new LinearLayoutManager(this));
        checkAll = findViewById(R.id.check_all);
        sum = findViewById(R.id.sum);
        clearing = findViewById(R.id.clearing);
    }

    private void notifyPropChanged() {

        float sumValue = 0.0f;
        boolean flag = false;
        for (Goods_sql entity : goods_sqls) {
            if (entity.isCheck()) {
                sumValue += entity.getPriceSum();
            } else {
                flag = true;
            }
        }
        if (flag) {
            checkAll.setTag(true);
            checkAll.setChecked(false);
        } else {
            checkAll.setTag(false);
            checkAll.setChecked(true);
        }
        sum.setText(String.valueOf(sumValue));

        if (issd == false) {
            checkAll.setTag(false);
        }

        sumValue2 =sumValue;
    }

    private void checkdAll(boolean b) {

        issd = b;

        for (Goods_sql entity : goods_sqls) {
            entity.setCheck(b);
        }


        goodsSqlAdapter.notifyDataSetChanged();
        notifyPropChanged();


    }
}