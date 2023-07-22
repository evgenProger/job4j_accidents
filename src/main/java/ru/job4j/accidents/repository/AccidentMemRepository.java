package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMemRepository implements AccidentRepository {

    private AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();



    private AccidentMemRepository() {
        save(new Accident(0, "ДТП1", "Описание1", "Адрес1", new AccidentType(0, "Две машины")));
        save(new Accident(0, "ДТП2", "Описание2", "Адрес2", new AccidentType(1, "Машина и человек")));
        save(new Accident(0, "ДТП3", "Описание3", "Адрес3", new AccidentType(2, "Машина и велосипед")));
    }


    @Override
    public Accident save(Accident accident) {
        accident.setId(nextId.incrementAndGet());
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(accidents.get(id));
    }

    @Override
    public void update(Accident accident) {
        int id = accident.getId();
        accidents.computeIfPresent(id, (key, existingAccident) -> {
            existingAccident.setName(accident.getName());
            existingAccident.setAddress(accident.getAddress());
            existingAccident.setText(accident.getText());
            existingAccident.setType(accident.getType());
            return existingAccident;
        });
    }
}
