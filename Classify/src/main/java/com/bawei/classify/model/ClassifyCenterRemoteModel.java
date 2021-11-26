package com.bawei.classify.model;

import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.Goods;
import com.bawei.classify.entity.GoodsType;
import com.bawei.mvvmcore.model.IModel;
import com.bawei.net.RetrofitFactory;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

public class ClassifyCenterRemoteModel implements IModel {

    public LiveData<BaseRespEntity<List<GoodsType>>> getGoodsType(int parentId){
        Classify api = RetrofitFactory.getInstance().create(Classify.class);

        LiveData<BaseRespEntity<List<GoodsType>>> goodsType = api.getGoodsType(parentId);

        return goodsType;
    }

    public LiveData<BaseRespEntity<List<Goods>>> getGoods(String keyword,String type){
        Classify api = RetrofitFactory.getInstance().create(Classify.class);

        LiveData<BaseRespEntity<List<Goods>>> goods = api.getGoods(keyword, type);

        return goods;
    }
}
