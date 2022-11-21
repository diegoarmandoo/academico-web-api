package br.com.academico.endereco;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
public enum StatusEndereco {
    ATIVO,
    DESATIVO;

    public static StatusEndereco fromString(final String status){
        return StatusEndereco.valueOf(status);
    }
}
