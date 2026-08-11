package com.example.common.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class Money {
    private final BigDecimal amount;

    public Money(double amount) {
        this.amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
    }

    public Money(BigDecimal amount) {
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money plus(Money other) {
        return new Money(this.amount.add(other.amount));
    }

    public Money times(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Money)) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}