package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentDBRepository;

import java.util.List;

@Controller
@AllArgsConstructor
public class IndexController {

    private final AccidentDBRepository accidentDBRepository;


    @GetMapping(value = {"/", "/index"})
    public String getAll(Model model) {
        List<Accident> all = accidentDBRepository.findAll();
        model.addAttribute("accidents", all);
        return "index";
    }
}
