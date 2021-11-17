package com.bawei.mvvmcore.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

import com.bawei.mvvmcore.repository.BaseRepository;

public abstract class BaseViewModel<Repo extends BaseRepository> extends ViewModel implements LifecycleEventObserver {

    protected Repo mRepository;
    protected LifecycleOwner owner;


    public BaseViewModel(LifecycleOwner _owner) {
        mRepository =createRepositroy();
        _owner.getLifecycle().addObserver(this);
        this.owner = _owner;
    }

    //创建具体数据库
    protected abstract Repo createRepositroy();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void activityOnCreate(){
        initRes();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void activityOnStop(){
        releaseRes();
    }

    //释放资源
    protected abstract void releaseRes();


    //子类初始化资源
    protected  abstract void initRes();


}
