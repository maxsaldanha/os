package com.maxsaldanha.os.dtos;

import com.maxsaldanha.os.domain.Tecnico;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo é requerido")

    private String nome;
    /*A validação de CPF pode ser feito de N formas basicamente com @Column(unique = true, nullable = false); está a validação é realizada no banco de dados ou
    implementação de método na classe repository; está a validação é realizada antes do banco de dados*/
    @CPF
    @NotEmpty(message = "O campo é requerido")
    private String cpf;

    @NotEmpty(message = "O campo é requerido")
    private String telefone;

    public TecnicoDTO() {
    }

    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
