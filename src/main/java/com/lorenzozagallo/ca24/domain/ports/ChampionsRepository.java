package com.lorenzozagallo.ca24.domain.ports;

import com.lorenzozagallo.ca24.domain.model.Champion;

import java.util.List;
import java.util.Optional;

/* ChampionsRepository: define um contrato para acessar os dados dos campeões.
 essa interface é implementada posteriormente por ChampionsJdbcRepository */

public interface ChampionsRepository {

    // método para buscar todos os campeões no banco de dados
    List<Champion> findAll();

    /* método para buscar um campeão pelo ID (pode retornar
     um Optional vazio se não encontrar) */
    Optional<Champion> findById(Long id);
}