package com.bawei.net.retrofit.calladapter;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.bawei.net.procotol.BaseRespEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, MutableLiveData<BaseRespEntity<R>>> {


    private Type type;

    public LiveDataCallAdapter(Type t) {
        this.type = t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public MutableLiveData<BaseRespEntity<R>> adapt(Call<R> call) {

        MutableLiveData<BaseRespEntity<R>> liveData = new MutableLiveData<>();


        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue((BaseRespEntity<R>) response.body());
                }else {
                    liveData.postValue((BaseRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                BaseRespEntity baseRespEntity = new BaseRespEntity();

                baseRespEntity.setCode(-1);

                baseRespEntity.setMsg(t.getMessage());

                if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue(baseRespEntity);
                }else {
                    liveData.postValue(baseRespEntity);
                }

            }
        });




        return liveData;
    }
}
