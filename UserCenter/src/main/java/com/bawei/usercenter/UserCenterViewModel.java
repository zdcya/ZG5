package com.bawei.usercenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.viewmodel.BaseViewModel;

public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {

    //页面 数据 源
    public MutableLiveData<UserEntity> source = new MutableLiveData<>();

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

    public MutableLiveData<UserEntity> login (String phoneNumber,String pwd){

        return mRepository.login(phoneNumber, pwd);
    }
}
