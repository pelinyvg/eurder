package com.switchfully.eurder.domain.items;

import java.util.Currency;

public class Price {
    private final double priceNumber;
    private final Currency currency;

    public Price(double priceNumber, Currency currency) {
        this.priceNumber = priceNumber;
        this.currency = currency;
    }

    public double getPriceNumber() {
        return priceNumber;
    }

    public Currency getCurrency() {
        return currency;
    }
}
