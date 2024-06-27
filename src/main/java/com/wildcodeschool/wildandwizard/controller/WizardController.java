package com.wildcodeschool.wildandwizard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.entity.Wizard;
import com.wildcodeschool.wildandwizard.repository.WizardRepository;
import java.util.Optional;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class WizardController {

    @Autowired
    private WizardRepository wizardRepository;
    
    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping("/wizard")
    public String getWizard(Model model, @RequestParam Long id) {

        Optional<Wizard> optionalWizard = wizardRepository.findById(id);

        if (optionalWizard.isPresent()) {
            model.addAttribute("wizard", optionalWizard.get());
        } else {
            model.addAttribute("wizard", new Wizard());
        }

        return "wizard";
    }

    @PostMapping("/createWizard")
    public String createWizard() {
        School school = new School();

        schoolRepository.save(school);

        Wizard wizard = new Wizard();

        wizard.setSchool(school);
        wizardRepository.save(wizard);

        return "wizard";
    }
    
}
