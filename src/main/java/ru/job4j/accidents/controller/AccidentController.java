package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;
import ru.job4j.accidents.service.AccidentService;

import java.util.Collection;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;


    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        Collection<AccidentType> types = accidentService.findAll();
        model.addAttribute("types", types);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            return "accidentNotFound";
        }
        model.addAttribute("accident", accident.get());
        return "update";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
