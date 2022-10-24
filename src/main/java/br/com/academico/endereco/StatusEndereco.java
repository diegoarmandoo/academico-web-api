package br.com.academico.endereco;

public enum StatusEndereco {
    ATIVO,
    DESATIVO;

    public static StatusEndereco fromString(final String status){
        return StatusEndereco.valueOf(status);
    }
}
