package com.bawei.usercenter;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;

import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.usercenter.databinding.ActivityLoginBinding;

import java.util.HashMap;

public class LoginActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityLoginBinding>{


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
        String phoneNumber=mViewModel.source.getValue().getPhoneNumber();
        String pwd=mViewModel.source.getValue().getPwd();
        Log.d("123", String.format("phoneNumber : %s Pwd : %s",phoneNumber,pwd));
        mViewModel.login(phoneNumber,pwd).observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity entity) {
                showMsg(entity.toString());
            }
        });
    }
}
