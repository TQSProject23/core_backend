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
import tqs.loadconnect.core_backend.controllers.OrderController;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.OrderService;

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

    private Order order1 = new Order("Description1", )
    private Order order2;
    private Order order3;

    @BeforeEach
    void setUp() {

        // create a couple of partner stores
        partnerStore1 = new PartnerStore("Partner Store 1", "Rua do Partner Store 1", new List<Order>());


    }

}
