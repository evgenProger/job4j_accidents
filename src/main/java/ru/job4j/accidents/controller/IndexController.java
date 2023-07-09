package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.repository.AccidentRepository;

@Controller
public class IndexController {

    private final AccidentRepository accidentRepository;

    public IndexController(AccidentRepository accidentRepository) {
        this.accidentRepository = accidentRepository;
    }


    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("accidents", accidentRepository.findAll());
        return "index";
    }

    @GetMapping("/index")
    public String getAllAccidents(Model model) {
        model.addAttribute("accidents", accidentRepository.findAll());
        return "index";
    }
}
