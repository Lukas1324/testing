package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.Implementierungen.MyDish;
import de.uniwue.jpp.testing.enums.DishType;

public interface Dish {

    public String getName();

    public double getBasePrice();

    public DishType getDishType();

    public static Dish createDish(String name, double basePrice, DishType type){
        if(name == null){
            throw new IllegalArgumentException("Name must not be null!");
        } else if (name.equals("")) {
            throw new IllegalArgumentException("Name must not be empty!");
        }
        if (basePrice <= 0.0){
            throw new IllegalArgumentException("BasePrice must not be zero or negative!");
        }
        if(type == null){
            throw new IllegalArgumentException("DishType must not be null!");
        }
        return new MyDish(name,basePrice,type);
    }
}
