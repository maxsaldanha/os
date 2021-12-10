package com.maxsaldanha.os.repositories;

import com.maxsaldanha.os.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*Realiza a comunicanção com o banco*/
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
