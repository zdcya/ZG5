package com.bawei.usercenter.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.mvvmcore.model.IModel;
import com.bawei.mvvmcore.repository.BaseRepository;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.UserCenterRemoteModel;

public class UserCenterRepository extends BaseRepository<UserCenterRemoteModel> {

    UserCenterRemoteModel remoteModel;

    public UserCenterRepository() {
        remoteModel = new UserCenterRemoteModel();
    }

    @Override
    protected UserCenterRemoteModel createModel() {
        return remoteModel;
    }


    public LiveData<BaseRespEntity<UserEntity>> login(String username, String pwd){

        return remoteModel.login(username,pwd);

    }

    public LiveData<BaseRespEntity<UserEntity>> retister(String username, String pwd){

        return remoteModel.retister(username,pwd);

    }
}
