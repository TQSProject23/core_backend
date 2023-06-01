package tqs.loadconnect.core_backend.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartnerStoreLoginDTOTest {

    @Test
    @DisplayName("Test equals() method")
    void testEquals() {
        PartnerStoreLoginDTO dto1 = new PartnerStoreLoginDTO("store1@example.com", "password");
        PartnerStoreLoginDTO dto2 = new PartnerStoreLoginDTO("store1@example.com", "password");

        assertTrue(dto1.equals(dto2));
    }

    @Test
    @DisplayName("Test hashCode() method")
    void testHashCode() {
        PartnerStoreLoginDTO dto1 = new PartnerStoreLoginDTO("store1@example.com", "password");
        PartnerStoreLoginDTO dto2 = new PartnerStoreLoginDTO("store1@example.com", "password");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Test toString() method")
    void testToString() {
        PartnerStoreLoginDTO dto = new PartnerStoreLoginDTO("store1@example.com", "password");

        String expectedString = "PartnerStoreLoginDTO(email=store1@example.com, ps_password=password)";
        assertEquals(expectedString, dto.toString());
    }

    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        PartnerStoreLoginDTO dto = new PartnerStoreLoginDTO();
        dto.setEmail("store1@example.com");
        dto.setPs_password("password");

        assertEquals("store1@example.com", dto.getEmail());
        assertEquals("password", dto.getPs_password());
    }
}
