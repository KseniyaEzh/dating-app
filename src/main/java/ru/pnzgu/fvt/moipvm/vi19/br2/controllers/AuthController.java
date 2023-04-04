package ru.pnzgu.fvt.moipvm.vi19.br2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.Person;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.*;
import ru.pnzgu.fvt.moipvm.vi19.br2.util.Counter;
import ru.pnzgu.fvt.moipvm.vi19.br2.util.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final CityService cityService;
    private final ActivityService activityService;
    private final GenderService genderService;
    private final EducationService educationService;
    private final MaritalService maritalService;
    private final Counter counter;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator, CityService cityService, ActivityService activityService, GenderService genderService, EducationService educationService, MaritalService maritalService, Counter counter) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.cityService = cityService;
        this.activityService = activityService;
        this.genderService = genderService;
        this.educationService = educationService;
        this.maritalService = maritalService;
        this.counter = counter;
    }

    @GetMapping("/login")
    public String loginPage() {
        counter.increment();
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person, Model model) {
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());
        model.addAttribute("gender", genderService.findAll());
        model.addAttribute("marital", maritalService.findAll());
        model.addAttribute("education", educationService.findAll());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult, Model model) {

        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());
        model.addAttribute("gender", genderService.findAll());
        model.addAttribute("marital", maritalService.findAll());
        model.addAttribute("education", educationService.findAll());

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "/auth/registration";
        }

        counter.increment();

        registrationService.register(person);

        return "redirect:/auth/login";
    }
}
