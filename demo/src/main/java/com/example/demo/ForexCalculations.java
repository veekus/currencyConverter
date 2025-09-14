package com.example.demo;


import java.util.HashMap;
import java.util.Map;

public class ForexCalculations {

    public static Map<String, Double> getRates(String base){
        if (base == null || base.equals("EUR")){
            return ForexRates.DEFAULT_RATES;
        }
        Map<String, Double> baseMap = new HashMap<String, Double>();
        Double factor = ForexRates.DEFAULT_RATES.get("EUR:"+base);
        ForexRates.DEFAULT_RATES.forEach((currency, rate)->
        {
            if (!base.equals(currency.substring(currency.indexOf(":")+1))) {
                baseMap.put(base + ":" + currency.substring(currency.indexOf(":") + 1), rate / factor);
            }
            else {
                baseMap.put(base+":"+"EUR", 1/rate);
            }

        });
        return baseMap;
    }
}
