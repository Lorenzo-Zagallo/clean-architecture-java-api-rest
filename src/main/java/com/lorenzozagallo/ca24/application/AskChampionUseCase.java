package com.lorenzozagallo.ca24.application;

import com.lorenzozagallo.ca24.domain.exception.ChampionNotFoundException;
import com.lorenzozagallo.ca24.domain.model.Champion;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;

/* AskChampionUseCase: essa classe representa um caso de uso
que lida com perguntas feitas a um campeão do jogo. */

// pode usar o @Service do spring
public record AskChampionUseCase(ChampionsRepository repository) {
    // classe que representa um caso de uso para interagir com os campeões
    // `ChampionsRepository` é injetado automaticamente devido ao uso de `record`

    public String askChampion(Long championId, String question) {
        // método que processa uma pergunta para um determinado campeão

        Champion champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));
        // busca o campeão no banco de dados pelo ID
        // caso o campeão não seja encontrado, lança a exceção `ChampionNotFoundException`

        String championContext = champion.generateContextByQuestion(question);
        // chama um método do Champion para gerar uma resposta baseada na pergunta

        // TODO: Evoluir a lógica de negócio para considerar a integração com IAs Generativas

        return championContext ;
        // retorna a resposta gerada
    }
}


/*
*
* Em vez de uma classe comum, foi usado um record,
* que é um recurso do Java 14+ que facilita
* a criação de classes imutáveis.
*
* Como record já fornece um construtor automático,
* não há necessidade de um construtor explícito.
*
* */