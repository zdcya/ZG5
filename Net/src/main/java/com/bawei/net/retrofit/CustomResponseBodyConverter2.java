package com.bawei.net.retrofit;

import com.bawei.net.procotol.BaseListRespEntity;
import com.bawei.net.procotol.BaseRespEntity;
import com.bawei.net.procotol.TokenRespEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomResponseBodyConverter2<T> implements Converter<ResponseBody,T> {

    private Type type;

    public CustomResponseBodyConverter2(Type _type) {
        this.type = _type;
    }


    @Override
    public T convert(ResponseBody value) throws IOException {

        Gson gson = new Gson();

        String respContent = value.string();

        if (respContent.contains("access_")){
            return (T) gson.fromJson(respContent, TokenRespEntity.class);
        }

        BaseListRespEntity baseListRespEntity = gson.fromJson(respContent, BaseListRespEntity.class);

        if (baseListRespEntity.getCode() != 200){

            return (T) baseListRespEntity;
        }else {
            return gson.fromJson(respContent,type);
        }

    }
}
