package com.bawei.net.retrofit;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {


    public static LiveDataCallAdapterFactory create(){

        return new LiveDataCallAdapterFactory();
    }


    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {


        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);

        //类型不匹配 return null；
        if (rawType != LiveData.class){

            Log.i("---", "this retrofit request is not call type liveData");

            return null;
        }


        //返回值类 型必须是泛型
        if (!(returnType instanceof ParameterizedType)){

            throw  new IllegalStateException("retutnType must be ParameterizedType");
        }




        return null;
    }
}
