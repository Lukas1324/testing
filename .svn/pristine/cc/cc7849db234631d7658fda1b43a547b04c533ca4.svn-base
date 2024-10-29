package de.uniwue.jpp.testing.Implementierungen;

import de.uniwue.jpp.testing.enums.DiscountFactorProvider;
import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;


import java.util.*;

public class MyOrder implements Order {

    private final Guest guest;
    private final List<Dish> dishes;

    public MyOrder(Guest guest, List<Dish> dishes) {
        this.guest = guest;
        this.dishes = dishes;
    }
    /*
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
        MyOrder order = new MyOrder(guest, dishes);
        return order;
    }

     */

    @Override
    public double calculatePrice() {
        double tempPrice = 0.0;
        DishType dishe = null;
        boolean vorSpeise = false;
        boolean hauptgang = false;
        boolean nachspeise = false;
        for(Dish dish: dishes ){
            tempPrice += dish.getBasePrice();
            dishe = dish.getDishType();
            if ( dishe.equals(DishType.STARTER)){
                vorSpeise = true;
            } else if (dishe.equals(DishType.MAIN_DISH)) {
                hauptgang = true;
            } else if (dishe.equals(DishType.DESSERT)) {
                nachspeise = true;
            }

        }
        tempPrice = guest.getGuestType().getDiscountFactor() * tempPrice;
        tempPrice = Math.round(tempPrice * 100)/100.0;
        if (vorSpeise && hauptgang && nachspeise){
            tempPrice = tempPrice * 0.8;
        }

        return tempPrice;
    }

    @Override
    public List<Dish> getDishes() {
        return Collections.unmodifiableList(dishes);
    }

    @Override
    public Guest getGuest() {
        return guest;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyOrder myOrder = (MyOrder) o;
        return Objects.equals(guest, myOrder.guest) && (myOrder.getDishes().containsAll(this.dishes)) && this.dishes.containsAll(myOrder.getDishes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(guest, sortDishes(dishes));
    }


    @Override
    public String toString() {
        String tempString = "Order:\n";
        for(Dish dish: dishes){
            tempString += "\t- " + dish.getName() + "\n";
        }
        tempString += "\n\tName: " + guest.getName() +" | Total price: " + calculatePrice() + "€";
        return tempString;
    }

    public List<Dish> sortDishes(List<Dish> ds){
        List<Dish> tempDish = new ArrayList<>(ds);
        Collections.sort(tempDish, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                String tempNum1 = o1.getName().split("_")[1];
                String tempNum2 = o2.getName().split("_")[1];
                return Long.compare(Long.parseLong(tempNum1), Long.parseLong(tempNum2));
            }
        });

        return tempDish;
    }
}
