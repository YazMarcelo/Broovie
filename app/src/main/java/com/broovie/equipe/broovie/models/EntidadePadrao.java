package com.broovie.equipe.broovie.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public abstract class EntidadePadrao implements Serializable {
    private Long code;
}