package com.bawei.classify.model;

import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.Goods;
import com.bawei.classify.entity.GoodsType;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Classify {

    @GET("api/GoodsType/getTypes")
    LiveData<BaseRespEntity<List<GoodsType>>> getGoodsType(@Query("parentId")int parentId);

    @GET("api/Goods/getGoods?pageno=1&pagesize=10")
    LiveData<BaseRespEntity<List<Goods>>> getGoods(@Query("keyword")String keyword, @Query("type")String type);
}
