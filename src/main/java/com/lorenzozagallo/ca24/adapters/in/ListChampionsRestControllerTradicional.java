/*
package com.lorenzozagallo.ca24.adapters.in;

import com.lorenzozagallo.ca24.application.ListChampionsUseCase;
import com.lorenzozagallo.ca24.domain.model.Champion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/champions")
public class ListChampionsRestControllerTradicional {

    private final ListChampionsUseCase useCase;

    public ListChampionsRestControllerTradicional(ListChampionsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<Champion> findAllChampions() {
        return useCase.findAll();
    }
}
*/
