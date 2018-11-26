package com.broovie.equipe.broovie.resources;

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

public interface UsuarioResource {
    @GET("usuarios")
    Call<List<Usuario>> get(@Header("Authorization") String token);

    @GET("usuario/{code}")
    Call<Usuario> get(@Header("Authorization") String token, @Path("code") long code);

    @GET("usuarios/pesquisar")
    Call<List<Usuario>> getPesquisar(@Header("Authorization") String token, @Query("nome") String nome, @Query("nomeUsuario") String nomeUsuario);

    @POST("usuario")
    Call<Usuario> post(@Header("Authorization") String token, @Body Usuario usuario);

    @PUT("usuario")
    Call<Usuario> put(@Header("Authorization") String token, @Body Usuario usuario);

    @DELETE("usuario")
    Call<Usuario> delete(@Header("Authorization") String token, @Body Usuario usuario);

    @POST("usuario/autenticar")
    Call<Void> autenticar(@Body Usuario usuario);
}
