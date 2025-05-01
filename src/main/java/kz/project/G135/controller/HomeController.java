package kz.project.G135.controller;


import kz.project.G135.db.*;
import kz.project.G135.db.DBManager;
import kz.project.G135.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute("mashinu", DBConnector.getAllCars());
        return "index";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        DBConnector.addCar(car);
        return "redirect:/";
    }

    @GetMapping(value = "/add-car")
    public String addCarPage(){
        return "add-car";
    }

    @GetMapping(value = "/details/{id}")
    public String showDetailsCar(Model model, @PathVariable long id){
        model.addAttribute("mashina", DBConnector.getCarById(id));
        return "details-page";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){
        DBConnector.updateCar(car);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam long id){
        DBConnector.deleteCar(id);
        return "redirect:/";
    }


}
