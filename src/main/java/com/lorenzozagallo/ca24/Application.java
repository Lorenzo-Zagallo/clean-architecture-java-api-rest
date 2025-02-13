package com.lorenzozagallo.ca24;

import com.lorenzozagallo.ca24.application.AskChampionUseCase;
import com.lorenzozagallo.ca24.application.ListChampionsUseCase;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;
import com.lorenzozagallo.ca24.domain.ports.GenerativeAiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

// Application: essa é a classe principal da aplicação, responsável por inicializar o Spring Boot.

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ListChampionsUseCase provideListChampionsUseCase(ChampionsRepository repository) {
        return new ListChampionsUseCase(repository);
    }

    @Bean
    public AskChampionUseCase provideAskChampionUseCase(
            ChampionsRepository repository, GenerativeAiService genAiService) {
        return new AskChampionUseCase(repository, genAiService);
    }

}

/*
A anotação @SpringBootApplication marca essa classe como a principal do projeto,
onde o Spring Boot será inicializado.

Os métodos anotados com @Bean permitem a criação de instâncias dos casos de uso
(ListChampionsUseCase e AskChampionUseCase) e injetam automaticamente um
ChampionsRepository nelas.
*/
