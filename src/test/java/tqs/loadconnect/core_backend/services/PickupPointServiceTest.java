package tqs.loadconnect.core_backend.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.loadconnect.core_backend.Utils.Enums.PickupPEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;
import tqs.loadconnect.core_backend.services.PartnerStoreService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PickupPointServiceTest {
    @Mock
    private PickupPRepository pickupPRepository;

    @Mock
    private PartnerStoreService partnerStoreService;

    @InjectMocks
    private PickupPointService pickupPointService;

    @Test
    @DisplayName("Test addPickupPoint - Pickup Point added successfully")
    void testAddPickupPoint() {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);
        PartnerStore partnerStore = new PartnerStore();
        partnerStore.setId(1);
        pickupPoint.setPartnerStore(partnerStore);

        when(pickupPRepository.findById(1L)).thenReturn(Optional.empty());
        when(partnerStoreService.getPartnerStoreById(1)).thenReturn(partnerStore);
        when(pickupPRepository.save(pickupPoint)).thenReturn(pickupPoint);

        PickupPoint result = pickupPointService.addPickupPoint(pickupPoint);

        assertNotNull(result);
        assertEquals(pickupPoint, result);
        verify(pickupPRepository).save(pickupPoint);
    }

    @Test
    @DisplayName("Test addPickupPoint - Pickup Point already exists")
    void testAddPickupPointAlreadyExists() {
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(1);

        when(pickupPRepository.findById(1L)).thenReturn(Optional.of(pickupPoint));

        PickupPoint result = pickupPointService.addPickupPoint(pickupPoint);

        assertNull(result);
        verify(partnerStoreService, never()).getPartnerStoreById(anyInt());
        verify(pickupPRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test getAllPickupPoints - Pickup Points found")
    void testGetAllPickupPointsFound() {
        List<PickupPoint> pickupPoints = new ArrayList<>();
        pickupPoints.add(new PickupPoint());
        pickupPoints.add(new PickupPoint());

        when(pickupPRepository.findAll()).thenReturn(pickupPoints);

        List<PickupPoint> result = pickupPointService.getAllPickupPoints();

        assertNotNull(result);
        assertEquals(pickupPoints, result);
    }

    @Test
    @DisplayName("Test getAllPickupPoints - No Pickup Points found")
    void testGetAllPickupPointsNotFound() {
        List<PickupPoint> pickupPoints = new ArrayList<>();

        when(pickupPRepository.findAll()).thenReturn(pickupPoints);

        List<PickupPoint> result = pickupPointService.getAllPickupPoints();

        assertNotNull(result);
        assertEquals(pickupPoints, result);
    }

    @Test
    @DisplayName("Test getAllPickupPoints - Return all pickup points with status ACCEPTED")
    void testGetAllPickupPoints() {
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_status(PickupPEnum.ACCEPTED);

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_status(PickupPEnum.PENDING);

        List<PickupPoint> pickupPoints = new ArrayList<>();
        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);

        when(pickupPRepository.findAll()).thenReturn(pickupPoints);

        List<PickupPoint> result = pickupPointService.getAllPickupPoints();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pickupPoint1, result.get(0));
    }

    @Test
    @DisplayName("Test getAllPickupPointsByCity - Return pickup points by city")
    void testGetAllPickupPointsByCity() {
        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setCity("New York");

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setCity("Los Angeles");

        List<PickupPoint> pickupPoints = new ArrayList<>();
        pickupPoints.add(pickupPoint1);
        pickupPoints.add(pickupPoint2);

        when(pickupPRepository.findAllByCity("New York")).thenReturn(Collections.singletonList(pickupPoint1));

        List<PickupPoint> result = pickupPointService.getAllPickupPointsByCity("New York");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pickupPoint1, result.get(0));
    }


    @Test
    @DisplayName("Test updatePickupPointStatus - Pickup Point status updated successfully")
    void testUpdatePickupPointStatus() {
        int pickupPointId = 1;
        String ppStatus = "ACCEPTED";

        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(pickupPointId);

        when(pickupPRepository.findById((long) pickupPointId)).thenReturn(Optional.of(pickupPoint));
        when(pickupPRepository.save(pickupPoint)).thenReturn(pickupPoint);

        PickupPoint result = pickupPointService.updatePickupPointStatus(pickupPointId, ppStatus);

        assertNotNull(result);
        assertEquals(PickupPEnum.ACCEPTED, result.getPp_status());
        verify(pickupPRepository).save(pickupPoint);
    }

    @Test
    @DisplayName("Test countPickupPoints - Return the count of pickup points")
    void testCountPickupPoints() {
        List<PickupPoint> pickupPoints = new ArrayList<>();
        pickupPoints.add(new PickupPoint());
        pickupPoints.add(new PickupPoint());
        pickupPoints.add(new PickupPoint());

        when(pickupPRepository.findAll()).thenReturn(pickupPoints);

        int result = pickupPointService.countPickupPoints();

        assertEquals(3, result);
    }

    @Test
    @DisplayName("Test countPickupPointsLastMonth - Return the count of pickup points from last month")
    void testCountPickupPointsLastMonth() {
        // TODO: Implement this test case
    }

    @Test
    @DisplayName("Test getDeliveryById - Return pickup point by ID")
    void testGetDeliveryById() {
        int ppId = 1;
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(ppId);

        when(pickupPRepository.findById((long) ppId)).thenReturn(Optional.of(pickupPoint));

        PickupPoint result = pickupPointService.getDeliveryById(ppId);

        assertNotNull(result);
        assertEquals(pickupPoint, result);
    }

    @Test
    @DisplayName("Test getAllOrdersByPickupPoint - Return all orders by pickup point ID")
    void testGetAllOrdersByPickupPoint() {
        int pickupPointId = 1;
        PickupPoint pickupPoint = new PickupPoint();
        pickupPoint.setId(pickupPointId);

        Order order1 = new Order();
        Order order2 = new Order();

        pickupPoint.addOrder(order1);
        pickupPoint.addOrder(order2);

        when(pickupPRepository.findById((long) pickupPointId)).thenReturn(Optional.of(pickupPoint));

        List<Order> result = pickupPointService.getAllOrdersByPickupPoint(pickupPointId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(order1, result.get(0));
        assertEquals(order2, result.get(1));
    }
}
