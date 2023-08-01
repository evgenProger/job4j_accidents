package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class RuleMemRepository implements RuleRepository {

    private AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    private RuleMemRepository() {
        save(new Rule(1, "Статья 1"));
        save(new Rule(2, "Статья 2"));
        save(new Rule(3, "Статья 3"));
    }


    @Override
    public Rule save(Rule rule) {
        rule.setId(nextId.incrementAndGet());
        rules.put(rule.getId(), rule);
        return rule;
    }

    @Override
    public Collection<Rule> findAll() {
        return rules.values();
    }

    @Override
    public Optional<Rule> ruleFindById(int id) {
        return Optional.ofNullable(rules.get(id));
    }
}
