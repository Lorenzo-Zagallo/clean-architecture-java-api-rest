package com.lorenzozagallo.ca24.domain.exception;

/* ChampionNotFoundException: essa é uma exceção personalizada que representa
um erro quando um campeão não é encontrado no banco de dados. */

// classe de exceção personalizada que estende RuntimeException
public class ChampionNotFoundException extends RuntimeException {

    // construtor que recebe o ID do campeão e gera uma mensagem personalizada
    public ChampionNotFoundException(Long championId) {
        super("Champion %d not found".formatted(championId));
    }
}
