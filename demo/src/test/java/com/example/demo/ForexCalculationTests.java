package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForexCalculationTests {
    @Test
    void testRatesFromEur() {
        Map<String, Double> rates = ForexCalculations.getRates("EUR");

        assertEquals(0.8522, rates.get("EUR:USD"), 0.0001);
        assertEquals(117.350, rates.get("EUR:RSD"), 0.0001);
        // вызвать ForexCalculations.getRates("EUR")
        // проверить что "EUR:USD" == 0.8522 (пример)
    }

    @Test
    void testRatesFromUsd() {
        Map<String, Double> rates = ForexCalculations.getRates("USD");

        // USD:EUR должен быть обратным к EUR:USD
        assertEquals(1 / 0.8522, rates.get("USD:EUR"), 0.0001);

        // Например, EUR:RSD = 117.350 → тогда USD:RSD = 117.350 / 0.8522
        assertEquals(117.350 / 0.8522, rates.get("USD:RSD"), 0.0001);
        // вызвать ForexCalculations.getRates("USD")
        // проверить что "USD:EUR" корректно пересчитан
    }

}
