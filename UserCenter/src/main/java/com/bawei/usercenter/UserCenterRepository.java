package com.bawei.usercenter;

import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.repository.BaseRepository;

public class UserCenterRepository extends BaseRepository<UserCenterModel> {
    @Override
    protected UserCenterModel createModel() {
        return new UserCenterModel();
    }


    //用户登录方法
    public MutableLiveData<UserEntity> login(String phoneNumber,String pwd){
        return mModel.login(phoneNumber, pwd);
    }
}
