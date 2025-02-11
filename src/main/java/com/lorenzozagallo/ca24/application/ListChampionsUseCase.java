package com.lorenzozagallo.ca24.application;

import com.lorenzozagallo.ca24.domain.model.Champions;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;

import java.util.List;

// posso usar o @Service do spring
public record ListChampionsUseCase(ChampionsRepository championsRepository) {

    public List<Champions> findAll() {
        return championsRepository.findAll();
    }
}
