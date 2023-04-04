package ru.pnzgu.fvt.moipvm.vi19.br2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.pnzgu.fvt.moipvm.vi19.br2.models.*;
import ru.pnzgu.fvt.moipvm.vi19.br2.security.PersonDetails;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.*;
import ru.pnzgu.fvt.moipvm.vi19.br2.util.Counter;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.pnzgu.fvt.moipvm.vi19.br2.util.FileUploadUtil;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.ActivityService;
import ru.pnzgu.fvt.moipvm.vi19.br2.services.CityService;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class HelloController {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    private final AdminService adminService;
    private final PersonDetailsService personDetailsService;
    private final CityService cityService;
    private final ActivityService activityService;
    private final GenderService genderService;
    private final EducationService educationService;
    private final MaritalService maritalService;
    private final Counter counter;

    @Autowired
    public HelloController(AdminService adminService, PersonDetailsService personDetailsService, CityService cityService, ActivityService activityService, GenderService genderService, EducationService educationService, MaritalService maritalService, Counter counter) {
        this.adminService = adminService;
        this.personDetailsService = personDetailsService;
        this.cityService = cityService;
        this.activityService = activityService;
        this.genderService = genderService;
        this.educationService = educationService;
        this.maritalService = maritalService;
        this.counter = counter;
    }

    @GetMapping
    public String showPeople(@ModelAttribute("person") Person person, Model model,
                             @ModelAttribute("city") City city,
                             @ModelAttribute("activity") Activity activity,
                             @ModelAttribute("gender") Gender gender,
                             @ModelAttribute("marital") Marital marital) {
//        model.addAttribute("people", personDetailsService.findAll());
        return "hello";
    }

    @GetMapping("/hello")
    public String sayHello(@ModelAttribute("person") Person person) {

//        model.addAttribute("personAll", personDetailsService.findAll());
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(@ModelAttribute("person") Person person) {
//        model.addAttribute("person", personDetailsService.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }

    @GetMapping("/{id}/show")
    public String show(Model model, @PathVariable("id") int id) {
        Person person = personDetailsService.findOne(id);
        model.addAttribute("person", person);

        Gender gender = genderService.findOne(person.getGender_id());
        model.addAttribute("gender", gender);

        City city = cityService.findOne(person.getCity_id());
        model.addAttribute("city", city);

        Activity activity = activityService.findOne(person.getActivity_id());
        model.addAttribute("activity", activity);

        Marital marital = maritalService.findOne(person.getMarital_id());
        model.addAttribute("marital", marital);

        Education education = educationService.findOne(person.getEducation_id());
        model.addAttribute("education", education);

        return "show";
    }

    @GetMapping("/users")
    public String users(@ModelAttribute("person") Person person) {
        return "users";
    }

    /**
     * Обрабатывает GET-запрос по адресу /info
     *
     * @return дополнительные данные - количество посещений и дата входа в формате {counter: $count, date: $date}
     */
    @GetMapping("/info")
    public String infoPage(@ModelAttribute("person") Person person, Model model) {
        String date = DATE_FORMAT.format(new Date(System.currentTimeMillis()));
        model.addAttribute("counter", counter);
        model.addAttribute("date", date);
        return "info";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("person") @Valid Person person, Model model,
                       @RequestParam("photo") MultipartFile multipartFile,
                       @PathVariable("id") int id) throws IOException {
        model.addAttribute("city", cityService.findAll());
        model.addAttribute("activity", activityService.findAll());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        person.setPhoto(fileName);
        Person savedUser = personDetailsService.update(id, person);
        String uploadDir = "src/main/resources/static/img";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "redirect:/{id}/show";
    }

}
