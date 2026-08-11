package com.example.common.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {
    @Test
    void timesAndPlus() {
        Money unit = new Money(10.0);
        assertEquals(new Money(20.0), unit.times(2));
        assertEquals(new Money(30.0), unit.times(2).plus(unit));
    }
}