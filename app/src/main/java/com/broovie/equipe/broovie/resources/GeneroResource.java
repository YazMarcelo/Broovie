package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Genero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GeneroResource {
    @GET("generos")
    Call<List<Genero>> get(@Header("Authorization") String token);

    @POST("genero")
    Call<Genero> post(@Header("Authorization") String token, @Body Genero genero);

    @PUT("genero")
    Call<Genero> put(@Header("Authorization") String token, @Body Genero genero);

    @DELETE("genero")
    Call<Genero> delete(@Header("Authorization") String token, @Body Genero genero);
}
