package com.bawei.classify.repositroy;

import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.Goods;
import com.bawei.classify.entity.GoodsType;
import com.bawei.classify.model.Classify;
import com.bawei.classify.model.ClassifyCenterRemoteModel;
import com.bawei.mvvmcore.repository.BaseRepository;
import com.bawei.net.RetrofitFactory;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.List;

public class ClassifyCenterRepositoty extends BaseRepository<ClassifyCenterRemoteModel> {

    ClassifyCenterRemoteModel classifyCenterRemoteModel;

    public ClassifyCenterRepositoty() {
        this.classifyCenterRemoteModel = new ClassifyCenterRemoteModel();
    }

    @Override
    protected ClassifyCenterRemoteModel createModel() {
        return classifyCenterRemoteModel;
    }

    public LiveData<BaseRespEntity<List<GoodsType>>> getGoodsType(int parentId){

        return classifyCenterRemoteModel.getGoodsType(parentId);
    }

    public LiveData<BaseRespEntity<List<Goods>>> getGoods(String keyword, String type){

        return classifyCenterRemoteModel.getGoods(keyword, type);
    }
}
