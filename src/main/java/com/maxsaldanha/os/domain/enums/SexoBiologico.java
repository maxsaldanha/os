package com.maxsaldanha.os.domain.enums;

public enum SexoBiologico {

    MASCULINO (0,"Masculino"),
    FEMININO (1, "Feminino"),
    INTERSEXO (2,"Intersexo");

    private final Integer cod;
    private final String descricao;

    SexoBiologico(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SexoBiologico toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (SexoBiologico x : SexoBiologico.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Sexo biológico inválido" + cod);
    }
}
