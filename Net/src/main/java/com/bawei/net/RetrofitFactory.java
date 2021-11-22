package com.bawei.net;


import android.util.Log;

import com.bawei.net.common.Constanct;
import com.bawei.net.procotol.TokenRespEntity;
import com.bawei.net.retrofit.CustomGsonConverterFactory;
import com.bawei.net.retrofit.CustomGsonConverterFactory2;
import com.bawei.net.retrofit.calladapter.LiveDataCallAdapterFactory;
import com.bawei.net.retrofit.calladapter.LiveDataCallAdapterFactory2;
import com.bawei.net.service.TokenApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static RetrofitFactory instance = null;

    private Retrofit retrofit = null;
    private Retrofit retrofit2 = null;

    public static class Holder{
        private static  RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    public static RetrofitFactory getInstance() {
        return Holder.INSTANCE ;
    }

    public RetrofitFactory() {

        retrofit = createRetrofit();
        retrofit2 = createRetrofit2();

    }

    private Retrofit createRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("http://82.156.178.182:8082/")
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private Retrofit createRetrofit2() {

        return new Retrofit.Builder()
                .baseUrl("http://82.156.178.182:8082/")
                .addConverterFactory(CustomGsonConverterFactory2.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory2.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2,TimeUnit.MINUTES)
                .writeTimeout(2,TimeUnit.MINUTES)
                .addNetworkInterceptor(createOkHttpNetInterceptor())
                .addInterceptor(createTokenInterceptor())
                .addInterceptor(createHeadsInterceptor())
                .build();

        return okHttpClient;
    }

    private Interceptor createHeadsInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
    }

    private Interceptor createTokenInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Response proceed = chain.proceed(request);

                if (proceed.code() == 401){

                    String token = doTokenTask();

                    Request newreq = request.newBuilder()
                            .addHeader("Authorization","bearer "+token)
                            .build();

                    return chain.proceed(newreq);
                }


                return proceed;
            }
        };
    }

    private String doTokenTask(){

        TokenApi tokenApi =create(TokenApi.class);

        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Constanct.getAuthCode(), "");

        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();

            if (result != null && result.body() != null){
                return  result.body().getAccess_token();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    public <T> T create(Class<?> service){
        return (T)retrofit.create(service);
    }

    public <T> T create2(Class<?> service){
        return (T)retrofit2.create(service);
    }

    private Interceptor createOkHttpNetInterceptor() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return httpLoggingInterceptor;
    }
}
