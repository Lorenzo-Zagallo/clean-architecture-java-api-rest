package com.lorenzozagallo.ca24.domain.ports;

import com.lorenzozagallo.ca24.domain.model.Champions;

import java.util.List;
import java.util.Optional;

public interface ChampionsRepository {

    List<Champions> findAll();

    Optional<Champions> findById(Long id);
}