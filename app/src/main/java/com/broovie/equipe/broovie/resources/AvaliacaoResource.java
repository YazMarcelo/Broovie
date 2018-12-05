package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Avaliacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AvaliacaoResource {
    @POST("avaliacao")
    Call<Avaliacao> post(@Body Avaliacao avaliacao);

    @PUT("avaliacao")
    Call<Avaliacao> put(@Body Avaliacao avaliacao);

    @GET("usuario/{code}/avaliacoes")
    Call<List<Avaliacao>> getAvaliacoes(@Path("code") long code);

    @GET("avaliacao/{codigoUsuario}/filme/{codigoFilme}")
    Call<Avaliacao> avaliacao(@Path("codigoUsuario") long codigoUsuario, @Path("codigoFilme") long codigoFilme);
}

