package com.bawei.home.viewmodel;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bawei.home.entity.GoodsEntity;
import com.bawei.home.repository.HomeCenterRepositoty;
import com.bawei.home.entity.TypeEntity;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;

import java.util.ArrayList;
import java.util.List;

public class HomeCenterViewModel extends BaseViewModel<HomeCenterRepositoty> {

    public MutableLiveData<List<TypeEntity>> source = new MutableLiveData<>();
    public MutableLiveData<List<GoodsEntity>> source_goods = new MutableLiveData<>();



    public HomeCenterViewModel(LifecycleOwner _owner) {
        super(_owner);
    }

    @Override
    protected HomeCenterRepositoty createRepositroy() {
        return new HomeCenterRepositoty();
    }

    @Override
    protected void releaseRes() {

    }

    @Override
    protected void initRes() {

    }

    @Override
    public void onStateChanged(LifecycleOwner source,  Lifecycle.Event event) {

    }

    public void getType(){


       mRepository.getType().observe(owner, new Observer<BaseRespEntity<List<TypeEntity>>>() {
           @Override
           public void onChanged(BaseRespEntity<List<TypeEntity>> listBaseRespEntity) {
               if (listBaseRespEntity!=null && listBaseRespEntity.getCode()==200){

                   ArrayList<TypeEntity> typeEntities = new ArrayList<>();

                   for (int i = 0; i <listBaseRespEntity.getData().size() ; i++) {

                       TypeEntity typeEntity = listBaseRespEntity.getData().get(i);

                       typeEntities.add(typeEntity);
                   }

                   source.setValue(typeEntities);
               }else {
                   Log.i("zdc", "onChanged: 失败");
               }
           }
       });
    }

    public LiveData<BaseRespEntity<List<GoodsEntity>>> getGoods(int page){

        return mRepository.getGoods(page);
    }
}
