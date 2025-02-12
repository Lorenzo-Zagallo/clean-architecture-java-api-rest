package com.lorenzozagallo.ca24.application;

import com.lorenzozagallo.ca24.domain.model.Champion;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;

import java.util.List;

/* ListChampionsUseCase: esse caso de uso retorna uma
lista com todos os campeões do banco de dados. */

// pode usar o @Service do spring
public record ListChampionsUseCase(ChampionsRepository championsRepository) {
    // caso de uso para listar todos os campeões cadastrados
    // o `ChampionsRepository` é injetado automaticamente devido ao uso de `record`

    public List<Champion> findAll() {
        // método que retorna todos os campeões do banco de dados
        return championsRepository.findAll();
    }
}
