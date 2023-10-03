package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentDBRepository;
import ru.job4j.accidents.repository.TypeDBRepository;

@Service
@AllArgsConstructor
public class AccidentService {

    private AccidentDBRepository accidentDBRepository;
    private TypeDBRepository typeDBRepository;


    public void save(Accident accident, String[] ids) {
        AccidentType accidentType = typeDBRepository.accidentTypeFindById(accident.getType().getId());
        accident.setType(accidentType);
        accidentDBRepository.save(accident, ids);
    }

    public void saveAccidentRule(Number accidentId, String[] ids) {
        accidentDBRepository.saveAccidentRule(accidentId, ids);
    }

    public Accident findById(int id) {
       return accidentDBRepository.accidentFindById(id);
    }

    public void update(Accident accident) {
        accidentDBRepository.updateAccident(accident);
    }
}
