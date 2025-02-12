package com.lorenzozagallo.ca24.adapters.out;

import com.lorenzozagallo.ca24.domain.model.Champion;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/* ChampionsJdbcRepository implementa ChampionsRepository
e faz operações no banco de dados via JdbcTemplate */

// indica que essa classe é um repositório Spring, responsável pelo acesso aos dados
@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {
    // implementa a interface ChampionsRepository

    private final JdbcTemplate jdbcTemplate;
    // objeto do Spring que facilita a execução de consultas SQL no banco de dados

    private final RowMapper<Champion> championsRowMapper;
    // responsável por converter os resultados do banco de dados em objetos `Champion`

    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        // construtor com injeção de dependência do JdbcTemplate
        this.jdbcTemplate = jdbcTemplate;

        // mapeia os resultados do banco de dados para um objeto `Champion`
        this.championsRowMapper = ((rs, rowNum) -> new Champion(
                rs.getLong("id"),        // mapeia o campo "id" do banco para a propriedade id
                rs.getString("name"),    // mapeia o campo "name" para a propriedade name
                rs.getString("role"),    // mapeia o campo "role" para a propriedade role
                rs.getString("lore"),    // mapeia o campo "lore" para a propriedade lore
                rs.getString("image_url") // mapeia o campo "image_url" para a propriedade imageUrl
        ));
    }


    @Override
    public List<Champion> findAll() {
        // método que retorna a lista de todos os campeões do banco de dados
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", championsRowMapper);
        /* executa a query "SELECT * FROM CHAMPIONS" e usa `championsRowMapper`
         para transformar os resultados em uma lista de `Champion` */
    }

    @Override
    public Optional<Champion> findById(Long id) {
        // método que busca um campeão pelo ID
        String sql = "SELECT * FROM CHAMPIONS WHERE id = ?";
        // define a query SQL com um parâmetro dinâmico (o ID do campeão)
        List<Champion> champion = jdbcTemplate.query(sql, championsRowMapper, id);
        // executa a query, mapeando o resultado para uma lista de `Champion`
        return champion.stream().findFirst();
        // retorna o primeiro campeão da lista, caso exista, dentro de um `Optional`
    }
}
