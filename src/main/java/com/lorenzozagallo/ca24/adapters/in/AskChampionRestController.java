package com.lorenzozagallo.ca24.adapters.in;

import com.lorenzozagallo.ca24.application.AskChampionUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/* AskChampionRestController: expõe um endpoint POST /champions/{championId}/ask
para perguntar algo sobre um campeão específico */

// adiciona documentação para a API usando Swagger
@Tag(name = "Campeões", description = "Endpoints do domínio dos Campeões do LOL.")
// indica que essa classe é um controlador REST do Spring
@RestController
// define o caminho base para os endpoints desta classe
@RequestMapping("/champions")
public record AskChampionRestController(AskChampionUseCase useCase) {
     /* `AskChampionUseCase` é injetado automaticamente como
     dependência (record facilita isso) */


    /* define um endpoint que responde a requisições
     POST para "/champions/{championId}/ask"*/
    @PostMapping("/{championId}/ask")
    public AskChampionResponse askChampion(@PathVariable Long championId,
            @RequestBody AskChampionRequest request) {
        /* `@PathVariable` extrai o ID do campeão da URL
         `@RequestBody` recebe o JSON enviado na requisição */

        String answer = useCase.askChampion(championId, request.question);
        /* chama o caso de uso `askChampion`, passando
         o ID do campeão e a pergunta recebida */

        return new AskChampionResponse(answer);
        // retorna a resposta dentro de um objeto `AskChampionResponse`
    }

    // classe imutável que representa a requisição JSON (contém apenas a pergunta)
    public record AskChampionRequest(String question) { }

    // classe imutável que representa a resposta JSON (contém apenas a resposta)
    public record AskChampionResponse(String answer) { }
}
