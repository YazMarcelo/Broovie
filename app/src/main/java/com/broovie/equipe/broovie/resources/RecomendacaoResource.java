package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Recomendacao;
import com.broovie.equipe.broovie.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecomendacaoResource {
    @GET("recomendacoes")
    Call<List<Recomendacao>> recomendacoes(@Header("Authorization") String token, @Query("codigoUsuario") long codigoUsuario, @Query("tipo") Recomendacao.TipoRecomendacao tipo);
}
