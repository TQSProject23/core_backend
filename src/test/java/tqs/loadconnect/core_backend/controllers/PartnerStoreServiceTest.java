package tqs.loadconnect.core_backend.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import tqs.loadconnect.core_backend.Utils.Enums.PickupPEnum;
import tqs.loadconnect.core_backend.models.PartnerStore;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.PartnerStoreService;

import java.time.LocalDate;
import java.util.List;

@WebMvcTest(PartnerStoreController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PartnerStoreServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PartnerStoreService partnerStoreService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @DisplayName("Add a new partner store")
    @Test
    void testAddStore() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");

        when(partnerStoreService.addStore(any(PartnerStore.class))).thenReturn(store);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(store)
                .when()
                .post("/api/v1/partnerstore/add")
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo(store.getId()))
                .body("ps_name", equalTo(store.getPs_name()))
                .body("email", equalTo(store.getEmail()))
                .body("address", equalTo(store.getAddress()));

        verify(partnerStoreService, times(1)).addStore(any(PartnerStore.class));
    }

    @DisplayName("Add a new partner store - Invalid")
    @Test
    void testAddStoreInvalidRequest() {
        when(partnerStoreService.addStore(any(PartnerStore.class))).thenReturn(null);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/api/v1/partnerstore/add")
                .then()
                .status(HttpStatus.BAD_REQUEST);

        verify(partnerStoreService, times(0)).addStore(any(PartnerStore.class));

    }

    @DisplayName("Get all partner stores")
    @Test
    void testGetAllPartnerStores() {
        PartnerStore store1 = new PartnerStore();
        store1.setPs_name("Store 1");
        store1.setAddress("Address 1");

        PartnerStore store2 = new PartnerStore();
        store2.setPs_name("Store 2");
        store2.setAddress("Address 2");

        List<PartnerStore> stores = List.of(store1, store2);

        when(partnerStoreService.getAllStores()).thenReturn(stores);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/all")
                .then()
                .status(HttpStatus.OK)
                .body("$", hasSize(2))
                .body("[0].id", equalTo(store1.getId()))
                .body("[0].ps_name", equalTo(store1.getPs_name()))
                .body("[0].email", equalTo(store1.getEmail()))
                .body("[0].address", equalTo(store1.getAddress()))
                .body("[1].id", equalTo(store2.getId()))
                .body("[1].ps_name", equalTo(store2.getPs_name()))
                .body("[1].email", equalTo(store2.getEmail()))
                .body("[1].address", equalTo(store2.getAddress()));

        verify(partnerStoreService, times(1)).getAllStores();
    }

    @DisplayName("Get all partner stores - Invalid")
    @Test
    void testGetAllPartnerStoresInvalidRequest() {
        when(partnerStoreService.getAllStores()).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/all")
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getAllStores();
    }

    @DisplayName("Get partner store by id")
    @Test
    void testGetPartnerStoreById() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");

        when(partnerStoreService.getStoreById((long) store.getId())).thenReturn(store);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/0")
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo(store.getId()))
                .body("ps_name", equalTo(store.getPs_name()))
                .body("email", equalTo(store.getEmail()))
                .body("address", equalTo(store.getAddress()));

        verify(partnerStoreService, times(1)).getStoreById((long) store.getId());

    }

    @DisplayName("Get partner store by id - Invalid")
    @Test
    void testGetPartnerStoreByIdInvalidRequest() {
        when(partnerStoreService.getStoreById((long) 99)).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/99")
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getStoreById((long) 99);
    }

    @DisplayName("Get partner store by email")
    @Test
void testGetPartnerStoreByEmail() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");
        store.setEmail("test@gmail.com");

        when(partnerStoreService.getStoreByEmail(store.getEmail())).thenReturn(store);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/email/" + store.getEmail())
                .then()
                .status(HttpStatus.OK)
                .body("id", equalTo(store.getId()))
                .body("ps_name", equalTo(store.getPs_name()))
                .body("email", equalTo(store.getEmail()))
                .body("address", equalTo(store.getAddress()));

        verify(partnerStoreService, times(1)).getStoreByEmail(store.getEmail());

    }

    @DisplayName("Get partner store by email - Invalid")
    @Test
    void testGetPartnerStoreByEmailInvalidRequest() {

        when(partnerStoreService.getStoreByEmail("null")).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/email/" + null)
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getStoreByEmail("null");
    }

    @DisplayName("get all pickup points by partner store id")
    @Test
    void testGetAllPickupPointsByPartnerStoreId() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");

        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setPartnerStore(store);

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setPartnerStore(store);

        List<PickupPoint> pickupPoints = List.of(pickupPoint1, pickupPoint2);

        when(partnerStoreService.getAllPickupPointsByPartnerStoreId(store.getId())).thenReturn(pickupPoints);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/"+store.getId()+"/pps")
                .then()
                .status(HttpStatus.OK)
                .body("$", hasSize(2))
                .body("[0].id", equalTo(pickupPoint1.getId()))
                .body("[0].pp_name", equalTo(pickupPoint1.getPp_name()))
                .body("[0].address", equalTo(pickupPoint1.getAddress()))
                .body("[1].id", equalTo(pickupPoint2.getId()))
                .body("[1].pp_name", equalTo(pickupPoint2.getPp_name()))
                .body("[1].address", equalTo(pickupPoint2.getAddress()));

        verify(partnerStoreService, times(1)).getAllPickupPointsByPartnerStoreId( store.getId());
    }

    @DisplayName("get all pickup points by partner store id - Invalid")
    @Test
    void testGetAllPickupPointsByPartnerStoreIdInvalidRequest() {
        when(partnerStoreService.getAllPickupPointsByPartnerStoreId(99)).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/99/pps")
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getAllPickupPointsByPartnerStoreId(99);
    }

    @DisplayName("get number of pickup points by partner store ACCEPTED ONES")
    @Test
    void testGetNumberOfPickupPointsByPartnerStoreId() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");

        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setPartnerStore(store);
        pickupPoint1.setPp_status(PickupPEnum.ACCEPTED);

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setPartnerStore(store);
        pickupPoint2.setPp_status(PickupPEnum.ACCEPTED);

        List<PickupPoint> pickupPoints = List.of(pickupPoint1, pickupPoint2);

        when(partnerStoreService.getNumberOfPickupPointsByPartnerStoreId(store.getId())).thenReturn(pickupPoints.size());

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/"+store.getId()+"/pps/total")
                .then()
                .status(HttpStatus.OK)
                .body(equalTo(String.valueOf(pickupPoints.size())));

        verify(partnerStoreService, times(1)).getNumberOfPickupPointsByPartnerStoreId( store.getId());
    }

    @DisplayName("get number of pickup points by partner store ACCEPTED ONES - Invalid")
    @Test
    void testGetNumberOfPickupPointsByPartnerStoreIdInvalidRequest() {
        when(partnerStoreService.getNumberOfPickupPointsByPartnerStoreId(99)).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/99/pps/total")
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getNumberOfPickupPointsByPartnerStoreId(99);
    }

    @DisplayName("get number of pickup points PENDING by partner store")
    @Test
    void testGetNumberOfPickupPointsPendingByPartnerStoreId() {
        PartnerStore store = new PartnerStore();
        store.setPs_name("Store 1");
        store.setAddress("Address 1");

        PickupPoint pickupPoint1 = new PickupPoint();
        pickupPoint1.setPp_name("Pickup Point 1");
        pickupPoint1.setAddress("Address 1");
        pickupPoint1.setPartnerStore(store);
        pickupPoint1.setPp_status(PickupPEnum.PENDING);

        PickupPoint pickupPoint2 = new PickupPoint();
        pickupPoint2.setPp_name("Pickup Point 2");
        pickupPoint2.setAddress("Address 2");
        pickupPoint2.setPartnerStore(store);
        pickupPoint2.setPp_status(PickupPEnum.PENDING);

        List<PickupPoint> pickupPoints = List.of(pickupPoint1, pickupPoint2);

        when(partnerStoreService.getNumberOfPendingPickupPointsByPartnerStoreId(store.getId())).thenReturn(pickupPoints.size());

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/"+store.getId()+"/pps/pending")
                .then()
                .status(HttpStatus.OK)
                .body(equalTo(String.valueOf(pickupPoints.size())));

        verify(partnerStoreService, times(1)).getNumberOfPendingPickupPointsByPartnerStoreId( store.getId());
    }

    @DisplayName("get number of pickup points PENDING by partner store - Invalid")
    @Test
    void testGetNumberOfPickupPointsPendingByPartnerStoreIdInvalidRequest() {
        when(partnerStoreService.getNumberOfPendingPickupPointsByPartnerStoreId(99)).thenReturn(null);

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/99/pps/pending")
                .then()
                .status(HttpStatus.NOT_FOUND);

        verify(partnerStoreService, times(1)).getNumberOfPendingPickupPointsByPartnerStoreId(99);
    }

    @DisplayName("get total number of partner stores")
    @Test
    void testGetTotalNumberOfPartnerStores() {
        PartnerStore store1 = new PartnerStore();
        store1.setPs_name("Store 1");
        store1.setAddress("Address 1");

        PartnerStore store2 = new PartnerStore();
        store2.setPs_name("Store 2");
        store2.setAddress("Address 2");

        List<PartnerStore> stores = List.of(store1, store2);

        when(partnerStoreService.getTotalPartnerStores()).thenReturn(stores.size());

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/total")
                .then()
                .status(HttpStatus.OK)
                .body(equalTo(String.valueOf(stores.size())));

        verify(partnerStoreService, times(1)).getTotalPartnerStores();
    }

    @DisplayName("return number of partner stores created in the last month")
    @Test
    void testGetNumberOfPartnerStoresCreatedInLastMonth() {
        PartnerStore store1 = new PartnerStore();
        store1.setPs_name("Store 1");
        store1.setAddress("Address 1");
        store1.setCreated_at(LocalDate.now());

        PartnerStore store2 = new PartnerStore();
        store2.setPs_name("Store 2");
        store2.setAddress("Address 2");
        store2.setCreated_at(LocalDate.now());

        List<PartnerStore> stores = List.of(store1, store2);

        when(partnerStoreService.getTotalPartnerStoresLastMonth()).thenReturn(stores.size());

        RestAssuredMockMvc.given()
                .when()
                .get("/api/v1/partnerstore/total/lastmonth")
                .then()
                .status(HttpStatus.OK)
                .body(equalTo(String.valueOf(stores.size())));

        verify(partnerStoreService, times(1)).getTotalPartnerStoresLastMonth();
    }

}
