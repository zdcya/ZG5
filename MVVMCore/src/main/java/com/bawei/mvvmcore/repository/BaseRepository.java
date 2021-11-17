package com.bawei.mvvmcore.repository;

import com.bawei.mvvmcore.model.IModel;

public abstract class BaseRepository<M extends IModel> {

    protected M mModel;

    public BaseRepository() {
        this.mModel = createModel();
    }

    //创建实例化Model
    protected abstract M createModel();
}
