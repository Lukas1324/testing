package de.uniwue.jpp.testing.Implementierungen;

import de.uniwue.jpp.testing.interfaces.DayManager;
import de.uniwue.jpp.testing.interfaces.Order;

import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class MyDayManager implements DayManager {

    private final LocalDate date;
    private final int numberOfTimeSlots;
    private final int capacityPerSlot;
    private HashMap<Integer, List<Order>> map;
    private List<String> hatSchonBestellt;


    public MyDayManager(LocalDate date, int numberOfTimeSlots, int capacityPerSlot) {
        this.date = date;
        this.numberOfTimeSlots = numberOfTimeSlots;
        this.capacityPerSlot = capacityPerSlot;
        this.map = new HashMap<>();
        this.hatSchonBestellt = new ArrayList<>();
        for (int i = 0; i<numberOfTimeSlots; i++){
            map.put(i, new ArrayList<>());
        }
    }

    @Override
    public Optional<Integer> addOrder(Order order, int preferredSlot) {
        int pendel = 1;
        if (order == null){
            throw new IllegalArgumentException("Order must not be null!");
        }
        if (preferredSlot >= numberOfTimeSlots){
            throw new IllegalArgumentException("Illegal slot index!");
        }
        if (hatSchonBestellt.contains(order.getGuest().getName())){
            throw new IllegalArgumentException("Illegal order: This guest has already ordered!");
        }
        if (map.get(preferredSlot)  == null){
            throw new IllegalArgumentException("Illegal slot index!");
        }
        if (map.get(preferredSlot).size() < capacityPerSlot){
            map.get(preferredSlot).add(order);
            hatSchonBestellt.add(order.getGuest().getName());
            return Optional.of(preferredSlot);
        }else {
            for (int i = 0; i<numberOfTimeSlots;i++){
                if (preferredSlot-pendel >= 0){
                    if (map.get(preferredSlot - pendel).size() < capacityPerSlot) {
                        map.get(preferredSlot - pendel).add(order);
                        hatSchonBestellt.add(order.getGuest().getName());
                        return Optional.of(preferredSlot - pendel);
                    }
                }
                if (preferredSlot + pendel < numberOfTimeSlots){
                    if (map.get(preferredSlot + pendel).size() < capacityPerSlot) {
                        map.get(preferredSlot + pendel).add(order);
                        hatSchonBestellt.add(order.getGuest().getName());
                        return Optional.of(preferredSlot + pendel);
                    }
                }
                pendel++;
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Integer> addOrder(Order order) {
        return addOrder(order, 0);
    }

    @Override
    public List<Order> getOrdersForSlot(int slot) {
        if (map.size() > slot && slot >= 0){
            return Collections.unmodifiableList(map.get(slot));
        }
        throw new IllegalArgumentException("Illegal slot index!");
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> tempList = new ArrayList<>();
        for (int i = 0; i<numberOfTimeSlots; i++){
            tempList.addAll(map.get(i));
        }
        return Collections.unmodifiableList(tempList);
    }

    @Override
    public double calculateEarnings() {
        double tempEaring = 0.0;
        for (Order order: getAllOrders()) {
            tempEaring += order.calculatePrice();
        }
        return tempEaring;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.uu");
        return "SimpleDayManager (" + date.format(dateTimeFormatter) + ")";
    }
}
