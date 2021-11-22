package com.bawei.home.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.bawei.home.entity.GoodsEntity;
import com.bawei.home.entity.TypeEntity;
import com.bawei.mvvmcore.model.IModel;
import com.bawei.net.RetrofitFactory;
import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

public class HomeCenterRemoteModel implements IModel {

    public LiveData<BaseRespEntity<List<TypeEntity>>> getType(){
        HomeCenterApi api = RetrofitFactory.getInstance().create(HomeCenterApi.class);

        LiveData<BaseRespEntity<List<TypeEntity>>> type = api.getType();

        return type;
    }

    public LiveData<BaseRespEntity<List<GoodsEntity>>> getGoods(int page){
        HomeCenterApi api = RetrofitFactory.getInstance().create(HomeCenterApi.class);

        LiveData<BaseRespEntity<List<GoodsEntity>>> goods = api.getGoods(page);

        return goods;
    }
}
