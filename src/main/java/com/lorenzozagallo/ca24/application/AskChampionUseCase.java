package com.lorenzozagallo.ca24.application;

import com.lorenzozagallo.ca24.domain.exception.ChampionNotFoundException;
import com.lorenzozagallo.ca24.domain.model.Champion;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;
import com.lorenzozagallo.ca24.domain.ports.GenerativeAiService;

// AskChampionUseCase:
// essa classe representa um caso de uso que lida com perguntas feitas a um campeão do jogo

// @Service
public record AskChampionUseCase(ChampionsRepository repository, GenerativeAiService genAiRepository) {
    // classe que representa um caso de uso para interagir com os campeões
    // `ChampionsRepository` é injetado automaticamente devido ao uso de `record

    // método que processa uma pergunta para um determinado campeão
    public String askChampion(Long championId, String question) {

        // busca o campeão no banco de dados e caso o campeão não seja encontrado, lança uma exceção
        Champion champion = repository.findById(championId)
                .orElseThrow(() -> new ChampionNotFoundException(championId));

        /* implementando a lógica de negócio para a integração com a IA */

        // chama um método do Champion para gerar uma resposta baseada na pergunta
        String championContext = champion.generateContextByQuestion(question);

        // cria uma string que será passada como prompt na generativeAi
        String objective = """
                Atua como um assistente com a habilidade de se comportar como os Campeões do League of Legends (LOL).
                Responda perguntas incorporando a personalidade e estilo de um determinado Campeão.
                Segue a pergunta, o nome do Campeão e sua respectiva lore (história):
                
                """;

        return genAiRepository.generateContent(objective, championContext);
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