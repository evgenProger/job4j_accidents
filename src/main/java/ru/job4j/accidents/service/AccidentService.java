package ru.job4j.accidents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

@Service
public class AccidentService {

    @Autowired private AccidentRepository accidentRepository;

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }
}
