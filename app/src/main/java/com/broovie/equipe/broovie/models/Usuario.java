package com.broovie.equipe.broovie.models;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Usuario extends EntidadePadrao {
    private String nome;
    private String email;
    private Date dataNascimento;
    private String pais;
    private String nomeUsuario;
    private String senha;
    private List<Usuario> amigos;
    private List<Genero> generos;

        /*
       @Column
        private String cidade;
        @Column
        private String estado;
    */
}