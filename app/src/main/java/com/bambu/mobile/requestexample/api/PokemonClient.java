package com.bambu.mobile.requestexample.api;

import com.bambu.mobile.requestexample.model.Pokemon;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ken on 11/07/16.
 */
public interface PokemonClient {


    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") int id);


    @GET("pokemon/{id}")
    Call<ResponseBody> getPokemonById2(@Path("id") int id);


}
