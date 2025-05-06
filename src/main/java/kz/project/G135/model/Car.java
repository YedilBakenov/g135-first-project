package kz.project.G135.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {

   private long id;
   private String model;
   private double price;
   private double engine;
   private String color;
   private String description;
   private City city;

}
