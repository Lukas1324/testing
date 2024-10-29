package de.uniwue.jpp.testing;

import de.uniwue.jpp.testing.enums.GuestType;
import de.uniwue.jpp.testing.interfaces.Guest;
import de.uniwue.jpp.testing.util.AbstractTestClass;
import org.junit.jupiter.api.Test;
import static de.uniwue.jpp.testing.util.TestUtils.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestGuest extends AbstractTestClass<Guest> {

    @Test
    public void testConstructorValidArguments(){
        try {
            Guest guest1 = construct("Bello", GuestType.GUEST);
            Guest guest2 = construct("Bell", GuestType.STAFF);
            Guest guest3 = construct("Bel", GuestType.STUDENT);
            Guest guest4 = construct("Be", GuestType.GUEST);
            Guest guest5 = construct("B", GuestType.GUEST);


        }catch (Exception e){
            throw new AssertionError("Testing valid arguments for Guest constructor" + e.getMessage());
        }

    }

    @Test
    public void testConstructorInvalidArguments(){
        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct(null, GuestType.GUEST),
                "Name must not be null!",
                "Testing invalid name argument for Guest constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct("Günter", null),
                "GuestType must not be null!",
                "Testing invalid guestType argument for Guest constructor");

        assertThrowsWithMessage(IllegalArgumentException.class,
                () -> construct("", GuestType.GUEST),
                "Name must not be empty!",
                "Testing invalid name argument for Guest constructor");
    }

    @Test
    public void testGetName(){
        Guest guest1 = construct("Bello", GuestType.GUEST);
        Guest guest2 = construct("Bell", GuestType.STAFF);
        Guest guest3 = construct("Bel", GuestType.STUDENT);
        Guest guest4 = construct("Be", GuestType.GUEST);
        Guest guest5 = construct("B", GuestType.GUEST);

        assertEquals("Bello", guest1.getName(), "Incorrect name returned by getName()");
        assertEquals("Bell", guest2.getName(), "Incorrect name returned by getName()");
        assertEquals("Bel", guest3.getName(), "Incorrect name returned by getName()");
        assertEquals("Be", guest4.getName(), "Incorrect name returned by getName()");
        assertEquals("B", guest5.getName(), "Incorrect name returned by getName()");

    }

    @Test
    public void testGetGuestType(){
        Guest guest1 = construct("Bello", GuestType.GUEST);
        Guest guest2 = construct("Bell", GuestType.STAFF);
        Guest guest3 = construct("Bel", GuestType.STUDENT);
        Guest guest4 = construct("Be", GuestType.GUEST);
        Guest guest5 = construct("B", GuestType.GUEST);

        assertEquals(guest1.getGuestType(), GuestType.GUEST, "Incorrect guestType returned by getGuestType()");
        assertEquals(guest2.getGuestType(), GuestType.STAFF, "Incorrect guestType returned by getGuestType()");
        assertEquals(guest3.getGuestType(), GuestType.STUDENT, "Incorrect guestType returned by getGuestType()");
        assertEquals(guest4.getGuestType(), GuestType.GUEST, "Incorrect guestType returned by getGuestType()");
        assertEquals(guest5.getGuestType(), GuestType.GUEST, "Incorrect guestType returned by getGuestType()");
    }

    @Test
    public void testEquals(){
        Guest guest1 = construct("Guest1", GuestType.GUEST);
        Guest guest2 = construct("Guest1", GuestType.GUEST);
        Guest guest3 = construct("Guest3", GuestType.GUEST);
        Guest guest12 = construct("Guest4", GuestType.GUEST);

        Guest guest4 = construct("Guest1", GuestType.STAFF);
        Guest guest5 = construct("Guest1", GuestType.STAFF);
        Guest guest6 = construct("Staff3", GuestType.STAFF);
        Guest guest7 = construct("Staff4", GuestType.STAFF);

        Guest guest8 = construct("Guest1", GuestType.STUDENT);
        Guest guest9 = construct("Guest1", GuestType.STUDENT);
        Guest guest10 = construct("Student3", GuestType.STUDENT);
        Guest guest11 = construct("Student4", GuestType.STUDENT);


        assertEquals(guest1, guest2, "Two guests with identical names should be equal");
        assertEquals(guest4, guest5, "Two guests with identical names should be equal");
        assertEquals(guest1, guest5, "Two guests with identical names should be equal");
        assertEquals(guest4, guest8, "Two guests with identical names should be equal");
        assertEquals(guest2, guest9, "Two guests with identical names should be equal");


        assertNotEquals(guest1, guest6, "Two guests with different names should not be equal");
        assertNotEquals(guest3, guest12, "Two guests with different names should not be equal");
        assertNotEquals(guest6, guest7, "Two guests with different names should not be equal");
        assertNotEquals(guest10, guest11, "Two guests with different names should not be equal");
        assertNotEquals(guest1, guest11, "Two guests with different names should not be equal");
        assertNotEquals(guest7, guest10, "Two guests with different names should not be equal");
        assertNotEquals(guest1, guest10, "Two guests with different names should not be equal");
    }

    @Test
    public void testHashCode(){
        Guest guest1 = construct("Guest1", GuestType.STUDENT);
        Guest guest2 = construct("Guest1", GuestType.STUDENT);

        Guest guest3 = construct("Guest2", GuestType.STAFF);
        Guest guest4 = construct("Guest2", GuestType.STAFF);

        Guest guest5 = construct("Guest3", GuestType.GUEST);
        Guest guest6 = construct("Guest3", GuestType.GUEST);

        Guest guest7 = construct("Guest4", GuestType.STUDENT);
        Guest guest8 = construct("Guest4", GuestType.STAFF);

        Guest guest9 = construct("Guest5", GuestType.STUDENT);
        Guest guest10 = construct("Guest5", GuestType.GUEST);

        assertEquals(guest1.hashCode(), guest2.hashCode(), "Two guests with identical names should have the same hash code");
        assertEquals(guest3.hashCode(), guest4.hashCode(), "Two guests with identical names should have the same hash code");
        assertEquals(guest5.hashCode(), guest6.hashCode(), "Two guests with identical names should have the same hash code");
        assertEquals(guest7.hashCode(), guest8.hashCode(), "Two guests with identical names should have the same hash code");
        assertEquals(guest9.hashCode(), guest10.hashCode(), "Two guests with identical names should have the same hash code");

    }

    @Test
    public void testToString(){
        Guest guest1 = construct("Guest1", GuestType.GUEST);
        Guest guest2 = construct("Guest2", GuestType.STAFF);
        Guest guest3 = construct("Guest3", GuestType.STUDENT);
        Guest guest4 = construct("Guest4", GuestType.GUEST);
        Guest guest5 = construct("Guest5", GuestType.GUEST);


        assertEquals(guest1.toString(), "Guest1 (GUEST)", "Incorrect string representation returned by toString()");
        assertEquals(guest2.toString(), "Guest2 (STAFF)", "Incorrect string representation returned by toString()");
        assertEquals(guest3.toString(), "Guest3 (STUDENT)", "Incorrect string representation returned by toString()");
        assertEquals(guest4.toString(), "Guest4 (GUEST)", "Incorrect string representation returned by toString()");
        assertEquals(guest5.toString(), "Guest5 (GUEST)", "Incorrect string representation returned by toString()");

    }
}
