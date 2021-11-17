package com.bawei.mvvmcore.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.utils.MsgUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEnv();
    }


    protected abstract void initEnv();

    //跳转到指定Activity
    protected void jumpToActivity(Class<?> target){
        this.startActivity(new Intent(this,target));
    }
    //携带参数跳转
    protected void jumpToActivity(Class<?> target,Bundle bundle){
        Intent intent = new Intent(this, target);
        intent.putExtra("params",bundle);
        this.startActivity(intent);
    }

    //Toast
    protected void showMsg(String msg){

        MsgUtils.getInstance().showMsg(msg);
    }
}
