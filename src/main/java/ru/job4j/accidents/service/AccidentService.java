package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentRepository accidentRepository;

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
       return accidentRepository.findById(id);
    }

    public void update(Accident accident) {
        accidentRepository.update(accident);
    }
}
