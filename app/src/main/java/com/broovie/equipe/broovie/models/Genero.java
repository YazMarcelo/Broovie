package com.broovie.equipe.broovie.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@ToString

public class Genero extends EntidadePadrao {
    private String descricao;

    public Genero(String descricao) {
        this.descricao = descricao;
    }
}
