package com.broovie.equipe.broovie.models;

import java.util.Date;

import lombok.Data;

@Data
public abstract class EntidadePadrao {
    private Long code;
    private Date dataCadastro;
    private Date dataAtualizado = null;
    private Date dataExcluido = null;
}