package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {

    Accident save(Accident accident);
    Collection<Accident> findAll();
    Optional<Accident> findById(int id);
    void update(Accident accident);
}
