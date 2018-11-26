package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Filme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FilmeResource {
    @GET("filmes")
    Call<List<Filme>> get(@Header("Authorization") String token);

    @GET("filmes/pesquisar")
    Call<List<Filme>> getPesquisar(@Header("Authorization") String token, @Query("nome") String nome);

    @POST("filme")
    Call<Filme> post(@Header("Authorization") String token, @Body Filme filme);

    @PUT("filme")

    Call<Filme> put(@Header("Authorization") String token, @Body Filme filme);

    @DELETE("filme")
    Call<Filme> delete(@Header("Authorization") String token, @Body Filme filme);
}
