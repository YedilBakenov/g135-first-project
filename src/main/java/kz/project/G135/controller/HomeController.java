package kz.project.G135.controller;


import kz.project.G135.db.DBManager;
import kz.project.G135.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute("mashinu", DBManager.getCars());
        return "index";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        DBManager.addCar(car);
        return "redirect:/";
    }

    @GetMapping(value = "/add-car")
    public String addCarPage(){
        return "add-car";
    }


}
