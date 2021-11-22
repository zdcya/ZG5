package com.bawei.mvvmcore.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.bawei.mvvmcore.exception.MVVMNormaIException;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseMVVMFragment<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseLazyFragment {

    protected VM mViewModel;
    protected Binding mBinding;
    //存放UI上所需的数据源（页面变量）
    private HashMap<Integer,Object> mMap = new HashMap<>();



    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        
        mBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);

        mBinding.setLifecycleOwner(this);
        
        mViewModel = createViewModel();
        
        prepareSetVars(mMap);
        
        setVars(mBinding,mMap);
        
        return mBinding.getRoot();
    }

    private void setVars(Binding mBinding, HashMap<Integer, Object> mMap) {

        if (mMap == null || mMap.size() == 0){
            throw new MVVMNormaIException("please set variables...");
        }

        for (Map.Entry<Integer,Object> entry :mMap.entrySet()) {
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }
    
    protected abstract Integer getLayoutId();
    
    protected abstract VM createViewModel();

    protected abstract void prepareSetVars(HashMap<Integer, Object> mMap);

   

   
}
