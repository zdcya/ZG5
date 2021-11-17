package com.bawei.usercenter;

import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.model.IModel;
import com.bawei.utils.ThreadUtils;

public class UserCenterModel implements IModel {



    //登录方法
    public MutableLiveData<UserEntity> login(String phoneNumber,String pwd){


        MutableLiveData<UserEntity> result = new MutableLiveData<>();

        UserEntity userEntity = new UserEntity(1,"10010","",21,"天津");

        if (ThreadUtils.isMainThread()){

            result.setValue(userEntity);
        }else {

            result.postValue(userEntity);
        }

        return result;
    }

}
