package com.lorenzozagallo.ca24.domain.ports;

import com.lorenzozagallo.ca24.domain.model.Champion;

import java.util.List;
import java.util.Optional;

/* ChampionsRepository: define um contrato para acessar os dados dos campeões.
 essa interface é implementada posteriormente por ChampionsJdbcRepository */

public interface ChampionsRepository {

    List<Champion> findAll();

    Optional<Champion> findById(Long id);
}