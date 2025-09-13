package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ForexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testConvertEndpoint() throws Exception {
        mockMvc.perform(get("/forex/convert")
                        .param("from", "EUR")
                        .param("to", "USD")
                        .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0 EUR 109.00000000000001 USD"));
        mockMvc.perform(get("/forex/convert")
                .param("from", "AAA")
                .param("to", "BBB")
                .param("amount", "1000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ratesEndpoint() throws Exception{
        mockMvc.perform(get("/forex/rates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.USD").exists())
                .andExpect(jsonPath("$.RSD").value(117.35));
    }
}
