package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UsuarioResource {
    @GET("usuarios")
    Call<List<Usuario>> get();

    @GET("usuarios/pesquisar")
    Call<List<Usuario>> getPesquisar(@Query("nome") String nome);

    @POST("usuario")
    Call<Usuario> post(@Body Usuario usuario);

    @PUT("usuario")

    Call<Usuario> put(@Body Usuario usuario);

    @DELETE("usuario")
    Call<Usuario> delete(@Body Usuario usuario);
}
