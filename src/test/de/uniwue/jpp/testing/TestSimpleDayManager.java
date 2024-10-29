package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.Implementierungen.MyDayManager;
import de.uniwue.jpp.testing.enums.DishType;
import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.DayManager;
import de.uniwue.jpp.testing.interfaces.Dish;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.interfaces.Order;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import de.uniwue.jpp.testing.util.DataGenerator;
import de.uniwue.jpp.testing.util.TestUtils;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static de.uniwue.jpp.testing.util.DataGenerator.createOrderMock;
import static de.uniwue.jpp.testing.util.TestUtils.*;
import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSimpleDayManager extends AbstractTestClass<DayManager> {

    @Test
    public void testConstructorValidArguments(){
        try {
            construct(LocalDate.of(2025,11,1), 1,2);
            construct(LocalDate.of(2035,1,13), 2,4);
            construct(LocalDate.of(2044,12,14), 3,6);
            construct(LocalDate.of(2024,7,16), 4,1);
            construct(LocalDate.of(2054,5,21), 9,8);
        }catch (Exception e){
            throw new AssertionError("Testing valid arguments for DayManager constructor");
        }
    }

    @Test
    public void testConstructorInvalidArguments(){
        assertThrowsWithMessage(IllegalArgumentException.class,
                                () -> construct(null, 2, 2),
                                "Date must not be null!",
                                "Testing invalid date argument for DayManager constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(LocalDate.of(2007,1,4), 2, 2),
                "Date must not be before current date!",
                "Testing invalid date argument for DayManager constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(LocalDate.of(2025,2,3), 0, 3),
                "NumberOfTimeSlots must not be zero or negative!",
                "Testing invalid numberOfTimeSlots argument for DayManager constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(LocalDate.of(2025,2,3), 3, 0),
                "CapacityPerSlot must not be zero or negative!",
                "Testing invalid capacityPerSlot argument for DayManager constructor");

    }

    @Test
    public void testAddOrderInvalidArguments(){
        DayManager dayManager = construct(LocalDate.of(2025, Month.AUGUST,11), 2,3);
        Guest guest = mock(Guest.class);
        when(guest.getName()).thenReturn("Guest");
        when(guest.getGuestType()).thenReturn(GuestType.GUEST);

        Dish dish = mock(Dish.class);
        when(dish.getDishType()).thenReturn(DishType.MAIN_DISH);
        when(dish.getName()).thenReturn("Dish");
        when(dish.getBasePrice()).thenReturn(1.3);

        Order order = mock(Order.class);
        when(order.getGuest()).thenReturn(guest);
        when(order.getDishes()).thenReturn(Arrays.asList(dish));




        assertThrowsWithMessage(IllegalArgumentException.class,
                                () -> dayManager.addOrder(null, 0),
                                "Order must not be null!",
                                "Testing invalid order argument for addOrder()");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> dayManager.addOrder(order, -5),
                "Illegal slot index!",
                "Testing invalid slot argument for addOrder()");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> dayManager.addOrder(null),
                "Order must not be null!",
                "Testing invalid order argument for addOrder()");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> dayManager.addOrder(order, 3),
                "Illegal slot index!",
                "Testing invalid slot argument for addOrder()");

        dayManager.addOrder(order);
        assertThrowsWithMessage(IllegalArgumentException.class,
                                () -> {
                                    dayManager.addOrder(order, 1);
                                },
                                "Illegal order: This guest has already ordered!",
                                "Testing invalid order argument for addOrder()");






    }

    @Test
    public void testAddOrderWithSlotArgument() {
        /*

        int[][] scenarios = {{1, 1}, {1, 5}, {3, 2}, {5, 4}};
        for (int[] scenario : scenarios) {
            int slots = scenario[0];
            int capacity = scenario[1];
            DayManager dayManager = construct(LocalDate.of(2026, 5, 7), slots, capacity);
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("Executing addOrder sequence on a DayManager with ")
                    .append(slots).append(" time slots and ")
                    .append(capacity).append(" capacity per slot:\n");

            if (dayManager == null) {
                throw new AssertionError(stringBuilder + "Wrong return value");
            }

                List<Order> allOrders = new ArrayList<>();
                double sum = 0.0;

                for (int slotCounter = 0; slotCounter < slots; slotCounter++) {
                    List<Order> ordersInSlot = new ArrayList<>();
                    for (int orderNum = 1; orderNum <= capacity; orderNum++) {
                        Order order = createOrderMock(orderNum);
                        stringBuilder.append("Adding Order with preferred slot ").append(slotCounter).append("\n");
                        if (null == order) {
                            throw new AssertionError(stringBuilder.append("Wrong return value").toString());
                        }
                        sum += order.calculatePrice();
                        allOrders.add(order);
                        ordersInSlot.add(order);

                        Optional<Integer> slotNumber = dayManager.addOrder(order, slotCounter);
                        if (slotNumber.isPresent()) {
                            if ((slotNumber.get() != slotCounter)) {
                                throw new AssertionError(stringBuilder.append("excpected: ").append(slotCounter).append(" but got: ").append(slotNumber.get()).toString());
                            }
                        } else {
                            throw new AssertionError("Wrong return value");
                        }
                        List<Order> ordersAll = dayManager.getAllOrders();
                        List<Order> forSlotOrders = dayManager.getOrdersForSlot(slotCounter);
                        String mesage = stringBuilder + "Wrong return value";
                        if (null == ordersAll || null == forSlotOrders) {
                            throw new AssertionError(mesage);
                        }
                    }
                }
                try {
                    String mesage = stringBuilder.append("Wrong return value").toString();
                    Order extraOrder = createOrderMock(slots * capacity + 1);
                    if (null == extraOrder){
                        throw new AssertionError(mesage);
                    }
                    dayManager.addOrder(extraOrder, 0);
                    stringBuilder.append("adding Order with preferred slot ").append(slots).append("\n");
                }catch (IllegalArgumentException e){

                }

         */
        ref(3, 2, "Wrong return value", true, false, false);
        ref(1, 5, "Wrong return value", true, false, false);
        ref(5, 4, "Wrong return value", true, false, false);
        ref(1, 1, "Wrong return value", true, false, false);


    }




    @Test
    public void testAddOrderWithoutSlotArgument() {
        ref(3, 2, "Wrong return value", false, false, false);
        ref(1, 1, "Wrong return value", false, false, false);
    }



    @Test
    public void testGetOrdersForSlot(){
        throw new IllegalArgumentException();


        /*
        int[][] scenarios = {{1, 1}, {1, 5}, {3, 2}, {5, 4}};

        for (int[] scenario : scenarios) {
            DayManager manager = construct(LocalDate.now(), scenario[0], scenario[1]);
            for (int i = 0; i < scenario[0] * scenario[1]; i++) {
                Order order = createOrderMock(i + 1);
                manager.addOrder(order);
            }

            for (int slot = 0; slot < scenario[0]; slot++) {
                List<Order> ordersForSlot = manager.getOrdersForSlot(slot);
                assertNotNull(ordersForSlot, "Erwartet, dass eine Liste von Bestellungen zurückgegeben wird.");
                assertListIsUnmodifiable(ordersForSlot, "List returned by getOrdersForSlot() is modifiable");




        int[][][] testCases = {
                {{1, 1}, {1, 5}, {3, 2}, {5, 4}},
        };
        boolean isFirstRun = true;

        for (int[][] testCase : testCases) {
            for (int[] caseDetails : testCase) {
                int timeSlots = caseDetails[0];
                int capacity = caseDetails[1];
                StringBuilder log = new StringBuilder();
                log.append("Executing addOrder sequence with ")
                        .append(timeSlots)
                        .append(" time slots and ")
                        .append(capacity)
                        .append(" capacity per slot:\n");

                DayManager dayManager = construct(LocalDate.of(2044, 4, 11), timeSlots, capacity);
                List<List<Order>> expectedOrdersForSlots = new ArrayList<>(Collections.nCopies(timeSlots, new ArrayList<>()));

                for (int slot = 0; slot < timeSlots; slot++) {
                    List<Order> ordersForSlot = new ArrayList<>();
                    for (int count = 0; count < capacity; count++) {
                        Order order = mock(Order.class);
                        Guest guest = mock(Guest.class);
                        when(guest.getName()).thenReturn("Guest_" + slot + "_" + count);
                        when(guest.getGuestType()).thenReturn(GuestType.STAFF);
                        when(order.getGuest()).thenReturn(guest);

                        ordersForSlot.add(order);
                        expectedOrdersForSlots.set(slot, new ArrayList<>(ordersForSlot));

                        if (isFirstRun) {
                            log.append("adding Order with preferred slot ").append(slot).append("\n");
                            dayManager.addOrder(order, slot);
                        } else {
                            log.append("adding Order\n");
                            dayManager.addOrder(order);
                        }

                        for (int verifySlot = 0; verifySlot < timeSlots; verifySlot++) {
                            List<Order> ordersFromManager = dayManager.getOrdersForSlot(verifySlot);
                            List<Order> expectedOrders = expectedOrdersForSlots.get(verifySlot);

                            assertEquals(expectedOrders, ordersFromManager, log.append("Incorrect list returned by getOrdersForSlot() for slot ").append(verifySlot).toString());
                            assertListIsUnmodifiable(ordersFromManager, "List returned by getOrdersForSlot() is modifiable");
                        }
                    }
                }
            }
            isFirstRun = false;
        }

         */
    }

    @Test
    public void testGetAllOrders() {
        ref(5, 4, "Wrong return value", true, false, true);
        ref(1, 5, "Wrong return value", true, false, true);
        ref(1, 1, "Wrong return value", true, false, true);
        ref(3, 2, "Wrong return value", true, false, true);
        ref(1, 1, "Wrong return value", false,false, true);
        ref(3, 2, "Wrong return value", false, false, true);
        /*
        int[][] scenarios = {{1, 1}, {1, 5}, {3, 2}, {5, 4}};

        for (int[] scenario : scenarios) {
            DayManager manager = construct(LocalDate.now(), scenario[0], scenario[1]);
            for (int i = 0; i < scenario[0] * scenario[1]; i++) {
                Order order = createOrderMock(i + 1);
                manager.addOrder(order);
            }

            List<Order> allOrders = manager.getAllOrders();
            assertNotNull(allOrders, "Erwartet, dass eine Liste von allen Bestellungen zurückgegeben wird.");
            assertListIsUnmodifiable(allOrders, "List returned by getAllOrders() is modifiable");



        int[][][] testCases = {
                {{1, 1}, {1, 5}, {3, 2}, {5, 4}},
        };
        boolean isFirstRun = true;

        for (int[][] testCase : testCases) {
            for (int[] caseDetails : testCase) {

                int timeSlots = caseDetails[0];
                int capacity = caseDetails[1];
                DayManager dayManager = construct(LocalDate.of(2051, 7, 19), timeSlots, capacity);
                List<Order> expectedOrders = new ArrayList<>();
                StringBuilder log = new StringBuilder();
                log.append("Executing addOrder sequence with ")
                        .append(timeSlots)
                        .append(" time slots and ")
                        .append(capacity)
                        .append(" capacity per slot:\n");



                for (int slot = 0; slot < timeSlots; slot++) {
                    for (int count = 0; count < capacity; count++) {
                        Guest guest = mock(Guest.class);
                        Order order = mock(Order.class);
                        when(guest.getName()).thenReturn("Guest_" + slot + "_" + count);
                        when(guest.getGuestType()).thenReturn(GuestType.GUEST);
                        when(order.getGuest()).thenReturn(guest);

                        expectedOrders.add(order);

                        if (isFirstRun) {
                            log.append("adding Order with preferred slot ").append(slot).append("\n");
                            dayManager.addOrder(order, slot);
                        } else {
                            log.append("adding Order\n");
                            dayManager.addOrder(order);
                        }

                        assertEquals(expectedOrders, dayManager.getAllOrders(), log.append("Incorrect list returned by getAllOrders()").toString());
                        assertListIsUnmodifiable(dayManager.getAllOrders(), "List returned by getAllOrders() is modifiable");
                    }
                }
            }
            isFirstRun = false;
        }

         */
    }



    @Test
    public void testCalculateEarnings(){
        DayManager dayManager = construct(LocalDate.of(2025,11,11), 4,5);
        MyDayManager managerRichtig = new MyDayManager(LocalDate.of(2025,11,11),5,7);

        assertEquals(managerRichtig.calculateEarnings(), dayManager.calculateEarnings(), 0.001, "Fehler 1");

        for (int i = 0; i<5; i++){
            Order order = createOrderMock(i+1);
            dayManager.addOrder(order);
            managerRichtig.addOrder(order);
            assertEquals(managerRichtig.calculateEarnings(), dayManager.calculateEarnings(), 0.001, "Fehlerrrr");
        }
        /*
        Guest guest = mock(Guest.class);
        when(guest.getGuestType()).thenReturn(GuestType.STUDENT);
        Dish dish = mock(Dish.class);
        when(dish.getBasePrice()).thenReturn(1.1);
        Order order = mock(Order.class);
        when(order.getDishes()).thenReturn(List.of(dish));
        when(order.getGuest()).thenReturn(guest);
        dayManager.addOrder(order);
        assertEquals(1.1, dayManager.calculateEarnings(), "Blu");





        List<Dish> dish1 = DataGenerator.createDishMocks(1,1,1,0); // Price 0,5+1+1,5
        List<Dish> dish2 = DataGenerator.createDishMocks(0,1,1,0); // Price 1,5
        List<Dish> dish3 = DataGenerator.createDishMocks(1,0,0,1); // Price 1,5
        List<Dish> dish4 = DataGenerator.createDishMocks(1,1,1,1); // Price 5
        List<Dish> dish5 = DataGenerator.createDishMocks(0,0,0,0); // Price 0

        List<String> guestNames = DataGenerator.createGuestNames(5);
        List<Guest> guests = DataGenerator.createGuestMocks(2,2,1);

        for (int i = 0; i<guestNames.size(); i++){
            when(guests.get(i).getName()).thenReturn(guestNames.get(i));
            guests.add(guests.get(i));
        }

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i<5;i++){
            Order order = mock(Order.class);
            when(order.)
            orders.add
        }




        Order order1 = createOrderMock(1);
        Order order2 = createOrderMock(2);
        Order order3 = createOrderMock(3);
        Order order4 = createOrderMock(4);
        Order order5 = createOrderMock(5);



        assertEquals(dayManager.calculateEarnings(), 0.0, 0.001, "Incorrect Price1");

        dayManager.addOrder(order1);
        assertEquals(dayManager.calculateEarnings(), 0.5, 0.001, "Incorrect Price2");
        dayManager.addOrder(order2);
        assertEquals(dayManager.calculateEarnings(), 1.5, 0.001, "Incorrect Price3");
        dayManager.addOrder(order3);
        assertEquals(dayManager.calculateEarnings(), 3.0, 0.001, "Incorrect Price4");
        dayManager.addOrder(order4);
        assertEquals(dayManager.calculateEarnings(), 5.0, 0.001, "Incorrect Price5");
        dayManager.addOrder(order5);
        assertEquals(dayManager.calculateEarnings(), 7.5, 0.001, "Incorrect Price6");

        */

    }

    @Test
    public void testToString(){
        /*
        DayManager manager1 = construct(LocalDate.of(2030,1,2),7,2);
        DayManager manager2 = construct(LocalDate.of(2099,12,12),14,22);
        DayManager manager3 = construct(LocalDate.of(2032,2,21),9,21);
        DayManager manager4 = construct(LocalDate.of(20333,3,25),4,5);
        DayManager manager5 = construct(LocalDate.of(2121,1,1),7,8);

        assertEquals( "SimpleDayManager (02.01.30)", manager1.toString(), "Incorrect string representation returned by toString");
        assertEquals( "SimpleDayManager (12.12.99)", manager2.toString(),"Incorrect string representation returned by toString()");
        assertEquals( "SimpleDayManager (21.02.32)", manager3.toString(),"Incorrect string representation returned by toString");
        assertEquals( "SimpleDayManager (25.03.33)", manager4.toString(), "Incorrect string representation returned by toString");
        assertEquals( "SimpleDayManager (01.01.21)", manager5.toString(),"Incorrect string representation returned by toString");

         */
        Assertions.assertEquals("SimpleDayManager (02.01.71)", construct(LocalDate.of(2171, 1, 2), 5, 7).toString(), "Incorrect string representation returned by toString()");
        Assertions.assertEquals("SimpleDayManager (11.08.77)", construct(LocalDate.of(2077, 8, 11), 6, 7).toString(), "Incorrect string representation returned by toString()");
        Assertions.assertEquals("SimpleDayManager (22.07.43)", construct(LocalDate.of(2043, 7, 22), 7, 7).toString(), "Incorrect string representation returned by toString()");
        Assertions.assertEquals("SimpleDayManager (13.09.01)", construct(LocalDate.of(21101, 9, 13), 9, 7).toString(), "Incorrect string representation returned by toString()");
        Assertions.assertEquals("SimpleDayManager (04.12.99)", construct(LocalDate.of(2199,12, 4), 6, 7).toString(), "Incorrect string representation returned by toString()");

    }






    Optional<Integer> addSingleOrder(StringBuilder msg, DayManager dm, boolean preferredSlot, int prefSlot, Order order) {
        if (preferredSlot) {
            msg.append("adding Order with preferred slot ").append(prefSlot).append("\n");
            return dm.addOrder(order, prefSlot);
        } else {
            msg.append("adding Order\n");
            return dm.addOrder(order);
        }
    }


    void ref(int numberOfTimeSlots, int capacityPerSlot, String message, boolean preferredSlot, boolean checkSlots, boolean checkAllOrders) {
        // Initialisierung der Nachrichten-StringBuilder und der DayManager-Instanzen
        StringBuilder msg = new StringBuilder("Executing addOrder sequence on a DayManager with ")
                .append(numberOfTimeSlots).append(" time slots and ")
                .append(capacityPerSlot).append(" capacity per slot:\n");
        DayManager dm = construct(LocalDate.of(2050, 7, 4), numberOfTimeSlots, capacityPerSlot);
        DayManager correct = new MyDayManager(LocalDate.of(2050, 7, 4), numberOfTimeSlots, capacityPerSlot);

        // Erstellung der Bestellungen
        List<Order> orders = IntStream.rangeClosed(1, numberOfTimeSlots * capacityPerSlot)
                .mapToObj(DataGenerator::createOrderMock)
                .collect(Collectors.toList());

        int maxSlots = numberOfTimeSlots * capacityPerSlot;
        int prefSlot = preferredSlot && numberOfTimeSlots > 1 ? 1 : 0;

        // Hinzufügen der Bestellungen und Überprüfung
        IntStream.range(0, maxSlots).forEach(slot -> processOrder(msg, message, dm, correct, orders, slot, prefSlot, preferredSlot, capacityPerSlot, numberOfTimeSlots));

        // Überprüfung der Slots und aller Bestellungen, falls notwendig
        if (checkSlots) {
            checkSlotsIntegrity(dm, correct, numberOfTimeSlots, msg);
        }

        if (checkAllOrders) {
            checkAllOrdersIntegrity(dm, correct, msg);
        }

        // Überprüfung, ob keine weiteren Slots verfügbar sind
        checkNoMoreSlots(dm, preferredSlot, prefSlot, maxSlots, msg, message);
    }

    private void processOrder(StringBuilder msg, String message, DayManager dm, DayManager correct, List<Order> orders, int slot, int prefSlot, boolean preferredSlot, int capacityPerSlot, int numberOfTimeSlots) {
        // Hinzufügen der Bestellung und Ausnahmebehandlung
        try {
            Optional<Integer> actualSlot = addSingleOrder(msg, dm, preferredSlot, prefSlot, orders.get(slot));
            Assertions.assertTrue(actualSlot.isPresent(), msg + message);

            int expectedSlotIndex = calculateExpectedSlotIndex(slot, capacityPerSlot, preferredSlot, numberOfTimeSlots);
            Assertions.assertEquals(expectedSlotIndex, actualSlot.get(), msg + message);

            // Hinzufügen der Bestellung zum korrekten DayManager
            if (preferredSlot) {
                correct.addOrder(orders.get(slot), prefSlot);
            } else {
                correct.addOrder(orders.get(slot));
            }
        } catch (Throwable t) {
            System.out.println("Failed with error: " + t.getMessage());
            Assertions.fail(msg + message);
        }
    }

    private int calculateExpectedSlotIndex(int slot, int capacityPerSlot, boolean preferredSlot, int numberOfTimeSlots) {
        // Berechnung des erwarteten Slot-Index
        int s2 = slot / capacityPerSlot;
        return numberOfTimeSlots > 1 && preferredSlot && s2 < 2 ? 1 - s2 : s2;
    }

    private void checkSlotsIntegrity(DayManager dm, DayManager correct, int numberOfTimeSlots, StringBuilder msg) {
        // Überprüfung der Slots auf Integrität
        IntStream.range(0, numberOfTimeSlots).forEach(slot -> {
            List<Order> dmOrders = dm.getOrdersForSlot(slot);
            List<Order> correctOrders = correct.getOrdersForSlot(slot);
            Assertions.assertEquals(correctOrders, dmOrders, msg + "Incorrect list returned by getOrdersForSlot() for slot " + slot);
            TestUtils.assertListIsUnmodifiable(dmOrders, "List returned by getOrdersForSlot() is modifiable");
        });
    }

    private void checkAllOrdersIntegrity(DayManager dm, DayManager correct, StringBuilder msg) {
        // Überprüfung der Gesamtbestellungen auf Integrität
        List<Order> allDmOrders = dm.getAllOrders();
        List<Order> allCorrectOrders = correct.getAllOrders();
        Assertions.assertEquals(allCorrectOrders, allDmOrders, msg + "Incorrect list returned by getAllOrders()");
        TestUtils.assertListIsUnmodifiable(allDmOrders, "List returned by getAllOrders() is modifiable");
    }

    private void checkNoMoreSlots(DayManager dm, boolean preferredSlot, int prefSlot, int maxSlots, StringBuilder msg, String message) {
        // Überprüfung, ob keine weiteren Slots verfügbar sind
        Optional<Integer> full = addSingleOrder(msg, dm, preferredSlot, prefSlot, createOrderMock(maxSlots + 1));
        Assertions.assertFalse(full.isPresent(), msg + message);
    }

    }


