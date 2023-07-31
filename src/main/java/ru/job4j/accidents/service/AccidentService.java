package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentRepository;
import ru.job4j.accidents.repository.AccidentTypeRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentTypeRepository;

    public void save(Accident accident) {
        AccidentType accidentType = accidentTypeRepository.accidentTypeFindById(accident.getType()
                                                                                        .getId())
                                                          .get();

        accident.setType(accidentType);
        accidentRepository.save(accident);
    }

    public Optional<Accident> findById(int id) {
       return accidentRepository.findById(id);
    }

    public void update(Accident accident) {
        accidentRepository.update(accident);
    }
}
