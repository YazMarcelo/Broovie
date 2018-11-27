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
    Call<List<Usuario>> get();

    @GET("usuario/{code}")
    Call<Usuario> get(@Path("code") long code);

    @GET("usuarios/pesquisar")
    Call<List<Usuario>> pesquisar(@Query("nome") String nome, @Query("nomeUsuario") String nomeUsuario);

    @POST("usuario")
    Call<Usuario> post(@Body Usuario usuario);

    @PUT("usuario")
    Call<Usuario> put(@Body Usuario usuario);

    @DELETE("usuario")
    Call<Usuario> delete(@Body Usuario usuario);

    @POST("usuario/autenticar")
    Call<Void> autenticar(@Body Usuario usuario);

    @GET("usuario")
    Call<Usuario> readByNomeUsuario(@Query("nomeUsuario") String nomeUsuario);
}
