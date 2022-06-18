package com.techelevator.view;

import org.junit.Rule;
import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

public class VendingMachineTest {
    @Rule
    public final TextFromStandardInputStream systemInMock
            = emptyStandardInputStream();

    @Test
    public void summarizesTwoNumbers() {
        systemInMock.provideLines("1", "5", "2", "D4");
        VendingActions.purchaces();
        assertEquals(4.25, Money.getBalance(), 0.001);
        assertEquals(5, Money.getCurrentMoneyProvided(), 0.001);
    }

}


