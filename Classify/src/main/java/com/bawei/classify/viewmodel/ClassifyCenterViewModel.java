package com.bawei.classify.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bawei.classify.entity.Goods;
import com.bawei.classify.entity.GoodsType;
import com.bawei.classify.repositroy.ClassifyCenterRepositoty;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.net.procotol.BaseRespEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ClassifyCenterViewModel extends BaseViewModel<ClassifyCenterRepositoty> {
    public ClassifyCenterViewModel(LifecycleOwner _owner) {
        super(_owner);
    }

    @Override
    protected ClassifyCenterRepositoty createRepositroy() {
        return new ClassifyCenterRepositoty();
    }

    @Override
    protected void releaseRes() {

    }

    @Override
    protected void initRes() {

    }

    @Override
    public void onStateChanged(@NonNull @NotNull LifecycleOwner source, @NonNull @NotNull Lifecycle.Event event) {

    }

    public LiveData<BaseRespEntity<List<GoodsType>>> getGoodsType(int parentId){

        return mRepository.getGoodsType(parentId);
    }

    public LiveData<BaseRespEntity<List<Goods>>> getGoods(String keyword, String type){

        return mRepository.getGoods(keyword, type);
    }
}
