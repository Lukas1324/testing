package de.uniwue.jpp.testing.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDishType extends AbstractEnumTestClass {

    @Test
    public void testEnumValues(){
        int length =  clazz.getEnumConstants().length;
        if (length == 4) {
            Class thisClass = getEnumClass();
            String[] enumsAsStrings = new String[4];
            for (int i = 0 ; i < 4 ; i++) {
                enumsAsStrings[i] = thisClass.getEnumConstants()[i].toString();
            }
            for (String constant : enumsAsStrings) {
                if (constant.equals("STARTER")) {
                    // correct
                } else if (constant.equals("MAIN_DISH")) {
                    // correct
                } else if (constant.equals("DESSERT")) {
                    // correct
                } else if (constant.equals("OTHER")) {
                    // correct
                } else {
                    throw new AssertionError("The values in the DishType enum are not as expected");
                }
            }
        } else {
            throw new AssertionError("The values in the DishType enum are not as expected");
        }
    }
}
