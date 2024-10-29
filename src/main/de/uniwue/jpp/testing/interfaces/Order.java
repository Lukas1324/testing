package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.Implementierungen.MyOrder;

import java.util.List;

public interface Order {

    public double calculatePrice();

    public List<Dish> getDishes();

    public Guest getGuest();

    public static Order createOrder(Guest guest, List<Dish> dishes){
        if (guest == null){
            throw new IllegalArgumentException("Guest must not be null!");
        }
        if (dishes == null){
            throw new IllegalArgumentException("Dish list must not be null!");
        }
        if(dishes.isEmpty()){
            throw new IllegalArgumentException("Dish list must not be empty!");
        }
        return new MyOrder(guest,dishes);
    }
}
