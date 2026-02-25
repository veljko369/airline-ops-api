package com.veljko.airline_ops.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AircraftControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAircraft_shouldReturn200_andUppercaseRegistration() throws Exception {
        String body = """
                {
                  "registration": "yu-aph",
                  "type": "A320",
                  "manufacturer": "Airbus",
                  "seatingCapacity": 180,
                  "maxTakeoffWeightKg": 73500
                }
                """;

        mockMvc.perform(post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.registration").value("YU-APH"));
    }

    @Test
    void deleteAircraft_shouldReturn400_whenAssignedToFlight() throws Exception {

        Long begId = createAirportAndReturnId("BEG", "Belgrade Nikola Tesla Airport", "Belgrade", "Serbia");
        Long lhrId = createAirportAndReturnId("LHR", "Heathrow Airport", "London", "United Kingdom");

        Long aircraftId = createAircraftAndReturnId("YU-TEST", "A320", "Airbus", 180, 73500);

        String flightBody = """
                {
                  "flightNumber": "JU999",
                  "originAirportId": %d,
                  "destinationAirportId": %d,
                  "scheduledDeparture": "2025-12-13T08:30:00",
                  "scheduledArrival": "2025-12-13T10:45:00",
                  "gate": "A1",
                  "aircraftId": %d
                }
                """.formatted(begId, lhrId, aircraftId);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(flightBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

        mockMvc.perform(delete("/api/aircraft/{id}", aircraftId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    private Long createAirportAndReturnId(String code, String name, String city, String country) throws Exception {
        String body = """
                {
                  "code": "%s",
                  "name": "%s",
                  "city": "%s",
                  "country": "%s"
                }
                """.formatted(code, name, city, country);

        String response = mockMvc.perform(post("/api/airports")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return extractId(response);
    }

    private Long createAircraftAndReturnId(String registration, String type, String manufacturer,
                                           int seatingCapacity, int maxTakeoffWeightKg) throws Exception {
        String body = """
                {
                  "registration": "%s",
                  "type": "%s",
                  "manufacturer": "%s",
                  "seatingCapacity": %d,
                  "maxTakeoffWeightKg": %d
                }
                """.formatted(registration, type, manufacturer, seatingCapacity, maxTakeoffWeightKg);

        String response = mockMvc.perform(post("/api/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return extractId(response);
    }

    private Long extractId(String json) throws Exception {
        JsonNode node = objectMapper.readTree(json);
        return node.get("id").asLong();
    }
}