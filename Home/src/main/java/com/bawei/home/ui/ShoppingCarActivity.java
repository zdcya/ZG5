package com.bawei.home.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.blankj.utilcode.util.ToastUtils;
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
    int count=0;
    ArrayList<Goods_sql> goods_sqls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);
        initView();


        GoodsSql goodsSql = new GoodsSql(this, "goods.db", null, 1);

        SQLiteDatabase db = goodsSql.getWritableDatabase();


        Cursor cursor = db.rawQuery("select * from goods", null);


        goods_sqls = new ArrayList<>();


        while (cursor.moveToNext()) {

            String ShortTitle = cursor.getString(cursor.getColumnIndex("ShortTitle"));

            Log.i("000", "onCreate: " + ShortTitle);
            String PictUrl = cursor.getString(cursor.getColumnIndex("PictUrl"));
            String ReservePrice = cursor.getString(cursor.getColumnIndex("ReservePrice"));

            Goods_sql goods_sql = new Goods_sql(ShortTitle, PictUrl, ReservePrice);

            goods_sqls.add(goods_sql);
        }

        int size = goods_sqls.size();


        GoodsSqlAdapter goodsSqlAdapter = new GoodsSqlAdapter(goods_sqls);

        ScarRv.setAdapter(goodsSqlAdapter);

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (checkAll.isChecked()) {
                    qx = true;
                } else {
                    qx = false;
                }

                goodsSqlAdapter.notifyDataSetChanged();

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



            TextView add = baseViewHolder.findView(R.id.add_item);
            TextView del = baseViewHolder.findView(R.id.del_item);
            TextView num_ = baseViewHolder.findView(R.id.num_item);
            CheckBox checkBox = baseViewHolder.findView(R.id.checked_car);


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = Integer.parseInt(num_.getText().toString());

                    i++;

                    num_.setText(i + "");
                }
            });

            del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int i = Integer.parseInt(num_.getText().toString());

                    i--;

                    if (i >= 0) {
                        num_.setText(i + "");
                    }


                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked){
                        count++;
                    }else {
                        count--;
                    }

                    if (count == goods_sqls.size()){
                        if (checkAll.isChecked()==false){
                            checkAll.setChecked(true);
                        }
                    }else {
                        checkAll.setChecked(false);
                    }
                }
            });


            if (qx){
                checkBox.setChecked(true);
            }else {
                checkBox.setChecked(false);
            }


        }
    }

    private void initView() {
        ScarRv = findViewById(R.id.Scar_rv);

        ScarRv.setLayoutManager(new LinearLayoutManager(this));
        checkAll = findViewById(R.id.check_all);
    }
}