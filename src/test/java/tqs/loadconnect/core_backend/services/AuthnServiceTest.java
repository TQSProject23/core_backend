package tqs.loadconnect.core_backend.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PartnerStoreDTO;
import tqs.loadconnect.core_backend.models.PartnerStoreLoginDTO;
import tqs.loadconnect.core_backend.models.PartnerStoreRegistDTO;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

// unit tests with Mockito and Hamcrest
@ExtendWith(MockitoExtension.class)
public class AuthnServiceTest {

    private AuthnService authnService;

    @Mock
    private PartnerStoreRepository partnerStoreRepository;

    @BeforeEach
    void setUp() {
        authnService = new AuthnService(partnerStoreRepository);
    }

    @DisplayName("Register Partner Store (valid)")
    @Test
    void testRegisterPartnerStoreValid() {

        PartnerStoreRegistDTO register = new PartnerStoreRegistDTO();
        register.setPs_name("ps_name");
        register.setEmail("email@a.b");
        register.setAddress("address");
        register.setPs_password("ps_password");

        when(partnerStoreRepository.findByEmail(register.getEmail())).thenReturn(null);

        boolean result = authnService.registerPartnerStore(register);
        assertThat(result, is(true));
    }

    @DisplayName("Register Partner Store (invalid)")
    @Test
    void testRegisterPartnerStoreInvalid() {

        PartnerStoreRegistDTO register = new PartnerStoreRegistDTO();
        register.setPs_name("ps_name");
        register.setEmail("t@t.c");
        register.setAddress("address");
        register.setPs_password("ps_password");

        PartnerStore ps = new PartnerStore();
        ps.setEmail(register.getEmail());

        when(partnerStoreRepository.findByEmail(register.getEmail())).thenReturn(ps);

        boolean result = authnService.registerPartnerStore(register);
        assertThat(result, is(false));
    }

    @DisplayName("Login Partner Store (valid)")
    @Test
    void testLoginPartnerStoreValid() {

        PartnerStoreLoginDTO login = new PartnerStoreLoginDTO();
        login.setEmail("a@test.com");
        login.setPs_password("pswd");

        PartnerStore ps = new PartnerStore();
        ps.setEmail(login.getEmail());
        ps.setPassword(login.getPs_password());

        when(partnerStoreRepository.findByEmail(login.getEmail())).thenReturn(ps);

        assertThat(authnService.loginPartnerStore(login), is(notNullValue()));
    }

    @DisplayName("Login Partner Store (invalid)")
    @Test
    void testLoginPartnerStoreInvalid() {

        PartnerStoreLoginDTO login = new PartnerStoreLoginDTO();
        login.setEmail("t@t.com");
        login.setPs_password("pswd");

        when(partnerStoreRepository.findByEmail(login.getEmail())).thenReturn(null);

        assertThat(authnService.loginPartnerStore(login), is(nullValue()));
    }

}

/* PartnerStoreRegistDTO register = new PartnerStoreRegistDTO();
        register.setPs_name("ps_name");
        register.setEmail("galp@gmail.com");
        register.setAddress("address");
        register.setPs_password("ps_password");

        PartnerStore ps = new PartnerStore();
        ps.setPs_name(register.getPs_name());
        ps.setEmail(register.getEmail());
        ps.setAddress(register.getAddress());
        ps.setPassword(register.getPs_password());


        when(partnerStoreRepository.findByEmail("galp@gmail.com")).thenReturn(null);

        boolean result = authnService.registerPartnerStore(register);
        assertThat(result, is(false)); */