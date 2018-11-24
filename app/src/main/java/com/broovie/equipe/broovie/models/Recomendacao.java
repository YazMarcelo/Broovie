package com.broovie.equipe.broovie.models;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recomendacao {
    private Long code;
    private Usuario usuario;
    private Filme filme;
    private Avaliacao.Nota notaCalculada;
    private Date dataRecomendacao;
    private TipoRecomendacao tipoRecomendacao;

    public enum TipoRecomendacao {
        USER_SIMILARITY,
        MATRIX_FACTORIZATION;
    }
}
