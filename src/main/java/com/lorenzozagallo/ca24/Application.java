package com.lorenzozagallo.ca24;

import com.lorenzozagallo.ca24.application.AskChampionUseCase;
import com.lorenzozagallo.ca24.application.ListChampionsUseCase;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/* Application: essa é a classe principal da aplicação,
responsável por inicializar o Spring Boot. */

// marca essa classe como uma aplicação Spring Boot
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // inicializa a aplicação Spring Boot
        SpringApplication.run(Application.class, args);
    }

    // criação de um Bean do caso de uso ListChampionsUseCase, injetando o repository
    @Bean
    public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
        return new ListChampionsUseCase(repository);
    }

    // criação de um Bean do caso de uso AskChampionUseCase, injetando o repository
    @Bean
    public AskChampionUseCase provideAskChampionUseCase(ChampionsRepository repository) {
        return new AskChampionUseCase(repository);
    }

}

/*
A anotação @SpringBootApplication marca essa classe como a principal do projeto,
onde o Spring Boot será inicializado.

Os métodos anotados com @Bean permitem a criação de instâncias dos casos de uso
(ListChampionsUseCase e AskChampionUseCase) e injetam automaticamente um
ChampionsRepository nelas.
*/
