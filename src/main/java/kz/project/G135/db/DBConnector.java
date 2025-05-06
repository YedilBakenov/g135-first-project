package kz.project.G135.db;

import kz.project.G135.model.Car;
import kz.project.G135.model.City;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBConnector {

    private static Connection connection;
    private static String url = "jdbc:postgresql://localhost:5432/g135";
    private static String login = "postgres";
    private static String password = "postgres";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Car> getAllCars(){

        List<Car> cars = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM cars c " +
                    "INNER JOIN cities ci " +
                    "ON c.city_id = ci.id ORDER BY c.id ASC");

            ResultSet resultSet = statement.executeQuery(); //Только когда тянем данные с таблицы

            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setColor(resultSet.getString("color"));
                car.setPrice(resultSet.getDouble("price"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setDescription(resultSet.getString("description"));

                City city = new City();
                city.setId(resultSet.getLong("city_id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setCode(resultSet.getString("code"));
                city.setTicker(resultSet.getString("ticker"));

                car.setCity(city);

                cars.add(car);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cars;
    }

    public static void addCar(Car car){

        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO cars " +
                    "(model, price, engine, color, city_id, description) VALUES (?, ?, ?, ?, ?, ?)");

            statement.setString(1, car.getModel());
            statement.setDouble(2, car.getPrice());
            statement.setDouble(3, car.getEngine());
            statement.setString(4, car.getColor());
            statement.setLong(5, car.getCity().getId());
            statement.setString(6, car.getDescription());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Car getCarById(long id){
        Car car = new Car();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM cars c " +
                    "INNER JOIN cities ci " +
                    "ON c.city_id = ci.id WHERE c.id=?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                car.setId(resultSet.getLong("id"));
                car.setColor(resultSet.getString("color"));
                car.setPrice(resultSet.getDouble("price"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setDescription(resultSet.getString("description"));

                City city = new City();
                city.setId(resultSet.getLong("city_id"));
                city.setCode(resultSet.getString("code"));
                city.setTicker(resultSet.getString("ticker"));
                city.setCityName(resultSet.getString("city_name"));

                car.setCity(city);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return car;
    }

    public static void updateCar(Car car){

        try {

            PreparedStatement statement = connection.prepareStatement("UPDATE cars " +
                    "SET color=?, description=?, price=?, model=?, engine=?, city_id=? WHERE id=?");

            statement.setString(1, car.getColor());
            statement.setString(2, car.getDescription());
            statement.setDouble(3, car.getPrice());
            statement.setString(4, car.getModel());
            statement.setDouble(5, car.getEngine());
            statement.setLong(6, car.getCity().getId());
            statement.setLong(7, car.getId());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void deleteCar(long id){

        try {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM cars WHERE id=?");
            statement.setLong(1, id);

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<City> getAllCity() {
        List<City> cities = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM cities");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                City city = new City();
                city.setId(resultSet.getLong("id"));
                city.setCityName(resultSet.getString("city_name"));
                city.setTicker(resultSet.getString("ticker"));
                city.setCode(resultSet.getString("code"));

                cities.add(city);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return  cities;
    }
}











