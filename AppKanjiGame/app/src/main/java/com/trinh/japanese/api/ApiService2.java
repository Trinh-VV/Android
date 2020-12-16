package com.trinh.japanese.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trinh.japanese.model.Word;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService2 {
    String BASE_URL = "http://ohayojp.com:8080/api/v1/" ;
    OkHttpClient client = new OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            })
            .build();

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyu-MM-dd HH:mm:ss")
            .create();
    ApiService2 apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService2.class);

    @GET("jpvn-keysearchs")
    Call<List<Word>> getWordByKey(@Query("keysearch") String keysearch);
}
