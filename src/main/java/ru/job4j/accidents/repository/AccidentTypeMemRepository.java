package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMemRepository implements AccidentTypeRepository {

    private AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, AccidentType> accidentTypes = new ConcurrentHashMap<>();

    private AccidentTypeMemRepository() {
        save(new AccidentType(1, "Две машины"));
        save(new AccidentType(2, "Машина и человек"));
        save(new AccidentType(3, "Машина и велосипед"));
    }


    @Override
    public AccidentType save(AccidentType accidentType) {
        accidentType.setId(nextId.incrementAndGet());
        accidentTypes.put(accidentType.getId(), accidentType);
        return accidentType;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypes.values();
    }

    @Override
    public Optional<AccidentType> accidentTypeFindById(int id) {
        return Optional.ofNullable(accidentTypes.get(id));
    }
}
