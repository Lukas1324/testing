package de.uniwue.jpp.testing.Implementierungen;


import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.DayManager;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Dish dish = Dish.createDish("hallo", 1.2, DishType.MAIN_DISH);
        DayManager manager = new MyDayManager(LocalDate.now(), 3, 2);
        Order order1 = Order.createOrder(Guest.createGuest("hsdfik", GuestType.GUEST), List.of(dish));
        Order order2 = Order.createOrder(Guest.createGuest("hsdioio", GuestType.GUEST), List.of(dish));
        Order order3 = Order.createOrder(Guest.createGuest("hlllaai", GuestType.GUEST), List.of(dish));
        manager.addOrder(order1, 1);
        manager.addOrder(order2, 1);
        Optional<Integer> x = manager.addOrder(order3, 1);
        int z = x.orElse(-1);
        System.out.println(z);

    }
}
