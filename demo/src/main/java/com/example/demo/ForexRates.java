package com.example.demo;

import java.util.Map;

public class ForexRates {
    static final Map<String, Double> RATES = Map.of(
            "USD:EUR", 0.92,
            "EUR:USD", 1.09
    );

    static final Map<String, Double> DEFAULT_RATES = Map.of(
            "EUR:GBP", 1.1555,
            "EUR:USD", 0.8522,
            "EUR:RUB", 98.966,
            "EUR:KZT", 637.490,
            "EUR:RSD", 117.350
    );
}
