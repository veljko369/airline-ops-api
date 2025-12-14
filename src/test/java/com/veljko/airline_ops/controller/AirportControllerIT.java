package com.veljko.airline_ops.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AirportControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAirport_shouldReturn200_andReturnId() throws Exception {
        String body = """
                {
                  "code": "BEG",
                  "name": "Belgrade Nikola Tesla Airport",
                  "city": "Belgrade",
                  "country": "Serbia"
                }
                """;

        mockMvc.perform(post("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.code").value("BEG"));
    }

    @Test
    void createAirport_shouldReturn400_whenCodeMissing() throws Exception {
        String body = """
                {
                  "name": "Belgrade Nikola Tesla Airport",
                  "city": "Belgrade",
                  "country": "Serbia"
                }
                """;

        mockMvc.perform(post("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }
}
