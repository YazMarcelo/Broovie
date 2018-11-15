package com.broovie.equipe.broovie.models;


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

public class Filme extends EntidadePadrao {
    private String nome;
    private List<Genero> generos;
    private String sinopse;
    private int classificacaoIndicativa;
    private Boolean adulto;
    private String fotoCapa;

}
