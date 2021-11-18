package com.bawei.usercenter.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.usercenter.BR;
import com.bawei.usercenter.R;
import com.bawei.usercenter.databinding.ActivityLoginBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;
import com.bawei.utils.MsgUtils;

import java.util.HashMap;

public class LoginActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityLoginBinding>{


    private android.widget.TextView reg;

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.user,mViewModel.source);

        mMap.put(BR.owner,this);
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEnv() {
        mViewModel.source.setValue(new UserEntity());
    }


    public void onLogin(View view){
        String phoneNumber=mViewModel.source.getValue().getUsername();
        String pwd=mViewModel.source.getValue().getPwd();
        Log.d("123", String.format("phoneNumber : %s Pwd : %s",phoneNumber,pwd));
        mViewModel.login(phoneNumber,pwd).observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                if (userEntityBaseRespEntity.getCode()==200){
                    Toast.makeText(LoginActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

   public void toRetister(View view){

        jumpToActivity(RetisterActivity.class);
   }
}
