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
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;



    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        Collection<AccidentType> types = accidentTypeService.findAll();
        Collection<Rule> rules = ruleService.findAll();
        model.addAttribute("types", types);
        model.addAttribute("rules", rules);
        return "createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute("accident") Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        Set<Rule> ruleSet = ruleService.getRuleSet(ids);
        accident.setRules(ruleSet);
        accidentService.save(accident, ids);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidentService.findById(id);
        if (accident == null) {
            return "accidentNotFound";
        }
        model.addAttribute("accident", accident);
        return "update";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/";
    }
}
