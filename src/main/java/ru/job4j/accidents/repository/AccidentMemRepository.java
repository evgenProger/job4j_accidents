package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMemRepository implements AccidentRepository {

    private static final AccidentMemRepository INSTANCE = new AccidentMemRepository();
    private int nextId = 1;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public static AccidentMemRepository getINSTANCE() {
        return INSTANCE;
    }

    private AccidentMemRepository() {
        save(new Accident(0, "ДТП1", "Описание1", "Адрес1"));
        save(new Accident(0, "ДТП2", "Описание2", "Адрес2"));
        save(new Accident(0, "ДТП3", "Описание3", "Адрес3"));
    }


    @Override
    public Accident save(Accident accident) {
        accident.setId(nextId++);
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }
}
