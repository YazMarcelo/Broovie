package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Filme;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FilmeResource {
    @GET("filmes")
    Call<List<Filme>> get();

    @GET("filmes/pesquisar")
    Call<List<Filme>> getPesquisar(@Query("nome") String nome);

    @POST("filme")
    Call<Filme> post(@Body Filme filme);

    @PUT("filme")

    Call<Filme> put(@Body Filme filme);

    @DELETE("filme")
    Call<Filme> delete(@Body Filme filme);

    @GET("filmes/recomendados")
    Call<List<Filme>> recomendados(@Query("codigoUsuario") Long codigoUsuario);
}
