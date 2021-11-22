package com.bawei.home.repository;

import androidx.lifecycle.LiveData;

import com.bawei.home.entity.GoodsEntity;
import com.bawei.home.entity.TypeEntity;
import com.bawei.home.model.HomeCenterRemoteModel;
import com.bawei.mvvmcore.repository.BaseRepository;
import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

public class HomeCenterRepositoty extends BaseRepository<HomeCenterRemoteModel> {

    HomeCenterRemoteModel remoteModel;

    public HomeCenterRepositoty() {
        this.remoteModel = new HomeCenterRemoteModel();
    }

    @Override
    protected HomeCenterRemoteModel createModel() {
        return remoteModel;
    }

    public LiveData<BaseRespEntity<List<TypeEntity>>> getType(){

        return remoteModel.getType();
    }

    public LiveData<BaseRespEntity<List<GoodsEntity>>> getGoods(int page){

        return remoteModel.getGoods(page);
    }
}
