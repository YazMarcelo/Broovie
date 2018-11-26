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
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AmizadeResource {
    @GET("usuario/{codigoUsuario}/amigos")
    Call<List<Usuario>> amigos(@Header("Authorization") String token, @Path("codigoUsuario") long codigoUsuario);

    @GET("amizades/{codigoUsuario}/pendentes")
    Call<List<Usuario>> solicitacoesPendentes(@Header("Authorization") String token, @Path("codigoUsuario") long codigoUsuario);

    @POST("usuario/{codigoUsuario}/amigos")
    Call<List<Usuario>> adicionarAceitarAmigo(@Header("Authorization") String token, @Path("codigoUsuario") long codigoUsuario, @Body Usuario amigo);

    @DELETE("usuario/{codigoUsuario}/amigos")
    Call<List<Usuario>> removerRejeitarAmigo(@Header("Authorization") String token, @Path("codigoUsuario") long codigoUsuario, @Body Usuario amigo);
}

