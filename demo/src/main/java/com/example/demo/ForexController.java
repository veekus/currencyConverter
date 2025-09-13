package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/forex")
@ControllerAdvice
public class ForexController {
    private final Map<String, Double> rates = Map.of(
            "USD:EUR", 0.92,
            "EUR:USD", 1.09
    );

    @GetMapping("/convert")
    public ResponseEntity<String> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {
        String key = from + ":" + to;
        Double rate = rates.get(key);
        if (rate == null) throw new IllegalArgumentException("Unsupported pair");
        return ResponseEntity.ok(amount + " " + from + " " + amount * rate + " "+ to);
    }

    @GetMapping("/rates")
        public ResponseEntity<Map<String, Double>> rates(){
            Map<String, Double> defaultRates = Map.of(
                    "GBP", 1.1555,
                    "USD", 0.8522,
                    "RUB", 98.966,
                    "KZT", 637.490,
                    "RSD", 117.350
            );
            if (defaultRates.isEmpty()) throw new RuntimeException("It was the error with rates");
            return ResponseEntity.ok(defaultRates);
        }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> convertExceptionHandler(){
            Map<String, String> errorMap = Map.of(
                    "error 3789", "Unsupported pair"
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
