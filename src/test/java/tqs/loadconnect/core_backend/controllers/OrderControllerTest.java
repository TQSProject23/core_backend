package tqs.loadconnect.core_backend.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.OrderService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    private PartnerStore partnerStore1;
    private PartnerStore partnerStore2;
    private PartnerStore partnerStore3;

    private PickupPoint pickupPoint1;
    private PickupPoint pickupPoint2;
    private PickupPoint pickupPoint3;
    private PickupPoint pickupPoint4;

    private Order order1;
    private Order order2;
    private Order order3;

    List<Order> allOrders;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);

        // partner stores
        partnerStore1 = new PartnerStore();
        partnerStore1.setPs_name("Store 1");
        partnerStore1.setAddress("Address 1");

        partnerStore2 = new PartnerStore();
        partnerStore2.setPs_name("Store 2");
        partnerStore2.setAddress("Address 2");

        // pickup points
        pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setPartnerStore(partnerStore1);

        pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setPartnerStore(partnerStore2);

        pickupPoint3 = new PickupPoint();
        pickupPoint3.setPp_name("Pickup Point 3");
        pickupPoint3.setAddress("Address 3");
        pickupPoint3.setPartnerStore(partnerStore3);

        pickupPoint4 = new PickupPoint();
        pickupPoint4.setPp_name("Pickup Point 4");
        pickupPoint4.setAddress("Address 4");
        pickupPoint4.setPartnerStore(partnerStore3);

        LocalDate d1 = LocalDate.of(2021, 1, 1);
        LocalDate d2 = LocalDate.of(2021, 1, 2);
        LocalDate d3 = LocalDate.of(2021, 1, 3);


        // ORDERS
        order1 = new Order();
        order1.setDescription("Item 1");
        order1.setPrice(10.99f);
        order1.setWeight(0.5f);
        order1.setDateOrdered(d1);
        order1.setExpectedDeliveryDate(d2);
        order1.setPickup_date(d3);
        order1.setStatus(OrderStatusEnum.PENDING);
        order1.setClientName("John Doe");
        order1.setClientEmail("john@example.com");
        order1.setPickupPoint(pickupPoint1);

        order2 = new Order();
        order2.setDescription("Item 2");
        order2.setPrice(19.99f);
        order2.setWeight(1.2f);
        order2.setDateOrdered(d1);
        order2.setExpectedDeliveryDate(d2);
        order2.setPickup_date(d3);
        order2.setStatus(OrderStatusEnum.IN_TRANSIT);
        order2.setClientName("Jane Smith");
        order2.setClientEmail("jane@example.com");
        order2.setPickupPoint(pickupPoint2);

        order3 = new Order();
        order3.setDescription("Item 3");
        order3.setPrice(5.99f);
        order3.setWeight(0.3f);
        order3.setDateOrdered(d1);
        order3.setExpectedDeliveryDate(d2);
        order3.setPickup_date(d3);
        order3.setStatus(OrderStatusEnum.DELIVERED);
        order3.setClientName("Alice Johnson");
        order3.setClientEmail("alice@example.com");
        order3.setPickupPoint(pickupPoint2);

        allOrders = List.of(order1, order2, order3);

    }

    @DisplayName("Get all orders")
    @Test
    // @Disabled
    void getAllOrders() throws Exception {

        when(orderService.getAllDeliveries()).thenReturn(allOrders);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/all")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(3))
                .body("[0].clientName", equalTo(order1.getClientName()))
                .body("[1].clientName", equalTo(order2.getClientName()))
                .body("[2].clientName", equalTo(order3.getClientName()));

        verify(orderService, times(1)).getAllDeliveries();
    }

    @DisplayName("Create new order")
    @Test
    void createNewOrder() throws Exception {
        // Create a sample order
        Order order = new Order();
        order.setDescription("Item 1");
        order.setPrice(10.99f);
        order.setClientName("John Doe");
        order.setClientEmail("john@example.com");

        // Mock the service method
        when(orderService.createOrder(any(Order.class))).thenReturn(order);

        // Convert the object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBodyJson = objectMapper.writeValueAsString(order);

        // Perform the POST request
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post("/api/v1/orders/new")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("clientName", equalTo(order.getClientName()))
                .body("clientEmail", equalTo(order.getClientEmail()))
                .body("description", equalTo(order.getDescription()))
                .body("price", equalTo(order.getPrice()));
        // Verify the service method is called
        verify(orderService, times(1)).createOrder(any(Order.class));
    }

    @DisplayName("Get order by id")
    @Test
    void getOrderById() throws Exception {
        Integer orderId = 0; //order1 ID = 0

        when(orderService.getDeliveryById(orderId)).thenReturn(order1);
        System.out.println("order1: " + order1.getId());

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/{id}", orderId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("clientName", equalTo(order1.getClientName()))
                .body("clientEmail", equalTo(order1.getClientEmail()))
                .body("description", equalTo(order1.getDescription()))
                .body("price", equalTo(order1.getPrice()));
    }

    @DisplayName("Get order by id - Not Found")
    @Test
    void getOrderByIdNotFound() throws Exception {
        Integer orderId = 9999;

        when(orderService.getDeliveryById(orderId)).thenReturn(null);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/{id}", orderId)
                .then()
                .statusCode(404);

        verify(orderService, times(1)).getDeliveryById(orderId);
    }

    @DisplayName("Get orders by pickup point")
    @Test
    void getOrdersByPickupPoint() {
        Integer pickupPointId = 0; //pickupPoint1 ID = 0

        when(orderService.getDeliveriesByPickupPointId(pickupPointId)).thenReturn(List.of(order1));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/ppoint/{pickup_point_id}", pickupPointId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(1))
                .body("[0].clientName", is(order1.getClientName()))
                .body("[0].clientEmail", is(order1.getClientEmail()))
                .body("[0].description", is(order1.getDescription()))
                .body("[0].price", is(order1.getPrice()));

        verify(orderService, times(1)).getDeliveriesByPickupPointId(pickupPointId);
    }


    @DisplayName("Update order by id")
    @Test
    void updateOrder() {
        int orderId = 0;
        String orderStatus = "DELIVERED";

        order1.setStatus(OrderStatusEnum.DELIVERED);

        // Mock the service method
        when(orderService.updateOrder(eq(orderId), eq(orderStatus))).thenReturn(order1);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(orderStatus)
                .when()
                .put("/api/v1/orders/update/{id}", orderId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(order1.getId()))
                .body("status", is(orderStatus));

        // Verify the service method is called
        verify(orderService, times(1)).updateOrder(eq(orderId), eq(orderStatus));
    }

    @DisplayName(("total number of orders"))
    @Test
    void getTotalNumberOfOrders() throws Exception {
        int totalOrders = 3;

        when(orderService.getTotalOrders()).thenReturn(totalOrders);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/orders/total")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(equalTo(String.valueOf(totalOrders)));

        verify(orderService, times(1)).getTotalOrders();
    }

    @DisplayName("Get total number of ongoing orders")
    @Test
    void getTotalNumberOfOngoingOrders() {
        int totalOngoingOrders = 2;

        when(orderService.getTotalOnGoingOrders()).thenReturn(totalOngoingOrders);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders/total/on_going")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(is(String.valueOf(totalOngoingOrders)));

        verify(orderService, times(1)).getTotalOnGoingOrders();
    }

    @DisplayName("Get total number of orders from last month")
    @Test
    void getTotalOrdersFromLastMonth() {
        int totalOrdersFromLastMonth = 5;

        when(orderService.getTotalOrdersFromLastMonth()).thenReturn(totalOrdersFromLastMonth);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders/total/lastmonth")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(is(String.valueOf(totalOrdersFromLastMonth)));

        verify(orderService, times(1)).getTotalOrdersFromLastMonth();
    }

    @DisplayName("Get orders by partner store ID")
    @Test
    void getOrdersByPartnerStoreId() {
        int partnerStoreId = 1;

        when(orderService.getDeliveriesByPartnerStoreId(partnerStoreId)).thenReturn(List.of(order2, order3));

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders/partnerstore/{partner_store_id}", partnerStoreId)
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("", hasSize(equalTo(2)))
                .body("[0].clientName", is(order2.getClientName()))
                .body("[1].clientName", is(order3.getClientName()));

        verify(orderService, times(1)).getDeliveriesByPartnerStoreId(partnerStoreId);
    }

    @DisplayName("Get total number of deliveries by partner store ID")
    @Test
    void getTotalDeliveriesByPartnerStoreId() throws Exception {
        int partnerStoreId = 1;
        int totalDeliveries = 3;

        when(orderService.getTotalDeliveriesByPartnerStoreId(partnerStoreId)).thenReturn(totalDeliveries);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders/partnerstore/{partner_store_id}/total", partnerStoreId)
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(is(String.valueOf(totalDeliveries)));

        verify(orderService, times(1)).getTotalDeliveriesByPartnerStoreId(partnerStoreId);
    }

    @DisplayName("Get total number of ongoing deliveries by partner store ID")
    @Test
    void getTotalOngoingDeliveriesByPartnerStoreId() throws Exception {
        int partnerStoreId = 1;
        int totalOngoingDeliveries = 2;

        when(orderService.getTotalOnGoingDeliveriesByPartnerStoreId(partnerStoreId)).thenReturn(totalOngoingDeliveries);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/api/v1/orders/partnerstore/{partner_store_id}/total/on_going", partnerStoreId)
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(is(String.valueOf(totalOngoingDeliveries)));

        verify(orderService, times(1)).getTotalOnGoingDeliveriesByPartnerStoreId(partnerStoreId);
    }
}