package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Avaliacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AvaliacaoResource {
    @GET("usuario/{code}/avaliacoes")
    Call<List<Avaliacao>> getAvaliacoes(@Path("code") long code);
}

