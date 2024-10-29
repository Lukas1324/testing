package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import de.uniwue.jpp.testing.util.DataGenerator.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.uniwue.jpp.testing.interfaces.Order.createOrder;
import static de.uniwue.jpp.testing.util.DataGenerator.createDishSets;
import static de.uniwue.jpp.testing.util.DataGenerator.createGuestMocks;
import static de.uniwue.jpp.testing.util.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestOrder extends AbstractTestClass<Order> {

    @Test
    public void testConstructorValidArguments(){

        try {
            Guest guest1 = mock(Guest.class);
            when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
            when(guest1.getName()).thenReturn("Guest_1");

            Guest guest2 = mock(Guest.class);
            when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
            when(guest2.getName()).thenReturn("Guest_2");

            Guest guest3 = mock(Guest.class);
            when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
            when(guest3.getName()).thenReturn("Guest_3");

            Guest guest4 = mock(Guest.class);
            when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
            when(guest4.getName()).thenReturn("Guest_4");

            Guest guest5 = mock(Guest.class);
            when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
            when(guest5.getName()).thenReturn("Guest_5");


            Dish[] dishes = new Dish[4];
            dishes[0] = mock(Dish.class);
            dishes[1] = mock(Dish.class);
            dishes[2] = mock(Dish.class);
            dishes[3] = mock(Dish.class);

            when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
            when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
            when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
            when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

            when(dishes[0].getBasePrice()).thenReturn(1.2);
            when(dishes[1].getBasePrice()).thenReturn(1.4);
            when(dishes[2].getBasePrice()).thenReturn(1.5);
            when(dishes[3].getBasePrice()).thenReturn(1.6);

            when(dishes[0].getName()).thenReturn("Lalaaaa");
            when(dishes[1].getName()).thenReturn("Lalaaa");
            when(dishes[2].getName()).thenReturn("Lalaa");
            when(dishes[3].getName()).thenReturn("Lala");

            ArrayList<Dish> list1 = new ArrayList<>();
            ArrayList<Dish> list2 = new ArrayList<>();
            ArrayList<Dish> list3 = new ArrayList<>();
            ArrayList<Dish> list4 = new ArrayList<>();
            ArrayList<Dish> list5 = new ArrayList<>();

            list1.add(dishes[0]);
            list2.add(dishes[1]);
            list3.add(dishes[2]);
            list4.add(dishes[3]);
            list5.add(dishes[0]);
            list5.add(dishes[1]);
            list5.add(dishes[3]);



            Order order1 = construct(guest1, list1);
            Order order2 = construct(guest2, list2);
            Order order3 = construct(guest3, list3);
            Order order4 = construct(guest4, list4);
            Order order5 = construct(guest5, list5);

        }catch (Exception e){
            throw new AssertionError("Testing valid arguments for Order constructor: " + e.getMessage());
        }






    }

    @Test
    public void testConstructorInvalidArguments(){

        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");


        ArrayList<Dish> list1 = new ArrayList<>();
        list1.add(mock(Dish.class));

        assertThrowsWithMessage(IllegalArgumentException.class,
                                () -> construct(guest1, null),
                                "Dish list must not be null!",
                                "Testing invalid dishes argument for Order constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(guest1, new ArrayList<>()),
                "Dish list must not be empty!",
                "Testing invalid dishes argument for Order constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(null, list1),
                "Guest must not be null!",
                "Testing invalid guest argument for Order constructor");
    }

    @Test
    public void testGetDishes(){
        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(1.2);
        when(dishes[1].getBasePrice()).thenReturn(1.4);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(1.6);

        when(dishes[0].getName()).thenReturn("Lalaaaa");
        when(dishes[1].getName()).thenReturn("Lalaaa");
        when(dishes[2].getName()).thenReturn("Lalaa");
        when(dishes[3].getName()).thenReturn("Lala");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);



        Order order1 = construct(guest1, list1);
        Order order2 = construct(guest2, list2);
        Order order3 = construct(guest3, list3);
        Order order4 = construct(guest4, list4);
        Order order5 = construct(guest5, list5);

        assertListsEqualInAnyOrder(order1.getDishes(), list1, "Incorrect list returned by getDishes()");
        assertListsEqualInAnyOrder(order2.getDishes(), list2, "Incorrect list returned by getDishes()");
        assertListsEqualInAnyOrder(order3.getDishes(), list3, "Incorrect list returned by getDishes()");
        assertListsEqualInAnyOrder(order4.getDishes(), list4, "Incorrect list returned by getDishes()");
        assertListsEqualInAnyOrder(order5.getDishes(), list5, "Incorrect list returned by getDishes()");

        assertListIsUnmodifiable(order1.getDishes(), "List returned by getDishes() is modifiable");
        assertListIsUnmodifiable(order2.getDishes(), "List returned by getDishes() is modifiable");
        assertListIsUnmodifiable(order3.getDishes(), "List returned by getDishes() is modifiable");
        assertListIsUnmodifiable(order4.getDishes(), "List returned by getDishes() is modifiable");
        assertListIsUnmodifiable(order5.getDishes(), "List returned by getDishes() is modifiable");
    }

    @Test
    public void testGetGuest(){
        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(1.2);
        when(dishes[1].getBasePrice()).thenReturn(1.4);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(1.6);

        when(dishes[0].getName()).thenReturn("Lalaaaa");
        when(dishes[1].getName()).thenReturn("Lalaaa");
        when(dishes[2].getName()).thenReturn("Lalaa");
        when(dishes[3].getName()).thenReturn("Lala");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);



        Order order1 = construct(guest1, list1);
        Order order2 = construct(guest2, list2);
        Order order3 = construct(guest3, list3);
        Order order4 = construct(guest4, list4);
        Order order5 = construct(guest5, list5);

        assertEquals(order1.getGuest(), guest1, "Incorrect guest returned by getGuest()");
        assertEquals(order2.getGuest(), guest2, "Incorrect guest returned by getGuest()");
        assertEquals(order3.getGuest(), guest3, "Incorrect guest returned by getGuest()");
        assertEquals(order4.getGuest(), guest4, "Incorrect guest returned by getGuest()");
        assertEquals(order5.getGuest(), guest5, "Incorrect guest returned by getGuest()");
    }

    @Test
    public void testCalculatePrice(){
        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(0.5);
        when(dishes[1].getBasePrice()).thenReturn(1.0);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(2.0);

        when(dishes[0].getName()).thenReturn("Dish_1");
        when(dishes[1].getName()).thenReturn("Dish_2");
        when(dishes[2].getName()).thenReturn("Dish_3");
        when(dishes[3].getName()).thenReturn("Dish_4");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);

        String stringFehlermeldung = "";

        Order order1 = construct(guest1, list1);
        for (Dish dish: list1){
            stringFehlermeldung += dish.getDishType() + ", " + (dish.getBasePrice()) + "€\n";
        }
        stringFehlermeldung += "\n";
        assertEquals(order1.calculatePrice(), 0.5, 0.01,
                "Created order containing these dishes:\n\n" + stringFehlermeldung + "\nGuestType was " + guest1.getGuestType() +"\nIncorrect price returned by calculatePrice()");
        stringFehlermeldung = "";


        Order order2 = construct(guest2, list2);
        for (Dish dish: list2){
            stringFehlermeldung += dish.getDishType() + ", " + dish.getBasePrice() + "€\n";
        }
        stringFehlermeldung += "\n";
        assertEquals(order2.calculatePrice(), 0.8, 0.01,
                "Created order containing these dishes:\n\n" + stringFehlermeldung + "\nGuestType was " + guest1.getGuestType() +"\nIncorrect price returned by calculatePrice()");
        stringFehlermeldung = "";

        Order order3 = construct(guest3, list3);
        for (Dish dish: list3){
            stringFehlermeldung += dish.getDishType() + ", " + dish.getBasePrice() + "€\n";
        }
        stringFehlermeldung += "\n";
        assertEquals(order3.calculatePrice(), 0.9, 0.01,
                "Created order containing these dishes:\n\n" + stringFehlermeldung + "\nGuestType was " + guest1.getGuestType() +"\nIncorrect price returned by calculatePrice()");
        stringFehlermeldung = "";

        Order order4 = construct(guest4, list4);
        for (Dish dish: list4){
            stringFehlermeldung += dish.getDishType() + ", " + dish.getBasePrice() + "€\n";
        }
        stringFehlermeldung += "\n";
        assertEquals(order4.calculatePrice(), 2.0, 0.01,
                "Created order containing these dishes:\n\n" + stringFehlermeldung + "\nGuestType was " + guest1.getGuestType() +"\nIncorrect price returned by calculatePrice()");
        stringFehlermeldung = "";

        Order order5 = construct(guest5, list5);
        for (Dish dish: list5){
            stringFehlermeldung += dish.getDishType() + ", " + dish.getBasePrice() + "€\n";
        }
        stringFehlermeldung += "\n";
        assertEquals(order5.calculatePrice(), 1.68, 0.01,
                "Created order containing these dishes:\n\n" + stringFehlermeldung + "\nGuestType was " + guest1.getGuestType() +"\nIncorrect price returned by calculatePrice()");
        stringFehlermeldung = "";

    }

    @Test
    public void testEquals(){
        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(1.2);
        when(dishes[1].getBasePrice()).thenReturn(1.4);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(1.6);

        when(dishes[0].getName()).thenReturn("Lalaaaa");
        when(dishes[1].getName()).thenReturn("Lalaaa");
        when(dishes[2].getName()).thenReturn("Lalaa");
        when(dishes[3].getName()).thenReturn("Lala");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);




        Order order1 = construct(guest1, list1);
        Order order2 = construct(guest2, list2);
        Order order3 = construct(guest3, list3);
        Order order4 = construct(guest4, list4);
        Order order5 = construct(guest5, list5);

        //5 gleiche Bestellungen gleicher Gast und Gerichtliste
        assertEquals(order1,order1,"Two orders with identical guests and dishes should be equal");
        assertEquals(order2,order2,"Two orders with identical guests and dishes should be equal");
        assertEquals(order3,order3,"Two orders with identical guests and dishes should be equal");
        assertEquals(order4,order4,"Two orders with identical guests and dishes should be equal");
        assertEquals(order5,order5,"Two orders with identical guests and dishes should be equal");

        //5 Gleicher Gast unterschiedlich sortierte Gerichtliste
        assertEquals(construct(guest1, Arrays.asList(dishes[0], dishes[1])), construct(guest1, Arrays.asList(dishes[1], dishes[0])), "Two orders with identical dishes (which may be out of order...) should be equal");
        assertEquals(construct(guest2, Arrays.asList(dishes[2], dishes[1])), construct(guest2, Arrays.asList(dishes[1], dishes[2])), "Two orders with identical dishes (which may be out of order...) should be equal");
        assertEquals(construct(guest3, Arrays.asList(dishes[3], dishes[1])), construct(guest3, Arrays.asList(dishes[1], dishes[3])), "Two orders with identical dishes (which may be out of order...) should be equal");
        assertEquals(construct(guest4, Arrays.asList(dishes[0], dishes[2])), construct(guest4, Arrays.asList(dishes[2], dishes[0])), "Two orders with identical dishes (which may be out of order...) should be equal");
        assertEquals(construct(guest5, Arrays.asList(dishes[0], dishes[3])), construct(guest5, Arrays.asList(dishes[3], dishes[0])), "Two orders with identical dishes (which may be out of order...) should be equal");

        //3 Unterschiedliche Gäste und Gerichtliste
        assertNotEquals(construct(guest1,list1),construct(guest5, list1), "Two orders with different guests should not be equal");
        assertNotEquals(construct(guest1, list1), construct(guest1,list2),"Two orders containing different dishes should not be equal");
        assertNotEquals(construct(guest1,list1),construct(guest5,list2), "Two orders with different guests and dishes should not be equal");


    }

    @Test
    public void testHashCode(){


        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(1.2);
        when(dishes[1].getBasePrice()).thenReturn(1.4);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(1.6);

        when(dishes[0].getName()).thenReturn("Dish_1");
        when(dishes[1].getName()).thenReturn("Dish_2");
        when(dishes[2].getName()).thenReturn("Dish_3");
        when(dishes[3].getName()).thenReturn("Dish_4");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);




        Order order1 = construct(guest1, list1);
        Order order2 = construct(guest2, list2);
        Order order3 = construct(guest3, list3);
        Order order4 = construct(guest4, list4);
        Order order5 = construct(guest5, list5);



        ///5 gleiche Bestellungen
        assertEquals(order1.hashCode(), construct(guest1, list1).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");
        assertEquals(order2.hashCode(), construct(guest2, list2).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");
        assertEquals(order3.hashCode(), construct(guest3, list3).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");
        assertEquals(order4.hashCode(), construct(guest4, list4).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");
        assertEquals(order5.hashCode(), construct(guest5, list5).hashCode(), "Two Orders with identical guests and dishes should have the same hash code");

        ///5 gleiche aber unterschiedliche soriterung
        assertEquals(construct(guest1, Arrays.asList(dishes[0], dishes[1], dishes[2])), construct(guest1, Arrays.asList(dishes[1], dishes[0], dishes[2])), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");
        assertEquals(construct(guest2, Arrays.asList(dishes[0], dishes[2])), construct(guest2, Arrays.asList(dishes[2], dishes[0])), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");
        assertEquals(construct(guest3, Arrays.asList(dishes[0], dishes[3])), construct(guest3, Arrays.asList(dishes[3], dishes[0])), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");
        assertEquals(construct(guest4, Arrays.asList(dishes[3], dishes[1])), construct(guest4, Arrays.asList(dishes[1], dishes[3])), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");
        assertEquals(construct(guest5, Arrays.asList(dishes[2], dishes[1])), construct(guest5, Arrays.asList(dishes[1], dishes[2])), "Two Orders with identical dishes (which may be out of order...) should have the same hash code");


    }


    @Test
    public void testToString(){
        Guest guest1 = mock(Guest.class);
        when(guest1.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest1.getName()).thenReturn("Guest_1");

        Guest guest2 = mock(Guest.class);
        when(guest2.getGuestType()).thenReturn(GuestType.STAFF);
        when(guest2.getName()).thenReturn("Guest_2");

        Guest guest3 = mock(Guest.class);
        when(guest3.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest3.getName()).thenReturn("Guest_3");

        Guest guest4 = mock(Guest.class);
        when(guest4.getGuestType()).thenReturn(GuestType.GUEST);
        when(guest4.getName()).thenReturn("Guest_4");

        Guest guest5 = mock(Guest.class);
        when(guest5.getGuestType()).thenReturn(GuestType.STUDENT);
        when(guest5.getName()).thenReturn("Guest_5");


        Dish[] dishes = new Dish[4];
        dishes[0] = mock(Dish.class);
        dishes[1] = mock(Dish.class);
        dishes[2] = mock(Dish.class);
        dishes[3] = mock(Dish.class);

        when(dishes[0].getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dishes[1].getDishType()).thenReturn(DishType.STARTER);
        when(dishes[2].getDishType()).thenReturn(DishType.OTHER);
        when(dishes[3].getDishType()).thenReturn(DishType.DESSERT);

        when(dishes[0].getBasePrice()).thenReturn(1.2);
        when(dishes[1].getBasePrice()).thenReturn(1.4);
        when(dishes[2].getBasePrice()).thenReturn(1.5);
        when(dishes[3].getBasePrice()).thenReturn(1.6);

        when(dishes[0].getName()).thenReturn("Lalaaaa");
        when(dishes[1].getName()).thenReturn("Lalaaa");
        when(dishes[2].getName()).thenReturn("Lalaa");
        when(dishes[3].getName()).thenReturn("Lala");

        ArrayList<Dish> list1 = new ArrayList<>();
        ArrayList<Dish> list2 = new ArrayList<>();
        ArrayList<Dish> list3 = new ArrayList<>();
        ArrayList<Dish> list4 = new ArrayList<>();
        ArrayList<Dish> list5 = new ArrayList<>();

        list1.add(dishes[0]);
        list2.add(dishes[1]);
        list3.add(dishes[2]);
        list4.add(dishes[3]);
        list5.add(dishes[0]);
        list5.add(dishes[1]);
        list5.add(dishes[3]);




        Order order1 = construct(guest1, list1);
        Order order2 = construct(guest2, list2);
        Order order3 = construct(guest3, list3);
        Order order4 = construct(guest4, list4);
        Order order5 = construct(guest5, list5);

        assertEquals(order1.toString(), "Order:\n\t- Lalaaaa\n\n\tName: Guest_1 | Total price: 1.2€","Incorrect string representation returned by toString()");
    }

}
