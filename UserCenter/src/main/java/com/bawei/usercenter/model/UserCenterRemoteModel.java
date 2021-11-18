package com.bawei.usercenter.model;

import androidx.lifecycle.LiveData;

import com.bawei.mvvmcore.model.IModel;
import com.bawei.net.RetrofitFactory;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.usercenter.entity.UserEntity;

public class UserCenterRemoteModel implements IModel {


    public LiveData<BaseRespEntity<UserEntity>> login(String username,String pwd){

        UserCenterApi api = RetrofitFactory.getInstance().create(UserCenterApi.class);
        return api.login(new UserEntity(1,username,pwd,"0",""));
    }

    public LiveData<BaseRespEntity<UserEntity>> retister(String username,String pwd){

        UserCenterApi api = RetrofitFactory.getInstance().create(UserCenterApi.class);
        return api.retister(new UserEntity(11,username,pwd,"0",""));
    }
}
