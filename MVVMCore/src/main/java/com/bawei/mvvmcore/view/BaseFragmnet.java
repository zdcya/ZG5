package com.bawei.mvvmcore.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragmnet extends Fragment {

    protected BaseActivity mActivity;

    boolean isViewCreated = false;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        mActivity = (BaseActivity) getActivity();

        initEnv();


        return createView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;

        initView(view);
    }

    protected abstract void initEnv();
    protected abstract void initView(View view);



    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareLoadData();


    }

    boolean isFirst=true;
    private void prepareLoadData() {
        if (isFirst&&isViewCreated){
            loadData();
            Log.i("1213", "prepareLoadData: 11");
            isFirst=false;
        }

    }

    protected abstract void loadData();

    protected abstract View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


}
