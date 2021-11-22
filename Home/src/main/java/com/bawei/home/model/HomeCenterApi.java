package com.bawei.home.model;

import androidx.lifecycle.LiveData;

import com.bawei.home.entity.GoodsEntity;
import com.bawei.home.entity.TypeEntity;
import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HomeCenterApi {

    @GET("api/GoodsType/getRecommendTypes")
    LiveData<BaseRespEntity<List<TypeEntity>>> getType();


    @GET("api/Goods/getRecommendGoods?pagesize=20")
    LiveData<BaseRespEntity<List<GoodsEntity>>> getGoods(@Query("page")int page);
}
