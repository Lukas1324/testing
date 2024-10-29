package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import org.junit.jupiter.api.Test;

import static de.uniwue.jpp.testing.util.TestUtils.assertThrowsWithMessage;
import static org.junit.jupiter.api.Assertions.*;

public class TestDish extends AbstractTestClass<Dish> {

    @Test
    public void testConstructorValidArguments(){
        try {
            Dish dish1 = construct("asdf", 8.99, DishType.MAIN_DISH);
            Dish dish2 = construct("asst", 5.49, DishType.STARTER);
            Dish dish3 = construct("adfsdf", 3.99, DishType.DESSERT);
            Dish dish4 = construct("gfhfgh", 7.79, DishType.MAIN_DISH);
            Dish dish5 = construct("sadfsdf", 4.29, DishType.STARTER);
            Dish dish6 = construct("ertdsfg", 2.99, DishType.DESSERT);
            Dish dish7 = construct("dfghser", 9.99, DishType.MAIN_DISH);
            Dish dish8 = construct("cvbcb", 6.49, DishType.STARTER);
            Dish dish9 = construct("cvbcv", 4.99, DishType.OTHER);

            /*
            assertNotNull(dish1);
            assertNotNull(dish2);
            assertNotNull(dish3);
            assertNotNull(dish4);
            assertNotNull(dish5);
            assertNotNull(dish6);
            assertNotNull(dish7);
            assertNotNull(dish8);
            assertNotNull(dish9);

             */
        } catch (Exception e) {
            throw new AssertionError("Testing valid arguments for Dish constructor: " + e.getMessage());
        }
    }

    @Test
    public void testConstructorInvalidArguments(){
        assertThrowsWithMessage(IllegalArgumentException.class,
                ()-> construct(null, 8.34, DishType.MAIN_DISH),
                "Name must not be null!",
                "Testing invalid name argument for Dish constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                ()-> construct("", 9.70, DishType.STARTER),
                "Name must not be empty!",
                "Testing invalid name argument for Dish constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                ()-> construct("Huuhu", 0.0 , DishType.OTHER),
                "BasePrice must not be zero or negative!",
                "Testing invalid basePrice argument for Dish constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                ()-> construct("Huuhu", -1.4 , DishType.OTHER),
                "BasePrice must not be zero or negative!",
                "Testing invalid basePrice argument for Dish constructor");


        assertThrowsWithMessage(IllegalArgumentException.class,
                ()-> construct("Blabla", 2.94, null),
                "DishType must not be null!",
                "Testing invalid dishType argument for Dish constructor");
    }

    @Test
    public void testGetName(){
        Dish dish1 = construct("Mateee", 1.5,DishType.MAIN_DISH);
        Dish dish2 = construct("Matee", 1.3,DishType.DESSERT);
        Dish dish3 = construct("Mate", 1.4,DishType.STARTER);
        Dish dish4 = construct("MateTee", 1.1,DishType.OTHER);
        Dish dish5 = construct("MateTe", 1.2,DishType.STARTER);
        Dish dish6 = construct("CocoCola", 2.2,DishType.STARTER);
        Dish dish7 = construct("Cola", 2.1,DishType.STARTER);
        Dish dish8 = construct("Flammkuchen", 2.3,DishType.STARTER);
        Dish dish9 = construct("Beats", 3.4,DishType.STARTER);

        assertEquals(dish1.getName(), "Mateee","Incorrect name returned by getName()");
        assertEquals(dish2.getName(), "Matee","Incorrect name returned by getName()");
        assertEquals(dish3.getName(), "Mate","Incorrect name returned by getName()");
        assertEquals(dish4.getName(), "MateTee","Incorrect name returned by getName()");
        assertEquals(dish5.getName(), "MateTe","Incorrect name returned by getName()");
        assertEquals(dish6.getName(), "CocoCola","Incorrect name returned by getName()");
        assertEquals(dish7.getName(), "Cola","Incorrect name returned by getName()");
        assertEquals(dish8.getName(), "Flammkuchen","Incorrect name returned by getName()");
        assertEquals(dish9.getName(), "Beats","Incorrect name returned by getName()");
    }

    @Test
    public void testGetBasePrice(){
        Dish dish1 = construct("Mateee", 1.5,DishType.MAIN_DISH);
        Dish dish2 = construct("Matee", 1.3,DishType.DESSERT);
        Dish dish3 = construct("Mate", 1.4,DishType.STARTER);
        Dish dish4 = construct("MateTee", 1.1,DishType.OTHER);
        Dish dish5 = construct("MateTe", 1.2,DishType.STARTER);
        Dish dish6 = construct("CocoCola", 2.2,DishType.STARTER);
        Dish dish7 = construct("Cola", 2.1,DishType.STARTER);
        Dish dish8 = construct("Flammkuchen", 2.3,DishType.STARTER);
        Dish dish9 = construct("Beats", 3.4,DishType.STARTER);


        assertEquals(dish1.getBasePrice(), 1.5, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish2.getBasePrice(), 1.3, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish3.getBasePrice(), 1.4, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish4.getBasePrice(), 1.1, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish5.getBasePrice(), 1.2, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish6.getBasePrice(), 2.2, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish7.getBasePrice(), 2.1, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish8.getBasePrice(), 2.3, "Incorrect basePrice returned by getBasePrice()");
        assertEquals(dish9.getBasePrice(), 3.4, "Incorrect basePrice returned by getBasePrice()");
    }

    @Test
    public void testGetDishType(){
        Dish dish1 = construct("Mateee", 1.5,DishType.MAIN_DISH);
        Dish dish2 = construct("Matee", 1.3,DishType.DESSERT);
        Dish dish3 = construct("Mate", 1.4,DishType.STARTER);
        Dish dish4 = construct("MateTee", 1.1,DishType.MAIN_DISH);
        Dish dish5 = construct("MateTe", 1.2,DishType.OTHER);
        Dish dish6 = construct("CocoCola", 2.2,DishType.STARTER);
        Dish dish7 = construct("Cola", 2.1,DishType.STARTER);
        Dish dish8 = construct("Flammkuchen", 2.3,DishType.STARTER);
        Dish dish9 = construct("Beats", 3.4,DishType.STARTER);

        assertEquals(dish1.getDishType(), DishType.MAIN_DISH, "Incorrect dishType returned by getDishType()");
        assertEquals(dish2.getDishType(), DishType.DESSERT, "Incorrect dishType returned by getDishType()");
        assertEquals(dish3.getDishType(), DishType.STARTER, "Incorrect dishType returned by getDishType()");
        assertEquals(dish4.getDishType(), DishType.MAIN_DISH, "Incorrect dishType returned by getDishType()");
        assertEquals(dish5.getDishType(), DishType.OTHER, "Incorrect dishType returned by getDishType()");
        assertEquals(dish6.getDishType(), DishType.STARTER, "Incorrect dishType returned by getDishType()");
        assertEquals(dish7.getDishType(), DishType.STARTER, "Incorrect dishType returned by getDishType()");
        assertEquals(dish8.getDishType(), DishType.STARTER, "Incorrect dishType returned by getDishType()");
        assertEquals(dish9.getDishType(), DishType.STARTER, "Incorrect dishType returned by getDishType()");
    }

    @Test
    public void testEquals(){
        Dish dish1 = construct("Mate", 1.1, DishType.MAIN_DISH);
        Dish dish2 = construct("Mate", 1.4, DishType.DESSERT);
        Dish dish3 = construct("Mate", 1.5, DishType.OTHER);
        Dish dish4 = construct("Mate", 1.6, DishType.STARTER);
        Dish dish5 = construct("Mate", 1.7, DishType.MAIN_DISH);

        Dish dish6 = construct("Tea", 2.2, DishType.MAIN_DISH);
        Dish dish7 = construct("Tea", 2.4, DishType.DESSERT);
        Dish dish8 = construct("Tea", 2.5, DishType.OTHER);
        Dish dish9 = construct("Tea", 2.6, DishType.STARTER);
        Dish dish10 = construct("Tea", 2.7, DishType.MAIN_DISH);

        Dish dish11 = construct("Coffee", 3.3, DishType.STARTER);
        Dish dish12 = construct("Coffee", 3.5, DishType.OTHER);


        assertEquals(dish1, dish2, "Two dishes with identical names should be equal");
        assertEquals(dish3, dish4, "Two dishes with identical names should be equal");
        assertEquals(dish6, dish7, "Two dishes with identical names should be equal");
        assertEquals(dish9, dish8, "Two dishes with identical names should be equal");
        assertEquals(dish11, dish12, "Two dishes with identical names should be equal");


        assertNotEquals(dish1, dish6, "Two dishes with different names should not be equal");
        assertNotEquals(dish2, dish7, "Two dishes with different names should not be equal");
        assertNotEquals(dish3, dish8, "Two dishes with different names should not be equal");
        assertNotEquals(dish4, dish9, "Two dishes with different names should not be equal");
        assertNotEquals(dish5, dish10, "Two dishes with different names should not be equal");
        assertNotEquals(dish6, dish11, "Two dishes with different names should not be equal");
        assertNotEquals(dish7, dish12, "Two dishes with different names should not be equal");

    }

    @Test
    public void testHashCode(){
        Dish dish1 = construct("Mate", 1.1, DishType.MAIN_DISH);
        Dish dish2 = construct("Mate", 1.4, DishType.DESSERT);
        Dish dish3 = construct("Mate", 1.5, DishType.OTHER);
        Dish dish4 = construct("Mate", 1.6, DishType.STARTER);
        Dish dish5 = construct("Mate", 1.7, DishType.MAIN_DISH);

        Dish dish6 = construct("Tea", 2.2, DishType.MAIN_DISH);
        Dish dish7 = construct("Tea", 2.4, DishType.DESSERT);
        Dish dish8 = construct("Tea", 2.5, DishType.OTHER);
        Dish dish9 = construct("Tea", 2.6, DishType.STARTER);
        Dish dish10 = construct("Tea", 2.7, DishType.MAIN_DISH);

        assertEquals(dish1.hashCode(), dish2.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish1.hashCode(), dish3.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish1.hashCode(), dish4.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish1.hashCode(), dish5.hashCode(), "Two dishes with identical names should have the same hash code");

        assertEquals(dish2.hashCode(), dish3.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish2.hashCode(), dish4.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish2.hashCode(), dish5.hashCode(), "Two dishes with identical names should have the same hash code");

        assertEquals(dish3.hashCode(), dish4.hashCode(), "Two dishes with identical names should have the same hash code");
        assertEquals(dish3.hashCode(), dish5.hashCode(), "Two dishes with identical names should have the same hash code");

        assertEquals(dish4.hashCode(), dish5.hashCode(), "Two dishes with identical names should have the same hash code");
    }

    @Test
    public void testToString(){
        Dish dish1 = construct("Dish1", 10.99, DishType.MAIN_DISH);
        Dish dish2 = construct("Dish2", 6.50, DishType.DESSERT);
        Dish dish3 = construct("Dish3", 8.99, DishType.STARTER);
        Dish dish4 = construct("Dish4", 5.75, DishType.STARTER);
        Dish dish5 = construct("Dish5", 12.99, DishType.MAIN_DISH);
        Dish dish6 = construct("Dish6", 11.50, DishType.OTHER);
        Dish dish7 = construct("Dish7", 4.75, DishType.DESSERT);
        Dish dish8 = construct("Dish8", 7.25, DishType.STARTER);
        Dish dish9 = construct("Dish9", 9.99, DishType.MAIN_DISH);

        assertEquals("Dish1 | Baseprice: 10.99€ | Type: MAIN_DISH", dish1.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish2 | Baseprice: 6.5€ | Type: DESSERT", dish2.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish3 | Baseprice: 8.99€ | Type: STARTER", dish3.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish4 | Baseprice: 5.75€ | Type: STARTER", dish4.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish5 | Baseprice: 12.99€ | Type: MAIN_DISH", dish5.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish6 | Baseprice: 11.5€ | Type: OTHER", dish6.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish7 | Baseprice: 4.75€ | Type: DESSERT", dish7.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish8 | Baseprice: 7.25€ | Type: STARTER", dish8.toString(), "Incorrect string representation returned by toString()");
        assertEquals("Dish9 | Baseprice: 9.99€ | Type: MAIN_DISH", dish9.toString(), "Incorrect string representation returned by toString()");
    }

}
