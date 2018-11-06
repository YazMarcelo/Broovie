package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Genero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GeneroResource {
    @GET("generos")
    Call<List<Genero>> get();

    @POST("genero")
    Call<Genero> post(@Body Genero genero);

    @PUT("genero")
    Call<Genero> put(@Body Genero genero);

    @DELETE("genero")
    Call<Genero> delete(@Body Genero genero);
}
