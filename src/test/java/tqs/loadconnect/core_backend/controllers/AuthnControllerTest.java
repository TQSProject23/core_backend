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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tqs.loadconnect.core_backend.models.*;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import tqs.loadconnect.core_backend.services.AuthnService;



@WebMvcTest(AuthnController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthnControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthnService authnService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @DisplayName("Register PartnerStore - Success")
    @Test
    void registerPartnerStoreSuccess() throws Exception {
        PartnerStoreRegistDTO partnerStoreRegistDTO = new PartnerStoreRegistDTO("Teste","teste1@gmail.com","123456","Rua do teste");

        when(authnService.registerPartnerStore(any(PartnerStoreRegistDTO.class))).thenReturn(true);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(partnerStoreRegistDTO)
                .when()
                .post("/authn/register")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo("true"));

        verify(authnService, times(1)).registerPartnerStore(any(PartnerStoreRegistDTO.class));

    }

    @DisplayName("Register PartnerStore - Fail")
    @Test
    void registerPartnerStoreFailure() {
        // Prepare the request body
        PartnerStoreRegistDTO registerDTO = new PartnerStoreRegistDTO();


        // Mock the service method
        when(authnService.registerPartnerStore(any(PartnerStoreRegistDTO.class))).thenReturn(false);

        // Perform the POST request
        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(registerDTO)
                .when()
                .post("/authn/register")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(equalTo("false"));

        // Verify the service method is called
        verify(authnService, times(1)).registerPartnerStore(any(PartnerStoreRegistDTO.class));
    }

    @DisplayName("Login PartnerStore - Success")
    @Test
    void loginPartnerStoreSuccess() {
        // Prepare the request body
        PartnerStoreLoginDTO loginDTO = new PartnerStoreLoginDTO("hello@gmail.com","123456");

        PartnerStoreDTO partnerStoreDTO = new PartnerStoreDTO(0,"Teste","hello@gmail.com","Rua do teste");


        // Mock the service method
        when(authnService.loginPartnerStore(any(PartnerStoreLoginDTO.class))).thenReturn(partnerStoreDTO);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDTO)
                .when()
                .post("/authn/login")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ps_name", equalTo("Teste"))
                .body("email", equalTo("hello@gmail.com"))
                .body("address", equalTo("Rua do teste"));

        verify(authnService, times(1)).loginPartnerStore(any(PartnerStoreLoginDTO.class));

    }

    @DisplayName("Login PartnerStore - Fail")
    @Test
    void loginPartnerStoreFailure() {
        // Prepare the request body
        PartnerStoreLoginDTO loginDTO = new PartnerStoreLoginDTO();

        // Mock the service method
        when(authnService.loginPartnerStore(any(PartnerStoreLoginDTO.class))).thenReturn(null);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(loginDTO)
                .when()
                .post("/authn/login")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(is(emptyOrNullString()));

        verify(authnService, times(1)).loginPartnerStore(any(PartnerStoreLoginDTO.class));
    }

}
