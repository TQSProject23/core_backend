package tqs.loadconnect.core_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.Utils.Enums.PickupPEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.OrderService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.http.ContentType;
import tqs.loadconnect.core_backend.services.PickupPointService;

@WebMvcTest(PickupPointController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PickupPointControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PickupPointService pickupService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @DisplayName("Add Pickup Point - Success")
    @Test
    @Disabled
    void testAddPickupPoint() throws Exception {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);
        PartnerStore partnerStore = new PartnerStore();
        partnerStore.setId(1);
        pickupPoint.setPartnerStore(partnerStore);

        when(pickupService.addPickupPoint(any(PickupPoint.class))).thenReturn(pickupPoint);

        mvc.perform(post("/api/v1/pps/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pickupPoint)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pickupPoint.getId())))
                .andExpect(jsonPath("$.partnerStore.id", is(pickupPoint.getPartnerStore().getId())));

        verify(pickupService, times(1)).addPickupPoint(any(PickupPoint.class));
    }

    @DisplayName("Add Pickup Point - Failure")
    @Test
    void testAddPickupPointFailure() throws Exception {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);

        when(pickupService.addPickupPoint(any(PickupPoint.class))).thenReturn(null);

        mvc.perform(post("/api/v1/pps/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pickupPoint)))
                .andExpect(status().isBadRequest());

        verify(pickupService, times(1)).addPickupPoint(any(PickupPoint.class));
    }

    @DisplayName("Get Pickup Point Id - Success")
    @Test
    void testGetPickupPointByID() throws Exception {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);
        PartnerStore partnerStore = new PartnerStore();
        partnerStore.setId(1);

        pickupPoint.setPartnerStore(partnerStore);

        when(pickupService.getDeliveryById(1)).thenReturn(pickupPoint);

        mvc.perform(get("/api/v1/pps/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(pickupPoint.getId())))
                .andExpect(jsonPath("$.partnerStore.id", is(pickupPoint.getPartnerStore().getId())));

        verify(pickupService, times(1)).getDeliveryById(1);
    }

    @DisplayName("Get Pickup Point Id - Failure")
    @Test
    void testGetPickupPointByIDFailure() throws Exception {

        when(pickupService.getDeliveryById(99)).thenReturn(null);

        mvc.perform(get("/api/v1/pps/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pickupService, times(1)).getDeliveryById(99);
    }

    @DisplayName("Update Pickup Point Status - Success")
    @Test
    void testUpdatePickupPointStatus() throws Exception {
        // Define the input values
        int pickupPointId = 1;
        String ppStatus = "PENDING";

        // Create a sample PickupPoint object
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);
        pickupPoint.setPp_name("Test Pickup Point");

        // Define the expected response from the service
        PickupPoint expectedResponse = new PickupPoint();
        expectedResponse.setId(1);
        expectedResponse.setPp_name("Test Pickup Point");
        expectedResponse.setPp_status(PickupPEnum.ACCEPTED);

        // Mock the service method to return the expected response
        when(pickupService.updatePickupPointStatus(eq(pickupPointId), eq(ppStatus))).thenReturn(expectedResponse);

        // Perform the PUT request
        mvc.perform(put("/api/v1/pps/update/{id}", pickupPointId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ppStatus))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pp_name").value("Test Pickup Point"))
                .andExpect(jsonPath("$.pp_status").value("ACCEPTED"));


        verify(pickupService, times(1)).updatePickupPointStatus(eq(pickupPointId), eq(ppStatus));
    }

    @DisplayName("Update Pickup Point Status - Failure")
    @Test
    void testUpdatePickupPointStatusFailure() throws Exception {
        // Define the input values
        int pickupPointId = 1;
        String ppStatus = "PENDING";

        // Create a sample PickupPoint object
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);
        pickupPoint.setPp_name("Test Pickup Point");

        // Define the expected response from the service
        PickupPoint expectedResponse = new PickupPoint();
        expectedResponse.setId(1);
        expectedResponse.setPp_name("Test Pickup Point");
        expectedResponse.setPp_status(PickupPEnum.ACCEPTED);

        // Mock the service method to return the expected response
        when(pickupService.updatePickupPointStatus(eq(pickupPointId), eq(ppStatus))).thenReturn(null);

        // Perform the PUT request
        mvc.perform(put("/api/v1/pps/update/{id}", pickupPointId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ppStatus))
                .andExpect(status().isNotFound());

        verify(pickupService, times(1)).updatePickupPointStatus(eq(pickupPointId), eq(ppStatus));
    }

    @DisplayName("Get All Pickup Points - Success")
    @Test
    void testGetAllPickupPoints() throws Exception {
        // Create a list of sample PickupPoint objects
        List<PickupPoint> pickupPoints = new ArrayList<>();
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setId(1);
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setPp_status(PickupPEnum.ACCEPTED);
        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setId(2);
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setPp_status(PickupPEnum.ACCEPTED);
        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.getAllPickupPoints()).thenReturn(pickupPoints);

        // Perform the GET request
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].pp_name").value("Pickup Point 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].pp_name").value("Pickup Point 2"));

        verify(pickupService, times(1)).getAllPickupPoints();
    }

    @DisplayName("Get All Pickup Points - Failure")
    @Test
    void testGetAllPickupPointsFailure() throws Exception {

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.getAllPickupPoints()).thenReturn(null);

        // Perform the GET request
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/all"))
                .andExpect(status().isNotFound());

        verify(pickupService, times(1)).getAllPickupPoints();
    }

    @DisplayName("Get All Pickup Points By City - Success")
    @Test
    void testGetAllPickupPointsByCity() throws Exception {
        // Create a list of sample PickupPoint objects
        List<PickupPoint> pickupPoints = new ArrayList<>();
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setId(1);
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setCity("City A");
        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setId(2);
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setCity("City A");
        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.getAllPickupPointsByCity(anyString())).thenReturn(pickupPoints);

        // Perform the GET request with the city parameter
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/city/{city}", "City A"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].pp_name").value("Pickup Point 1"))
                .andExpect(jsonPath("$[0].city").value("City A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].pp_name").value("Pickup Point 2"))
                .andExpect(jsonPath("$[1].city").value("City A"));

        verify(pickupService, times(1)).getAllPickupPointsByCity(anyString());
    }

    @DisplayName("Get All Pickup Points By City - Failure")
    @Test
    void testGetAllPickupPointsByCityFailure() throws Exception {

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.getAllPickupPointsByCity(anyString())).thenReturn(null);

        // Perform the GET request with the city parameter
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/city/{city}", "City A"))
                .andExpect(status().isNotFound());

        verify(pickupService, times(1)).getAllPickupPointsByCity(anyString());
    }

    @DisplayName("Get Total Number of Pickup Points - Success")
    @Test
    void testGetTotalNumberOfPickupPoints() throws Exception {
        // Create a list of sample PickupPoint objects
        List<PickupPoint> pickupPoints = new ArrayList<>();
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setId(1);
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setCity("City A");
        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setId(2);
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setCity("City A");
        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.countPickupPoints()).thenReturn(pickupPoints.size());

        // Perform the GET request with the city parameter
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/total"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(2));

        verify(pickupService, times(1)).countPickupPoints();
    }

    @DisplayName("Get Total Number of Pickup Points - Failure")
    @Test
    void testGetTotalNumberOfPickupPointsFailure() throws Exception {

        // Mock the service method to return the sample PickupPoint list
        when(pickupService.countPickupPoints()).thenReturn(0);

        // Perform the GET request with the city parameter
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/pps/total"))
                .andExpect(status().isNotFound());

        verify(pickupService, times(1)).countPickupPoints();
    }






}
