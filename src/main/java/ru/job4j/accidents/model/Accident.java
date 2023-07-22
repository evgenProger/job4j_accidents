package ru.job4j.accidents.model;

import lombok.*;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Accident {
    @EqualsAndHashCode.Include
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
}
