package org.example;

import org.junit.Test;

public class charoma1Test {
    @Test
    public void testFactorial5(){
        charoma1 testt = new charoma1();
        assertEquals(120, testt.factorial(5));
    }

    @Test
    public void testFactorialNegative(){
        charoma1 testt = new charoma1();
        assertThrows(
                IllegalArgumentException.class, () -> testt.factorial(-5)
        );
    }
    /*
    assertEquals(120, testt.factorial(5));
    assertEquals(1, testt.factorial(0));
    assertEquals(1, testt.factorial(1));
    assertEquals(2, testt.factorial(2));
    assertEquals(6, testt.factorial(3));
    assertEquals(24, testt.factorial(4));
     */
}