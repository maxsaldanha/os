package com.maxsaldanha.os.repositories;

import com.maxsaldanha.os.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT obj from Pessoa obj WHERE obj.cpf =:cpf")
    Pessoa findByCPF (@Param("cpf") String cpf);

}
