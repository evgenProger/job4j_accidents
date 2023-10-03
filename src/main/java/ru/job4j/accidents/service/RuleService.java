package ru.job4j.accidents.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleDBRepository;

import java.util.Collection;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleService {

    private RuleDBRepository ruleDBRepository;

    public Collection<Rule> findAll() {
        return ruleDBRepository.findAll();
    }

    public Rule ruleFindById(int id) {
        return ruleDBRepository.ruleFindById(id);
    }

    public Set<Rule> getRuleSet(String[] ids) {
       return ruleDBRepository.getRuleSet(ids);
    }
}
