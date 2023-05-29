package tqs.loadconnect.core_backend;

import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.controllers.OrderController;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.OrderService;

import java.util.Date;
import java.util.List;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
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

        // ORDERS
        order1 = new Order();
        order1.setDescription("Item 1");
        order1.setPrice(10.99f);
        order1.setWeight(0.5f);
        order1.setDateOrdered(new Date());
        order1.setExpectedDeliveryDate(new Date());
        order1.setPickup_date(new Date());
        order1.setStatus(OrderStatusEnum.PENDING);
        order1.setClientName("John Doe");
        order1.setClientEmail("john@example.com");
        order1.setPickupPoint(pickupPoint1);

        order2 = new Order();
        order2.setDescription("Item 2");
        order2.setPrice(19.99f);
        order2.setWeight(1.2f);
        order2.setDateOrdered(new Date());
        order2.setExpectedDeliveryDate(new Date());
        order2.setPickup_date(new Date());
        order2.setStatus(OrderStatusEnum.IN_TRANSIT);
        order2.setClientName("Jane Smith");
        order2.setClientEmail("jane@example.com");
        order2.setPickupPoint(pickupPoint2);

        order3 = new Order();
        order3.setDescription("Item 3");
        order3.setPrice(5.99f);
        order3.setWeight(0.3f);
        order3.setDateOrdered(new Date());
        order3.setExpectedDeliveryDate(new Date());
        order3.setPickup_date(new Date());
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

        mvc.perform(get("/api/v1/orders/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(3))))
                .andExpect(jsonPath("$[0].clientName", is(order1.getClientName())))
                .andExpect(jsonPath("$[1].clientName", is(order2.getClientName())))
                .andExpect(jsonPath("$[2].clientName", is(order3.getClientName())))
        ;

        verify(orderService, times(1)).getAllDeliveries();

    }

   
}