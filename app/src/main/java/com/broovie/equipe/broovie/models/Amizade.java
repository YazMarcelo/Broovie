package com.broovie.equipe.broovie.models;

import lombok.*;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Amizade {
    private Long code;
    private Date dataSolicitacao;
    private Usuario solicitante;
    private Usuario solicitado;
    private Date dataConfirmacao;
    private Situacao situacao = Situacao.PENDENTE;
    public enum Situacao {
        PENDENTE,
        APROVADA,
        REJEITADA;
    }
}
