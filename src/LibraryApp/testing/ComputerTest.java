package LibraryApp.testing;

import LibraryApp.Models.Computer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class which tests the functionality of the Computer class
 */

class ComputerTest {

    private Computer computer;

    /**
     * Method which sets default values computer object.
     */

    private void setDefaults() {
        computer.setLocation("Strathclyde");
        computer.set_id(1001);
        computer.setInUse(false);
        Computer.setNumberBeingUsed(0);
    }

    /**
     * Method which is called before each test case.
     */

    @BeforeEach
    void setUp() {
        computer = new Computer();
        setDefaults();
    }
    /**
     * Method which is called after each test case.
     */

    @AfterEach
    void tearDown() {
        setDefaults();
    }

    /**
     * Method which tests the toString method.
     */

    @Test
    void testToString() {
        String expected = "Computer {_id=1001, isInUse=false, location='Strathclyde'}";
        String result = computer.toString();
        assertEquals(expected, result);
    }

    /**
     * Method which tests the get_id and set_id methods.
     */

    @Test
    void get_id() {
        computer.set_id(1002);
        int expected = 1002;
        int result = computer.get_id();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the isInUse and setInUse methods.
     */

    @Test
    void isInUse() {
        computer.setInUse(true);

        boolean result = computer.isInUse();
        assertTrue(result);
    }

    /**
     * Method which tests the static getTotalNumber method.
     */

    @Test
    void getTotalNumber() {
        int result = Computer.getTotalNumber();

        assertEquals(20, result);
    }

    /**
     * Method which tests the numberBeing used method.
     */

    @Test
    void getNumberBeingUsed() {
        computer.setInUse(true);
        int result = Computer.getNumberBeingUsed();

        assertEquals(1, result);
    }

    /**
     * Method which tests the getLocation and setLocation methods.
     */

    @Test
    void getLocation() {
        String expected = "Andersonian";
        computer.setLocation("Andersonian");
        String result = computer.getLocation();

        assertEquals(expected, result);
    }

    /**
     * Method which tests the setNumberBeingUsed method.
     */

    @Test
    void setNumberBeingUsed() {
        Computer.setNumberBeingUsed(10);
        int result = Computer.getNumberBeingUsed();
        assertEquals(10, result);
    }

    /**
     * Method which tests the useComputer and stopUsingComputer methods.
     */


    @Test
    void useComputer() {
        computer.useComputer();
        int result = Computer.getNumberBeingUsed();
        boolean secondResult = computer.isInUse();

        assertEquals(1, result);
        assertTrue(secondResult);

        computer.stopUsingComputer();

        int thirdResult = Computer.getNumberBeingUsed();
        boolean fourthResult = computer.isInUse();

        assertEquals(0, thirdResult);
        assertFalse(fourthResult);

    }

}