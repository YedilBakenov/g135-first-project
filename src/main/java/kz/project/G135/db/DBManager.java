package kz.project.G135.db;

import kz.project.G135.model.Car;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class DBManager {

    @Getter
    private static List<Car> cars = new ArrayList<>();

    private static long id = 5L;

//    static {
//        cars.add(new Car(1L, "Porshe Cayenne", 110000, 3.0, "white", "It is a luxury crossover SUV, and has been described as both a full-sized and a mid-sized vehicle"));
//        cars.add(new Car(2L, "BMW X5M", 130000, 5.0, "black", "It is a luxury crossover SUV, and has been described as both a full-sized and a mid-sized vehicle"));
//        cars.add(new Car(3L, "BMW X7M", 150000, 5.0, "white", "It is a luxury crossover SUV, and has been described as both a full-sized and a mid-sized vehicle"));
//        cars.add(new Car(4L, "MERCEDES S500", 190000, 5.5, "grey", "It is a luxury car"));
//    }


    public static void addCar(Car car) {
        car.setId(id);
        id++;
        cars.add(car);
    }

    public static Car getCarById(long id) {
        return cars.stream().filter(s -> s.getId()==id).findFirst().get();
    }

    public static void updateCar(Car car) {

        for(Car carFromBase: cars){
            if(carFromBase.getId()==car.getId()){
                carFromBase.setColor(car.getColor());
                carFromBase.setDescription(car.getDescription());
                carFromBase.setPrice(car.getPrice());
                carFromBase.setModel(car.getModel());
                carFromBase.setEngine(car.getEngine());
            }
        }

    }

    public static void deleteCar(long id) {
        cars.removeIf(s-> s.getId()==id);
    }
}
