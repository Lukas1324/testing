package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.Implementierungen.MyDayManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayManager {

    public Optional<Integer> addOrder(Order order, int preferredSlot);

    public Optional<Integer> addOrder(Order order);

    public List<Order> getOrdersForSlot(int slot);

    public List<Order> getAllOrders();

    public double calculateEarnings();

    public static DayManager createSimpleDayManager(LocalDate date, int numberOfTimeSlots, int capacityPerSlot){
        if (date == null){
            throw new IllegalArgumentException("Date must not be null!");
        }
        if (date.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Date must not be before current date!");
        }
        if (numberOfTimeSlots <= 0){
            throw new IllegalArgumentException("NumberOfTimeSlots must not be zero or negative!");
        }
        if (capacityPerSlot <= 0){
            throw new IllegalArgumentException("CapacityPerSlot must not be zero or negative!");
        }
        return new MyDayManager(date, numberOfTimeSlots, capacityPerSlot);
    }
}
