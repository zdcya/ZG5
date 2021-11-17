package com.bawei.net.retrofit.calladapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

public class DefaulCallAdapter<R> implements CallAdapter<R,Object> {

    Type type = null;

    public DefaulCallAdapter(Type t) {
        this.type = t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
