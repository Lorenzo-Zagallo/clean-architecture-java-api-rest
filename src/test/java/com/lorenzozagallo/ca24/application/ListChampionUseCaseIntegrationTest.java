package com.lorenzozagallo.ca24.application;

import com.lorenzozagallo.ca24.domain.model.Champion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ListChampionUseCaseIntegrationTest {

    @Autowired
    private ListChampionsUseCase listChampionsUseCase;

    @Test
    public void testListChampions() {
        List<Champion> championList = listChampionsUseCase.findAll();

        Assertions.assertEquals(12, championList.size());
    }
}
