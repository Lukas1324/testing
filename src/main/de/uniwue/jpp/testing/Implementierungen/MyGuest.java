package de.uniwue.jpp.testing.Implementierungen;

import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Guest;

import java.util.Objects;

public class MyGuest implements Guest {

    private final String name;
    private final GuestType guestType;

    public MyGuest(String name, GuestType guestType) {
        this.name = name;
        this.guestType = guestType;
    }

    /*public static Guest creatGuest(String name, GuestType guestType){

        Guest myGuest = new MyGuest(name,guestType);
        return myGuest;
    }
    */

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GuestType getGuestType() {
        return guestType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyGuest myGuest = (MyGuest) o;
        if (myGuest.getName().equals(this.name)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " (" + guestType +")";
    }
}
