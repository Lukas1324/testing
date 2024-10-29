package de.uniwue.jpp.testing.enums;

import org.junit.jupiter.api.Test;

public class TestGuestType extends AbstractEnumTestClass {

    @Test
    public void testEnumValues(){
        int lenght = clazz.getEnumConstants().length;
        if (lenght == 3){
            Class thisclass = getEnumClass();
            String[] enumAsStrings = new String[3];
            for (int i = 0; i<3; i++){
                enumAsStrings[i] = thisclass.getEnumConstants()[i].toString();
            }
            for (String x : enumAsStrings){
                if (x.equals("STUDENT")){
                    
                } else if (x.equals("STAFF")) {
                    
                } else if (x.equals("GUEST")) {

                }else{
                    throw new AssertionError("The values in the GuestType enum are not as expected");
                }
            }
        }else{
            throw new AssertionError("The values in the GuestType enum are not as expected");
        }

    }

    @Test
    public void testDiscountFactor(){
        double[] doubles = new double[3];
        doubles[0] = getGuestTypeConstant("STUDENT").getDiscountFactor();
        doubles[1] = getGuestTypeConstant("STAFF").getDiscountFactor();
        doubles[2] = getGuestTypeConstant("GUEST").getDiscountFactor();

        if (doubles[0] != 0.6){
            throw new AssertionError("Incorrect value returned by getDiscountFactor() on STUDENT");
        } else if (doubles[1] != 0.8) {
            throw new AssertionError("Incorrect value returned by getDiscountFactor() on STAFF");
        } else if (doubles[2] != 1.0) {
            throw new AssertionError("Incorrect value returned by getDiscountFactor() on GUEST");
        }

    }
}
