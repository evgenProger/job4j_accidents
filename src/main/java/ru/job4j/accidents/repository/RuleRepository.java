package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;


public interface RuleRepository {

    Rule save(Rule rule);

    Collection<Rule> findAll();

    Optional<Rule> ruleFindById(int id);

    Set<Rule> getRuleSet(String[] ids);
}
