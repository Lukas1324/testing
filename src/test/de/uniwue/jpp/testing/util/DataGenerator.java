package de.uniwue.jpp.testing.util;

import de.uniwue.jpp.testing.Implementierungen.MyGuest;
import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataGenerator {

    public static List<String> createGuestNames(int amount){
        if (amount < 1){
            return Collections.emptyList();
        }
        int zähler = 1;
        List<String> guestList = new ArrayList<>();
        for (int i = 0; i<amount; i++){
            guestList.add("Guest_" + zähler);
            zähler++;
        }
        return Collections.unmodifiableList(guestList);
    }

    public static List<GuestType> createGuestTypes(int staff, int student, int guest){
        if (staff < 0 || student < 0 || guest < 0){
            return Collections.emptyList();
        }
        List<GuestType> guestTypes = new ArrayList<>();
        for (int i = 0; i<staff; i++){
            guestTypes.add(GuestType.STAFF);
        }
        for (int i = 0; i<student; i++){
            guestTypes.add(GuestType.STUDENT);
        }
        for (int i = 0; i<guest; i++){
            guestTypes.add(GuestType.GUEST);
        }
        return Collections.unmodifiableList(guestTypes);
    }

    public static List<String> createDishNames(int amount){
        //Copy GuestNames
        if (amount < 1){
            return Collections.emptyList();
        }
        List<String> guestList = new ArrayList<>();
        for (int i = 0; i<amount; i++){
            guestList.add("Dish_" + (i+1));
        }
        return Collections.unmodifiableList(guestList);
    }

    public static List<Double> createBasePrices(int amount){
        if (amount < 1){
            return Collections.emptyList();
        }
        List<Double> basePrices = new ArrayList<>();
        for (int i = 0; i<amount; i++){
            basePrices.add((i*0.5)+ 0.5);
        }
        return Collections.unmodifiableList(basePrices);
    }

    public static List<DishType> createDishTypes(int main, int starter, int dessert, int other){
        if (main < 0 || starter < 0 || dessert < 0 || other < 0){
            return Collections.emptyList();
        }
        List<DishType> dishTypes = new ArrayList<>();
        for (int i = 0; i<main;i++){
            dishTypes.add(DishType.MAIN_DISH);
        }
        for (int i = 0; i<starter;i++){
            dishTypes.add(DishType.STARTER);
        }
        for (int i = 0; i<dessert; i++){
            dishTypes.add(DishType.DESSERT);
        }
        for (int i = 0; i<other; i++){
            dishTypes.add(DishType.OTHER);
        }
        return Collections.unmodifiableList(dishTypes);
    }

    public static List<Guest> createGuestMocks(int staff, int student, int guest){
        if (staff < 0 || student < 0 || guest < 0){
            return Collections.emptyList();
        }
        int zählerGuest = 1;
        List<Guest> guests = new ArrayList<>();
        for (int i = 0; i<staff; i++){
            Guest guest1 = mock(Guest.class);
            when(guest1.getName()).thenReturn("Guest_" + zählerGuest);
            when(guest1.getGuestType()).thenReturn(GuestType.STAFF);
            guests.add(guest1);
            zählerGuest++;
        }
        for (int i = 0; i<student; i++){
            Guest guest1 =  mock(Guest.class);
            when(guest1.getName()).thenReturn("Guest_" + zählerGuest);
            when(guest1.getGuestType()).thenReturn(GuestType.STUDENT);
            guests.add(guest1);
            zählerGuest++;
        }
        for (int i = 0; i<guest; i++){
            Guest guest1 = mock(Guest.class);
            when(guest1.getName()).thenReturn("Guest_" + zählerGuest);
            when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
            guests.add(guest1);
            zählerGuest++;
        }
        return  Collections.unmodifiableList(guests);

    }

    public static List<Dish> createDishMocks(int main, int starter, int dessert, int other){
        if (main < 0 || starter < 0 || dessert < 0 || other < 0){
            return Collections.emptyList();
        }
        int zählerGuest = 1;
        List<Dish> dishes = new ArrayList<>();
        List<Double> prices = createBasePrices(main+starter+dessert+other);
        int zählerPrices = 0;
        for (int i = 0; i<main; i++){
            Dish dish = mock(Dish.class);
            when(dish.getName()).thenReturn("Dish_" + zählerGuest);
            when(dish.getDishType()).thenReturn(DishType.MAIN_DISH);
            when(dish.getBasePrice()).thenReturn(prices.get(zählerPrices));
            dishes.add(dish);
            zählerGuest++;
            zählerPrices++;
        }
        for (int i = 0; i<starter; i++){
            Dish dish = mock(Dish.class);
            when(dish.getName()).thenReturn("Dish_" + zählerGuest);
            when(dish.getDishType()).thenReturn(DishType.STARTER);
            when(dish.getBasePrice()).thenReturn(prices.get(zählerPrices));
            dishes.add(dish);
            zählerGuest++;
            zählerPrices++;
        }
        for (int i = 0; i<dessert; i++){
            Dish dish = mock(Dish.class);
            when(dish.getName()).thenReturn("Dish_" + zählerGuest);
            when(dish.getDishType()).thenReturn(DishType.DESSERT);
            when(dish.getBasePrice()).thenReturn(prices.get(zählerPrices));
            dishes.add(dish);
            zählerGuest++;
            zählerPrices++;
        }
        for (int i = 0; i<other; i++){
            Dish dish = mock(Dish.class);
            when(dish.getName()).thenReturn("Dish_" + zählerGuest);
            when(dish.getDishType()).thenReturn(DishType.OTHER);
            when(dish.getBasePrice()).thenReturn(prices.get(zählerPrices));
            dishes.add(dish);
            zählerGuest++;
            zählerPrices++;
        }
        return Collections.unmodifiableList(dishes);
    }

    public static List<List<Dish>> createDishSets(int[][] data){
        if (data.length < 1){
            return Collections.emptyList();
        }
        List<List<Dish>> listOfDishList = new ArrayList<>();
        for (int i = 0; i< data.length; i++){
            if (data[i].length < 4){
                throw new IllegalArgumentException("Inner array too small!");
            }
            listOfDishList.add(createDishMocks(data[i][0],data[i][1],data[i][2],data[i][3]));
        }
        return Collections.unmodifiableList(listOfDishList);
    }

    public static Order createOrderMock(int index){
        if (index < 1){
            return null;
        }

        Guest guest = mock(Guest.class);
        when(guest.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest.getName()).thenReturn("Guest_" + index);

        List<Dish> dishes = new ArrayList<>();
        for (int i = 1; i <= index; i++) {
            Dish dish = mock(Dish.class);
            when(dish.getDishType()).thenReturn(DishType.MAIN_DISH);
            when(dish.getName()).thenReturn("Dish_" + i);
            when(dish.getBasePrice()).thenReturn(i * 0.5);
            dishes.add(dish);
        }

        Order order = mock(Order.class);
        when(order.getGuest()).thenReturn(guest);
        when(order.getDishes()).thenReturn(dishes);

        double calcPrice = 0.0;
        for (Dish dish: dishes){
            calcPrice += dish.getBasePrice();
        }

        when(order.calculatePrice()).thenReturn(calcPrice);

        return order;
    }

}
