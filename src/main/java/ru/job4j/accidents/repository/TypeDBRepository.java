package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;

@Repository
public class TypeDBRepository implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    public TypeDBRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public AccidentType save(AccidentType type) {
        jdbc.update("insert into accident_types (name) values (?)",
                type.getName());
        return type;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select id, name  from accident_types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    @Override
    public AccidentType accidentTypeFindById(int id) {
        return jdbc.query("select name from accident_types where id = " + id,
                           (rs, row) -> {
                               AccidentType accidentType = new AccidentType();
                               accidentType.setId(id);
                               accidentType.setName(rs.getString("name"));
                               return accidentType;

                           })
                   .stream()
                   .findFirst()
                   .orElse(null);
    }
}
