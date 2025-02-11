package com.lorenzozagallo.ca24.domain.model;

public record Champions(
        Long id,
        String name,
        String role,
        String lore,
        String imageUrl
) {
    /*public void teste() {
        Champions champ = new Champions(1L, "", "", "", "");
    }*/
}
// geralmente o record é usado como DTO (Data Transfer Object)