package ru.job4j.accidents.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AccidentDBRepository implements AccidentRepository {
    private final JdbcTemplate jdbc;
    @Autowired private TypeDBRepository typeDBRepository;
    @Autowired private RuleDBRepository ruleDBRepository;

    public AccidentDBRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Accident save(Accident accident, String[] ids) {
        String sql = "insert into accidents (name, description, address, accidenttype_id) values (?, ?, ?, ?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType()
                                 .getId());
            return ps;
        }, keyHolder);
        Number key = (Number) keyHolder.getKeyList()
                                      .get(0)
                                      .get("id");

        accident.setId(key.intValue());
        saveAccidentRule(key, ids);


        return accident;
    }

    public void saveAccidentRule(Number accidentId, String[] ids) {
        for (String ruleId : ids) {
            int rule = Integer.parseInt(ruleId);
            jdbc.update("insert into accident_rules(accident_id, rule_id) values (?, ?)",
                    accidentId, rule);
        }
    }


    @Override
    public List<Accident> findAll() {
        return jdbc.query("select id, name, description, address, accidenttype_id  from accidents",
                (rs, row) -> {

                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("description"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType type = typeDBRepository.accidentTypeFindById(rs.getInt("accidenttype_id"));
                    accident.setType(type);
                    accident.setRules(ruleDBRepository.findByAccidentId(accident.getId()));

                    return accident;
                });
    }

    @Override
    public Accident accidentFindById(int id) {
        return jdbc.query("select id, name, description, address, accidenttype_id  from accidents where id = " + id,
                           (rs, row) -> {

                               Accident accident = new Accident();
                               accident.setId(rs.getInt("id"));
                               accident.setName(rs.getString("name"));
                               accident.setText(rs.getString("description"));
                               accident.setAddress(rs.getString("address"));
                               AccidentType type = typeDBRepository.accidentTypeFindById(rs.getInt("accidenttype_id"));
                               accident.setType(type);
                               accident.setRules(ruleDBRepository.findByAccidentId(accident.getId()));

                               return accident;
                           })
                   .stream()
                   .findFirst()
                   .orElse(null);
    }

    public void updateAccident(Accident accident) {
        String updateQuery = "update accidents SET name = ?, description = ?, address = ?, accidenttype_id = ? WHERE id = ?";

        jdbc.update(updateQuery,
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType()
                        .getId(),
                accident.getId()
        );
    }
}

