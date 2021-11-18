package com.bawei.usercenter.viewmodel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.usercenter.repository.UserCenterRepository;
import com.bawei.usercenter.entity.UserEntity;

public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {

    //页面 数据 源
    public MutableLiveData<UserEntity> source = new MutableLiveData<>();
    public MutableLiveData<UserEntity> source_reg = new MutableLiveData<>();

    public UserCenterViewModel(LifecycleOwner _owner) {
        super(_owner);
    }


    @Override
    protected UserCenterRepository createRepositroy() {
        return new UserCenterRepository();
    }

    @Override
    protected void releaseRes() {

    }

    @Override
    protected void initRes() {

    }


    @Override
    public void onStateChanged( LifecycleOwner source, Lifecycle.Event event) {

    }

    public LiveData<BaseRespEntity<UserEntity>> login (String username, String pwd){

        return mRepository.login(username,pwd);
    }

    public LiveData<BaseRespEntity<UserEntity>> retister (String username, String pwd){

        return mRepository.retister(username,pwd);
    }
}
