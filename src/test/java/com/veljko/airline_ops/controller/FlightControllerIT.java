package com.veljko.airline_ops.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class FlightControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createFlight_shouldReturn200_andReturnId() throws Exception {
        Long begId = createAirportAndReturnId("BEG", "Belgrade Nikola Tesla Airport", "Belgrade", "Serbia");
        Long lhrId = createAirportAndReturnId("LHR", "Heathrow Airport", "London", "United Kingdom");
        Long aircraftId = createAircraftAndReturnId("YU-TEST", "A320", "Airbus", 180, 73500);

        String body = """
                {
                  "flightNumber": "ju540",
                  "originAirportId": %d,
                  "destinationAirportId": %d,
                  "scheduledDeparture": "2025-12-13T08:30:00",
                  "scheduledArrival": "2025-12-13T10:45:00",
                  "gate": "A4",
                  "aircraftId": %d,
                  "plannedPayloadKg": 8000,
                  "actualPayloadKg": 7800,
                  "fuelKg": 5200
                }
                """.formatted(begId, lhrId, aircraftId);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.flightNumber").value("JU540"))
                .andExpect(jsonPath("$.originAirport.code").value("BEG"))
                .andExpect(jsonPath("$.destinationAirport.code").value("LHR"))
                .andExpect(jsonPath("$.aircraft.registration").value("YU-TEST"));
    }

    @Test
    void createFlight_shouldReturn400_whenArrivalNotAfterDeparture() throws Exception {
        Long begId = createAirportAndReturnId("BEG", "Belgrade Nikola Tesla Airport", "Belgrade", "Serbia");
        Long lhrId = createAirportAndReturnId("LHR", "Heathrow Airport", "London", "United Kingdom");
        Long aircraftId = createAircraftAndReturnId("YU-TEST2", "A320", "Airbus", 180, 73500);

        String body = """
                {
                  "flightNumber": "JU541",
                  "originAirportId": %d,
                  "destinationAirportId": %d,
                  "scheduledDeparture": "2025-12-13T10:45:00",
                  "scheduledArrival": "2025-12-13T08:30:00",
                  "gate": "A4",
                  "aircraftId": %d
                }
                """.formatted(begId, lhrId, aircraftId);

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void patchStatus_shouldUpdateStatus() throws Exception {
        Long flightId = createFlightAndReturnId("JU600");

        String body = """
                { "status": "DELAYED" }
                """;

        mockMvc.perform(patch("/api/flights/{id}/status", flightId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DELAYED"));
    }

    @Test
    void patchGate_shouldUpdateGate() throws Exception {
        Long flightId = createFlightAndReturnId("JU610");

        String body = """
                { "gate": "C12" }
                """;

        mockMvc.perform(patch("/api/flights/{id}/gate", flightId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gate").value("C12"));
    }

    @Test
    void patchWeightBalance_shouldUpdateValues() throws Exception {
        Long flightId = createFlightAndReturnId("JU620");

        String body = """
                {
                  "plannedPayloadKg": 9000,
                  "actualPayloadKg": 8800,
                  "fuelKg": 6000
                }
                """;

        mockMvc.perform(patch("/api/flights/{id}/weight-balance", flightId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plannedPayloadKg").value(9000))
                .andExpect(jsonPath("$.actualPayloadKg").value(8800))
                .andExpect(jsonPath("$.fuelKg").value(6000));
    }

    @Test
    void deleteFlight_shouldRemoveFlight_andThenGetReturns404() throws Exception {
        Long flightId = createFlightAndReturnId("JU700");

        mockMvc.perform(delete("/api/flights/{id}", flightId))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/api/flights/{id}", flightId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    private Long createFlightAndReturnId(String flightNumber) throws Exception {
        Long begId = createAirportAndReturnId("BEG", "Belgrade Nikola Tesla Airport", "Belgrade", "Serbia");
        Long lhrId = createAirportAndReturnId("LHR", "Heathrow Airport", "London", "United Kingdom");
        Long aircraftId = createAircraftAndReturnId("YU-" + flightNumber, "A320", "Airbus", 180, 73500);

        String body = """
                {
                  "flightNumber": "%s",
                  "originAirportId": %d,
                  "destinationAirportId": %d,
                  "scheduledDeparture": "2025-12-13T08:30:00",
                  "scheduledArrival": "2025-12-13T10:45:00",
                  "gate": "A4",
                  "aircraftId": %d
                }
                """.formatted(flightNumber, begId, lhrId, aircraftId);

        String response = mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return extractId(response);
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