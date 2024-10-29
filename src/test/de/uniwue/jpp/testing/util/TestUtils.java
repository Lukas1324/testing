package de.uniwue.jpp.testing.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtils {

    public static void assertThrowsWithMessage(Class<? extends Exception> expectedType, Executable executable, String exceptionMessage, String message) {
        try {
            try {
                executable.execute();
                throw new AssertionError(message + " ==> Expected " + expectedType.getName() + " to be thrown, but nothing was thrown.");
            } catch (Exception e) {
                if (!expectedType.isAssignableFrom(e.getClass())) {
                    throw new AssertionError(message + " ==> Unexpected exception type thrown, expected: <" + expectedType.getName() + "> but was: <" + e.getClass().getName() + ">");
                }
                if (e.getMessage() == null) {
                    throw new AssertionError(message + " ==> Exception was thrown but message was null");
                }
                if (!exceptionMessage.equals(e.getMessage())) {
                    throw new AssertionError(message + " ==> Incorrect exception message ==> expected: <" + exceptionMessage + "> but was: <" + e.getMessage() + ">");
                }
            }
        } catch (AssertionError e) {
            throw e;

        } catch (Throwable e) {
            throw new AssertionError(message + " ==> Unexpected error occurred: " + e.getMessage());
        }

    }








    public static <T> void assertListsEqualInAnyOrder(List<T> expected, List<T> actual, String message){
        if (expected == null && actual == null ) {
            return;
        }if (expected == null || actual == null){
            throw new AssertionError(message);
        }if (expected.size() != actual.size()){
            throw new AssertionError(message);
        }if (expected.containsAll(actual) && actual.containsAll(expected)){
            return;
        }else {
            throw new AssertionError(message);
        }
    }

    public static void assertListIsUnmodifiable(List<?> list, String message){
        if (list == null){
            throw new AssertionError("List is null");
            }
        try{
            list.add(null);
            list.remove(null);
        }catch (Exception e){
            return;
        }
        throw new AssertionError(message);
    }
}
