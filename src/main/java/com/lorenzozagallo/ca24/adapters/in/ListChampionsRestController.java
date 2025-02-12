package com.lorenzozagallo.ca24.adapters.in;

import com.lorenzozagallo.ca24.application.ListChampionsUseCase;
import com.lorenzozagallo.ca24.domain.model.Champion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* ListChampionsRestController: expõe um endpoint GET /champions para
listar todos os campeões */

@Tag(name = "Campeões", description = "Endpoints do domínio dos Campeões do LOL.")
@RestController
@RequestMapping("/champions")
public record ListChampionsRestController(ListChampionsUseCase useCase) {
    // `ListChampionsUseCase` é injetado automaticamente como dependência

    @GetMapping
    public List<Champion> findAllChampions() {
        return useCase.findAll();
        /* chama o caso de uso `findAll()` para buscar
         todos os campeões e retorna a lista */
    }
}
