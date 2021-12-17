package com.oyelabs.marvel.universe.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oyelabs.marvel.universe.Connection.Api;
import com.oyelabs.marvel.universe.Connection.ApiConstant;
import com.oyelabs.marvel.universe.R;
import com.oyelabs.marvel.universe.Response.CharacterIdResponse.CharacterBasicResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CharacterIdActivity extends AppCompatActivity {

    ImageView image_id;
    TextView name_id;
    TextView marvel_id;
    TextView description_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_id);

        image_id=findViewById(R.id.image_id);
        name_id=findViewById(R.id.name_id);
        marvel_id=findViewById(R.id.marvel_id);
        description_id=findViewById(R.id.description_id);


        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
            getCharacterById(String.valueOf(id),ApiConstant.time,ApiConstant.hashkey);

    }

    public  void getCharacterById(String id,String timestamp,String hash)
    {
        Gson gson=new GsonBuilder().create();

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
               interceptor .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://gateway.marvel.com:443/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        String parameter="public/characters/"+id+"?ts="+timestamp+"&apikey="+ ApiConstant.PUBLIC_KEY+"&hash="+hash;

        ProgressDialog progressDialog=new ProgressDialog(CharacterIdActivity.this);
        progressDialog.show();
        Api api=retrofit.create(Api.class);
        api.getCharacter(parameter)
                .enqueue(new Callback<CharacterBasicResponse>() {
                    @Override
                    public void onResponse(Call<CharacterBasicResponse> call, Response<CharacterBasicResponse> response) {

                        if (response.isSuccessful()) {
                           name_id.setText(""+response.body().getData().getResults().get(0).getName());
                           description_id.setText(""+response.body().getData().getResults().get(0).getDescription());
                           marvel_id.setText(""+response.body().getData().getResults().get(0).getId());

                           Glide.with(CharacterIdActivity.this).
                                   load(response.body().getData().getResults().get(0).getThumbnail().getPath()+"."+response.body().getData().getResults().get(0).getThumbnail().getExtension()).into(image_id);
                           progressDialog.dismiss();
                        }
                    }
                    @Override
                    public void onFailure(Call<CharacterBasicResponse> call, Throwable t) {

                    }
                });


    }
}