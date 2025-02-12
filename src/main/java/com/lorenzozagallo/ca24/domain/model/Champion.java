package com.lorenzozagallo.ca24.domain.model;

/* Champion: essa classe modela um campeão do jogo, contendo informações
como ID, nome, função (role), história (lore) e URL da imagem. */


// geralmente o record é usado como DTO (Data Transfer Object) mas
// também pode ser usado para modelar entidades

public record Champion(
        Long id,            // Identificador único do campeão
        String name,        // Nome do campeão
        String role,        // Função do campeão (ex: Mago, Lutador, Assassino)
        String lore,        // História/lore do campeão
        String imageUrl     // URL da imagem do campeão
) {
    /*public void teste() {
        Champion champ = new Champion(1L, "", "", "", "");
    }*/

    // método para gerar um contexto textual baseado na pergunta do usuário
    public String generateContextByQuestion(String question) {
        return """
                Pergunta: %s
                Nome do Campeão: %s
                Função: %s
                Lore (História): %s
                """.formatted(question, this.name, this.role, this.lore);
    }
}



/*

Uso de record: Um record no Java é uma estrutura imutável que
facilita a criação de objetos DTO (Data Transfer Object),
mas aqui está sendo usada como entidade de domínio.

Método generateContextByQuestion: Cria um texto estruturado
com as informações do campeão com base na pergunta do usuário.

*/


