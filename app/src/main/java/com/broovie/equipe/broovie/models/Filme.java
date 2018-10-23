package com.broovie.equipe.broovie.models;


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
    private Genero genero;
    private String sinopse;
    private int classificacaoIndicativa;

}
