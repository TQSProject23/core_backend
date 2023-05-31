package tqs.loadconnect.core_backend.services;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.OrderRepository;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PickupPRepository pickupPRepository;

    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, pickupPRepository);
    }

    @Test
    @DisplayName("Test getAllDeliveries()")
    void testGetAllDeliveries() {
        // Mock data
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        // Mock behavior
        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Invoke the method
        List<Order> actualOrders = orderService.getAllDeliveries();

        // Verify the result
        assertThat(actualOrders, equalTo(expectedOrders));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getDeliveryById() - Order found")
    void testGetDeliveryByIdOrderFound() {
        // Mock data
        int orderId = 1;
        Order expectedOrder = new Order();
        expectedOrder.setId(orderId);

        // Mock behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Invoke the method
        Order actualOrder = orderService.getDeliveryById(orderId);

        // Verify the result
        assertThat(actualOrder, equalTo(expectedOrder));
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    @DisplayName("Test getDeliveryById() - Order not found")
    void testGetDeliveryByIdOrderNotFound() {
        // Mock data
        int orderId = 1;

        // Mock behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Invoke the method
        Order actualOrder = orderService.getDeliveryById(orderId);

        // Verify the result
        assertThat(actualOrder, nullValue());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    @DisplayName("Test getDeliveriesByPickupPointId()")
    void testGetDeliveriesByPickupPointId() {
        // Mock data
        int pickupPointId = 1;
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        // Mock behavior
        when(orderRepository.findByPickupPointId(pickupPointId)).thenReturn(expectedOrders);

        // Invoke the method
        List<Order> actualOrders = orderService.getDeliveriesByPickupPointId(pickupPointId);

        // Verify the result
        assertThat(actualOrders, equalTo(expectedOrders));
        verify(orderRepository, times(1)).findByPickupPointId(pickupPointId);
    }

    @Test
    @Disabled
    @DisplayName("Test Create order - Valid")
    void testCreateOrderValid() {

        Order order1 = new Order();
        PartnerStore ps1 = new PartnerStore();
        ps1.setPs_name("Store 1");
        ps1.setAddress("Address 1");

        PickupPoint pp1 = new PickupPoint();
        pp1 = new PickupPoint();
        pp1.setId(69);
        pp1.setPp_name("Pickup Point 1");
        pp1.setAddress("Address 1");
        pp1.setPartnerStore(ps1);
        pp1.setOrders(List.of(order1));

        LocalDate d1 = LocalDate.of(2021, 1, 1);
        LocalDate d2 = LocalDate.of(2021, 1, 2);
        LocalDate d3 = LocalDate.of(2021, 1, 3);

        // ORDERS
        order1 = new Order();
        order1.setId(1);
        order1.setDescription("Item 1");
        order1.setPrice(10.9F);
        order1.setWeight(0.5F);
        order1.setStatus(OrderStatusEnum.PENDING);
        order1.setClientName("John Doe");
        order1.setClientEmail("john@example.com");
        order1.setPickupPoint(pp1);


        // Mock behavior
        when(orderRepository.save(order1)).thenReturn(order1);

        // Invoke the method
        Order actualOrder = orderService.createOrder(order1);

        // Verify the result
        assertThat(actualOrder, equalTo(order1));

        verify(orderRepository, times(1)).save(order1);


    }

    @Test
    @DisplayName("Test updateOrder() - Order found")
    void testUpdateOrderOrderFound() {
        // Mock data
        int orderId = 1;
        String orderStatus = "IN_TRANSIT";
        Order order = new Order();
        order.setId(orderId);
        order.setStatus(OrderStatusEnum.PENDING);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        // Invoke the method
        Order updatedOrder = orderService.updateOrder(orderId, orderStatus);

        // Verify the result
        assertThat(updatedOrder.getId(), equalTo(orderId));
        assertThat(updatedOrder.getStatus(), equalTo(OrderStatusEnum.IN_TRANSIT));
        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    @DisplayName("Test updateOrder() - Order not found")
    void testUpdateOrderOrderNotFound() {
        // Mock data
        int orderId = 1;
        String orderStatus = "IN_TRANSIT";

        // Mock behavior
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Invoke the method
        Order updatedOrder = orderService.updateOrder(orderId, orderStatus);

        // Verify the result
        assertThat(updatedOrder, nullValue());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    @DisplayName("Test getTotalOrders()")
    void testGetTotalOrders() {
        // Mock data
        List<Order> orders = Arrays.asList(new Order(), new Order(), new Order());

        // Mock behavior
        when(orderRepository.findAll()).thenReturn(orders);

        // Invoke the method
        Integer totalOrders = orderService.getTotalOrders();

        // Verify the result
        assertThat(totalOrders, equalTo(orders.size()));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getTotalOnGoingOrders()")
    void testGetTotalOnGoingOrders() {
        // Mock data
        List<Order> orders = Arrays.asList(
                createOrderWithStatus(OrderStatusEnum.IN_TRANSIT),
                createOrderWithStatus(OrderStatusEnum.PENDING),
                createOrderWithStatus(OrderStatusEnum.DELIVERED)
        );

        // Mock behavior
        when(orderRepository.findAll()).thenReturn(orders);

        // Invoke the method
        Integer totalOnGoingOrders = orderService.getTotalOnGoingOrders();

        // Verify the result
        assertThat(totalOnGoingOrders, equalTo(2));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test getTotalOrdersFromLastMonth()")
    void testGetTotalOrdersFromLastMonth() {
        // Mock data
        List<Order> orders = Arrays.asList(new Order(), new Order());

        // Mock behavior
        when(orderRepository.findAll()).thenReturn(orders);

        // Invoke the method
        Integer totalOrdersFromLastMonth = orderService.getTotalOrdersFromLastMonth();

        // Verify the result
        assertThat(totalOrdersFromLastMonth, equalTo(orders.size()));
        verify(orderRepository, times(1)).findAll();
    }

    private @NotNull Order createOrderWithStatus(OrderStatusEnum status) {
        Order order = new Order();
        order.setStatus(status);
        return order;
    }

    private @NotNull Order createOrderWithPartnerStoreId(int partnerStoreId) {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.getPartnerStore().setId(partnerStoreId);
        Order order = new Order();
        order.setPickupPoint(pickupPoint);
        return order;
    }
}

