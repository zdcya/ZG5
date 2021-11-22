package com.bawei.net.retrofit.calladapter;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter2<R> implements CallAdapter<R, MutableLiveData<BaseListRespEntity<R>>> {


    private Type type;

    public LiveDataCallAdapter2(Type t) {
        this.type = t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public MutableLiveData<BaseListRespEntity<R>> adapt(Call<R> call) {

        MutableLiveData<BaseListRespEntity<R>> liveData = new MutableLiveData<>();


        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue((BaseListRespEntity<R>) response.body());
                }else {
                    liveData.postValue((BaseListRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                BaseListRespEntity baseListRespEntity = new BaseListRespEntity();

                baseListRespEntity.setCode(-1);

                baseListRespEntity.setMsg(t.getMessage());

                if (Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue(baseListRespEntity);
                }else {
                    liveData.postValue(baseListRespEntity);
                }

            }
        });




        return liveData;
    }
}
