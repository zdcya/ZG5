package com.bawei.mvvmcore.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bawei.mvvmcore.exception.MVVMNormaIException;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseMVVMActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseActivity {

    protected VM mViewModel;
    protected Binding mBinding;

    //存放UI上所需的数据源（页面变量）
    private HashMap<Integer,Object> mMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mBinding = DataBindingUtil.setContentView(this, getLayoutId());

        mBinding.setLifecycleOwner(this);

        mViewModel = createViewModel();

        super.onCreate(savedInstanceState);

        prepareSetVars(mMap);

        setVars(mBinding,mMap);
    }

    //将设置好的数据源绑定到DataBinding
    private void setVars(Binding mBinding,HashMap<Integer,Object> mMap){

        if (mMap == null || mMap.size() == 0){
            throw new MVVMNormaIException("please set variables...");
        }

        for (Map.Entry<Integer,Object> i: mMap.entrySet()) {
                mBinding.setVariable(i.getKey(),i.getValue());
        }

    }

    //项目父类Map添加变量
    protected abstract void prepareSetVars(HashMap<Integer,Object> mMap);

    //创建ViewModel
    protected abstract VM createViewModel();

    //设置布局ID;
    protected abstract int getLayoutId();
}
