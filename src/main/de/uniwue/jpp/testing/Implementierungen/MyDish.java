package de.uniwue.jpp.testing.Implementierungen;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.interfaces.Dish;

import java.util.Objects;

public class MyDish implements Dish {

    private final String name;
    private final double basePrice;
    private final DishType type;

    public MyDish(String name, double basePrice, DishType type) {
        this.name = name;
        this.basePrice = basePrice;
        this.type = type;
    }
    /*
    public static Dish createDish(String name, double basePrice, DishType type){
        if(name == null){
            throw new IllegalArgumentException("Name must not be null!");
        } else if (name.equals("")) {
            throw new IllegalArgumentException("Name must not be empty!");
        }
        if (basePrice <= 0){
            throw new IllegalArgumentException("BasePrice must not be zero or negative!");
        }
        if(type == null){
            throw new IllegalArgumentException("DishType must not be null!");
        }


        MyDish dish = new MyDish(name,basePrice,type);
        return dish;
    }

     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public DishType getDishType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDish myDish = (MyDish) o;
        return Objects.equals(name, myDish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " | Baseprice: " + basePrice + "€ | Type: " + type;
    }
}
