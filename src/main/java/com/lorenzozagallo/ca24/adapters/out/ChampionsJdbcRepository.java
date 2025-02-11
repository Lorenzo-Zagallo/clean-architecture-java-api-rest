package com.lorenzozagallo.ca24.adapters.out;

import com.lorenzozagallo.ca24.domain.model.Champions;
import com.lorenzozagallo.ca24.domain.ports.ChampionsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChampionsJdbcRepository implements ChampionsRepository {

    // objeto que simplifica a execução de consultas SQL
    private final JdbcTemplate jdbcTemplate;

    // converte os resultados do banco em objetos Champions
    private final RowMapper<Champions> championsRowMapper;

    // injeção de dependência do JdbcTemplate no construtor
    public ChampionsJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        // define como os dados do banco serão mapeados para a entidade Champions
        this.championsRowMapper = ((rs, rowNum) -> new Champions(
                rs.getLong("id"),        // mapeia o campo "id" do banco para a propriedade id
                rs.getString("name"),    // mapeia o campo "name" para a propriedade name
                rs.getString("role"),    // mapeia o campo "role" para a propriedade role
                rs.getString("lore"),    // mapeia o campo "lore" para a propriedade lore
                rs.getString("image_url") // mapeia o campo "image_url" para a propriedade imageUrl
        ));
    }


    @Override
    public List<Champions> findAll() {
        return jdbcTemplate.query("SELECT * FROM CHAMPIONS", championsRowMapper);
    }

    @Override
    public Optional<Champions> findById(Long id) {
        String sql = "SELECT * FROM CHAMPIONS WHERE id = ?";
        Champions champion = jdbcTemplate.queryForObject(sql, championsRowMapper, id);
        return Optional.ofNullable(champion);
    }
}
