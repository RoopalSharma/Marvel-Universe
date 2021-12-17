package com.oyelabs.marvel.universe.Connection;

import com.oyelabs.marvel.universe.Response.CharacterIdResponse.CharacterBasicResponse;
import com.oyelabs.marvel.universe.Response.CharacterResponse.BasicResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Api {

    @GET("public/characters")
    Call<BasicResponse>getdata(@Query("ts")String ts, @Query("apikey") String apikey, @Query("hash")String hash);


    @GET
    Call<CharacterBasicResponse>getCharacter(@Url String url);
}
