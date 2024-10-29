package de.uniwue.jpp.testing.interfaces;

import de.uniwue.jpp.testing.Implementierungen.MyGuest;
import de.uniwue.jpp.testing.enums.GuestType;

public interface Guest {
    public String getName();

    public GuestType getGuestType();

    public static Guest createGuest(String name, GuestType type){
        if(name== null){
            throw new IllegalArgumentException("Name must not be null!");
        } else if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty!");
        }
        if (type == null){
            throw new IllegalArgumentException("GuestType must not be null!");
        }
        return new MyGuest(name,type);
    }
}
