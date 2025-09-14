package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/forex")
@ControllerAdvice
public class ForexController {


    @GetMapping("/convert")
    public ResponseEntity<String> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        String key = from + ":" + to;
        Double rate = ForexRates.RATES.get(key);
        if (rate == null) throw new IllegalArgumentException("Unsupported pair");
        return ResponseEntity.ok(amount + " " + from + " " + amount * rate + " "+ to);
    }

    @GetMapping("/rates")
    public ResponseEntity<Map<String, Double>> rates(
            @RequestParam(name = "base", required = false, defaultValue = "EUR") String base
    ){
        if (ForexRates.DEFAULT_RATES.isEmpty()) throw new RuntimeException("It was the error with rates");
        if (!"EUR".equals(base) && ForexRates.DEFAULT_RATES.getOrDefault("EUR:"+base, null) == null) throw new IllegalArgumentException("Unsupported currency");
        return ResponseEntity.ok(ForexCalculations.getRates(base));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> convertExceptionHandler(){
            Map<String, String> errorMap = Map.of(
                    "error 3789", "Unsupported pair or unsupported currency"
            );
            return ResponseEntity.badRequest().body(errorMap);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> ratesJSONExceptionHandler(){
        Map<String, String> errorMap = Map.of(
                "error 6723", ":Please, wait until rates will update"
        );
        return ResponseEntity.badRequest().body(errorMap);
    }

}
