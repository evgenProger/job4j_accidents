package ru.job4j.accidents.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepository;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleService {

    private RuleRepository ruleRepository;

    public Collection<Rule> findAll() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> ruleFindById(int id) {
        return ruleRepository.ruleFindById(id);
    }


}
