package com.bawei.usercenter.ui;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.usercenter.BR;
import com.bawei.usercenter.R;
import com.bawei.usercenter.databinding.ActivityLoginBinding;
import com.bawei.usercenter.databinding.ActivtiyRetisterBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

public class RetisterActivity extends BaseMVVMActivity<UserCenterViewModel, ActivtiyRetisterBinding> {
    @Override
    protected void prepareSetVars(HashMap mMap) {
        mMap.put(BR.user_reg,mViewModel.source_reg);

        mMap.put(BR.owner_reg,this);
    }


    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activtiy_retister;
    }

    @Override
    protected void initEnv() {
        mViewModel.source_reg.setValue(new UserEntity());
    }

    public void onRetister(View nview){
        String username=mViewModel.source_reg.getValue().getUsername();
        String pwd=mViewModel.source_reg.getValue().getPwd();

        mViewModel.retister(username,pwd).observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {

                if (userEntityBaseRespEntity.getCode()==200){
                    Toast.makeText(RetisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RetisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void toLogin(View view){

        jumpToActivity(LoginActivity.class);
    }
}
