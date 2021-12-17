package com.oyelabs.marvel.universe.Connection;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {



    public static String ROOT = ApiConstant.BASE_URL;

    public static Retrofit retrofit = null;
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public  static  Retrofit getRetrofit()
    {

        if (retrofit==null)
        {
            HttpLoggingInterceptor interceptor= new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .addInterceptor(interceptor).build();

            retrofit= new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ROOT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }

        return retrofit;
    }


}
