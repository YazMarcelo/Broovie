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
    @GET("genero")
    Call<List<Genero>> get();

    @POST("genero")
    Call<Genero> post(@Body Genero genero);

    @PUT("genero")
    Call<Genero> put(@Body Genero genero);

    @DELETE("genero")
    Call<Void> delete(@Body Genero genero);
}
