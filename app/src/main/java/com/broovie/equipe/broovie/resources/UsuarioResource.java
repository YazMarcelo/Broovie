package com.broovie.equipe.broovie.resources;

import com.broovie.equipe.broovie.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UsuarioResource {
    @GET("usuario")
    Call<List<Usuario>> get();

    @POST("usuario")
    Call<Usuario> post(@Body Usuario usuario);

    @PUT("usuario")
    Call<Usuario> put(@Body Usuario usuario);

    @DELETE("usuario")
    Call<Void> delete(@Body Usuario usuario);
}
