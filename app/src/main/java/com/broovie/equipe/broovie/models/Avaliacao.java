package com.broovie.equipe.broovie.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Avaliacao extends EntidadePadrao {
    private Usuario usuario;
    private Filme filme;
    private Nota nota;
    public enum Nota {
        INUTIL,
        PESSIMO,
        RUIM,
        MEDIO,
        BOM,
        OTIMO
    }
}
