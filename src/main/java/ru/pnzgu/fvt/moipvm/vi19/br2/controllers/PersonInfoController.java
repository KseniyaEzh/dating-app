package ru.pnzgu.fvt.moipvm.vi19.br2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.PersonInfo;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.ActivityService;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.CityService;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.PersonInfoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class PersonInfoController {

    private final PersonInfoService personInfoService;
    private final CityService cityService;
    private final ActivityService activityService;


    @Autowired
    public PersonInfoController(PersonInfoService personInfoService, CityService cityService, ActivityService activityService) {
        this.personInfoService = personInfoService;
        this.cityService = cityService;
        this.activityService = activityService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("peopleInfo", personInfoService.findAll());
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());
        return "hello";
    }
//
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("personInfo", personInfoService.findOne(id));
//        return "profile/show";
//    }

    @GetMapping("/new")
    public String newPersonInfo(@ModelAttribute("personInfo") PersonInfo personInfo, Model model) {
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());
        return "profile/edit-profile";
    }

    @PostMapping()
    public String create(@ModelAttribute("personInfo") @Valid PersonInfo personInfo,
                         BindingResult bindingResult, Model model) {
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());
        if (bindingResult.hasErrors())
            return "profile/edit-profile";

        personInfoService.save(personInfo);
        return "redirect:/hello";
    }

//    @GetMapping("/{id}/edit-profile")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("personInfo", personInfoService.findOne(id));
//        return "profile/edit-profile";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("personInfo") @Valid PersonInfo personInfo, BindingResult bindingResult,
//                         @PathVariable("id") int id) {
//        if (bindingResult.hasErrors())
//            return "profile/edit-profile";
//
//        personInfoService.update(id, personInfo);
//        return "redirect:/hello";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        personInfoService.delete(id);
//        return "redirect:/hello";
//    }

}
