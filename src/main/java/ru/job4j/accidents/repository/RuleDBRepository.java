package ru.job4j.accidents.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RuleDBRepository implements RuleRepository {
    private final JdbcTemplate jdbc;

    public RuleDBRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Rule save(Rule rule) {
        jdbc.update("insert into rules (rule_name) values (?)",
                rule.getName());
        return rule;
    }

    @Override
    public List<Rule> findAll() {
        return jdbc.query("select id,  rule_name  from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("rule_name"));
                    return rule;
                });
    }

    public Set<Rule> findByAccidentId(int accidentId) {
        return new HashSet<>(jdbc.query("select rule_id from accident_rules where accident_id = " + accidentId,
                (rs, row) -> ruleFindById(rs.getInt("rule_id"))));

    }

    @Override
    public Rule ruleFindById(int id) {
        return jdbc.query("select rule_name from rules where id = " + id,
                           (rs, row) -> {
                               Rule rule = new Rule();
                               rule.setId(id);
                               rule.setName(rs.getString("rule_name"));
                               return rule;

                           })
                   .stream()
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public Set<Rule> getRuleSet(String[] ids) {
        String selectQuery = "SELECT id, rule_name FROM rules WHERE id IN (" + String.join(",", ids) + ")";

        List<Rule> rules = jdbc.query(selectQuery, (rs, rowNum) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("rule_name"));
            return rule;
        });
        return new HashSet<>(rules);
    }
}



