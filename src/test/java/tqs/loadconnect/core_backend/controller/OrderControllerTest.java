package tqs.loadconnect.core_backend.controller;

import jakarta.servlet.http.Part;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.controllers.OrderController;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.OrderService;

import java.util.Date;
import java.util.List;


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

    @BeforeEach
    void setUp() {

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

        // partner stores
        partnerStore1 = new PartnerStore();
        partnerStore1.setName("Store 1");
        partnerStore1.setAddress("Address 1");

        partnerStore2 = new PartnerStore();
        partnerStore2.setName("Store 2");
        partnerStore2.setAddress("Address 2");


        // pickup points
        pickupPoint1 = new PickupPoint();
        pickupPoint1.setName("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setPartnerStore(partnerStore1);

        pickupPoint2 = new PickupPoint();
        pickupPoint2.setName("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setPartnerStore(partnerStore2);

        pickupPoint3 = new PickupPoint();
        pickupPoint3.setName("Pickup Point 3");
        pickupPoint3.setAddress("Address 3");
        pickupPoint3.setPartnerStore(partnerStore3);

        pickupPoint4 = new PickupPoint();
        pickupPoint4.setName("Pickup Point 4");
        pickupPoint4.setAddress("Address 4");
        pickupPoint4.setPartnerStore(partnerStore3);


        // add orders to pickup points
        pickupPoint1.setOrders(List.of(order1, order2));
        pickupPoint2.setOrders(List.of(order3));
        
    }

}
