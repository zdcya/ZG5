package com.bawei.net.retrofit;

import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.net.procotol.TokenRespEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseBodyConverter<T> implements Converter<ResponseBody,T> {

    private Type type;

    public CustomResponseBodyConverter(Type _type) {
        this.type = _type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {

        Gson gson = new Gson();

        String respContent = value.string();

        if (respContent.contains("access_")){
            return (T) gson.fromJson(respContent, TokenRespEntity.class);
        }

        BaseRespEntity baseRespEntity = gson.fromJson(respContent, BaseRespEntity.class);

        if (baseRespEntity.getCode() != 200){

            return (T) baseRespEntity;
        }else {
            return gson.fromJson(respContent,type);
        }


    }
}
