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
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;
import tqs.loadconnect.core_backend.services.PartnerStoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerStoreServiceTest {

    @Mock
    private PartnerStoreRepository partnerStoreRepository;

    @InjectMocks
    private PartnerStoreService partnerStoreService;

    @Test
    @DisplayName("Test addStore - Store already exists")
    void testAddStoreExistingStore() {
        PartnerStore store = new PartnerStore();
        store.setId(1);

        when(partnerStoreRepository.findById((long) store.getId())).thenReturn(Optional.of(store));

        PartnerStore result = partnerStoreService.addStore(store);

        assertNull(result);
        verify(partnerStoreRepository, never()).save(store);
    }

    @Test
    @DisplayName("Test addStore - Store does not exist")
    void testAddStoreNewStore() {
        PartnerStore store = new PartnerStore();
        store.setId(1);

        when(partnerStoreRepository.findById((long) store.getId())).thenReturn(Optional.empty());
        when(partnerStoreRepository.save(store)).thenReturn(store);

        PartnerStore result = partnerStoreService.addStore(store);

        assertNotNull(result);
        assertEquals(store, result);
        verify(partnerStoreRepository, times(1)).save(store);
    }

    @Test
    @DisplayName("Test getAllStores")
    void testGetAllStores() {
        List<PartnerStore> stores = new ArrayList<>();
        stores.add(new PartnerStore());
        stores.add(new PartnerStore());

        when(partnerStoreRepository.findAll()).thenReturn(stores);

        List<PartnerStore> result = partnerStoreService.getAllStores();

        assertNotNull(result);
        assertEquals(stores.size(), result.size());
        assertEquals(stores, result);
    }

    @Test
    @DisplayName("Test getStoreById - Store found")
    void testGetStoreByIdFound() {
        PartnerStore store = new PartnerStore();
        store.setId(1);

        when(partnerStoreRepository.findById((long) store.getId())).thenReturn(Optional.of(store));

        PartnerStore result = partnerStoreService.getStoreById((long) store.getId());

        assertNotNull(result);
        assertEquals(store, result);
    }

    @Test
    @DisplayName("Test getStoreById - Store not found")
    void testGetStoreByIdNotFound() {
        Long storeId = 1L;

        when(partnerStoreRepository.findById(storeId)).thenReturn(Optional.empty());

        PartnerStore result = partnerStoreService.getStoreById(storeId);

        assertNull(result);
    }

    @Test
    @DisplayName("Test getPartnerStoreById - Store found")
    void testGetPartnerStoreByIdFound() {
        int storeId = 1;
        PartnerStore store = new PartnerStore();
        store.setId(storeId);

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.of(store));

        PartnerStore result = partnerStoreService.getPartnerStoreById(storeId);

        assertNotNull(result);
        assertEquals(store, result);
    }

    @Test
    @DisplayName("Test getPartnerStoreById - Store not found")
    void testGetPartnerStoreByIdNotFound() {
        int storeId = 1;

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.empty());

        PartnerStore result = partnerStoreService.getPartnerStoreById(storeId);

        assertNull(result);
    }

    @Test
    @DisplayName("Test getAllPickupPointsByPartnerStoreId - Store found")
    void testGetAllPickupPointsByPartnerStoreIdFound() {
        int storeId = 1;
        PartnerStore store = new PartnerStore();
        store.setId(storeId);

        PickupPoint pickupPoint1 = new PickupPoint();
        PickupPoint pickupPoint2 = new PickupPoint();
        store.addPickupPoint(pickupPoint1);
        store.addPickupPoint(pickupPoint2);

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.of(store));

        List<PickupPoint> result = partnerStoreService.getAllPickupPointsByPartnerStoreId(storeId);

        assertNotNull(result);
        assertEquals(store.getPickupPoints(), result);
    }

    @Test
    @DisplayName("Test getAllPickupPointsByPartnerStoreId - Store not found")
    void testGetAllPickupPointsByPartnerStoreIdNotFound() {
        int storeId = 1;

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.empty());

        List<PickupPoint> result = partnerStoreService.getAllPickupPointsByPartnerStoreId(storeId);

        assertNull(result);
    }

    @Test
    @DisplayName("Test getTotalPartnerStores")
    void testGetTotalPartnerStores() {
        List<PartnerStore> stores = new ArrayList<>();
        stores.add(new PartnerStore());
        stores.add(new PartnerStore());

        when(partnerStoreRepository.findAll()).thenReturn(stores);

        Integer result = partnerStoreService.getTotalPartnerStores();

        assertNotNull(result);
        assertEquals(stores.size(), result);
    }

    @Test
    @DisplayName("Test getTotalPartnerStoresLastMonth")
    void testGetTotalPartnerStoresLastMonth() {
        List<PartnerStore> stores = new ArrayList<>();
        stores.add(new PartnerStore());
        stores.add(new PartnerStore());

        when(partnerStoreRepository.findAll()).thenReturn(stores);

        Integer result = partnerStoreService.getTotalPartnerStoresLastMonth();

        assertNotNull(result);
        assertEquals(stores.size(), result);
    }

    @Test
    @DisplayName("Test getNumberOfPickupPointsByPartnerStoreId - Store found")
    void testGetNumberOfPickupPointsByPartnerStoreIdFound() {
        int storeId = 1;
        PartnerStore store = new PartnerStore();
        store.setId(storeId);

        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_status(PickupPEnum.ACCEPTED);
        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_status(PickupPEnum.ACCEPTED);
        PickupPoint pickupPoint3 = new PickupPoint();
        pickupPoint3.setPp_status(PickupPEnum.PENDING);
        store.addPickupPoint(pickupPoint1);
        store.addPickupPoint(pickupPoint2);
        store.addPickupPoint(pickupPoint3);

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.of(store));

        Integer result = partnerStoreService.getNumberOfPickupPointsByPartnerStoreId(storeId);

        assertNotNull(result);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test getNumberOfPickupPointsByPartnerStoreId - Store not found")
    void testGetNumberOfPickupPointsByPartnerStoreIdNotFound() {
        int storeId = 1;

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.empty());

        Integer result = partnerStoreService.getNumberOfPickupPointsByPartnerStoreId(storeId);

        assertNull(result);
    }

    @Test
    @DisplayName("Test getStoreByEmail - Store found")
    void testGetStoreByEmailFound() {
        String email = "test@example.com";
        PartnerStore store = new PartnerStore();
        store.setEmail(email);

        when(partnerStoreRepository.findByEmail(email)).thenReturn(store);

        PartnerStore result = partnerStoreService.getStoreByEmail(email);

        assertNotNull(result);
        assertEquals(store, result);
    }

    @Test
    @DisplayName("Test getStoreByEmail - Store not found")
    void testGetStoreByEmailNotFound() {
        String email = "test@example.com";

        when(partnerStoreRepository.findByEmail(email)).thenReturn(null);

        PartnerStore result = partnerStoreService.getStoreByEmail(email);

        assertNull(result);
    }

    @Test
    @DisplayName("Test getNumberOfPendingPickupPointsByPartnerStoreId - Store found")
    void testGetNumberOfPendingPickupPointsByPartnerStoreIdFound() {
        int storeId = 1;
        PartnerStore store = new PartnerStore();
        store.setId(storeId);

        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_status(PickupPEnum.PENDING);
        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_status(PickupPEnum.PENDING);
        PickupPoint pickupPoint3 = new PickupPoint();
        pickupPoint3.setPp_status(PickupPEnum.ACCEPTED);
        store.addPickupPoint(pickupPoint1);
        store.addPickupPoint(pickupPoint2);
        store.addPickupPoint(pickupPoint3);

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.of(store));

        Integer result = partnerStoreService.getNumberOfPendingPickupPointsByPartnerStoreId(storeId);

        assertNotNull(result);
        assertEquals(2, result);
    }

    @Test
    @DisplayName("Test getNumberOfPendingPickupPointsByPartnerStoreId - Store not found")
    void testGetNumberOfPendingPickupPointsByPartnerStoreIdNotFound() {
        int storeId = 1;

        when(partnerStoreRepository.findById((long) storeId)).thenReturn(Optional.empty());

        Integer result = partnerStoreService.getNumberOfPendingPickupPointsByPartnerStoreId(storeId);

        assertNull(result);
    }

}
