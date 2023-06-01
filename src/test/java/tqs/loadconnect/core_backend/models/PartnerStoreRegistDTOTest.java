package tqs.loadconnect.core_backend.models;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PartnerStoreRegistDTOTest
{

    @Test
    @DisplayName("Test equals() method")
    void testEquals() {
        PartnerStoreRegistDTO dto1 = new PartnerStoreRegistDTO("Store1", "store1@example.com", "password", "Address1");
        PartnerStoreRegistDTO dto2 = new PartnerStoreRegistDTO("Store1", "store1@example.com", "password", "Address1");

        assertTrue(dto1.equals(dto2));
    }

    @Test
    @DisplayName("Test hashCode() method")
    void testHashCode() {
        PartnerStoreRegistDTO dto1 = new PartnerStoreRegistDTO("Store1", "store1@example.com", "password", "Address1");
        PartnerStoreRegistDTO dto2 = new PartnerStoreRegistDTO("Store1", "store1@example.com", "password", "Address1");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Test toString() method")
    void testToString() {
        PartnerStoreRegistDTO dto = new PartnerStoreRegistDTO("Store1", "store1@example.com", "password", "Address1");

        String expectedString = "PartnerStoreRegistDTO(ps_name=Store1, email=store1@example.com, ps_password=password, address=Address1)";
        assertEquals(expectedString, dto.toString());
    }

    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        PartnerStoreRegistDTO dto = new PartnerStoreRegistDTO();
        dto.setPs_name("Store1");
        dto.setEmail("store1@example.com");
        dto.setPs_password("password");
        dto.setAddress("Address1");

        assertEquals("Store1", dto.getPs_name());
        assertEquals("store1@example.com", dto.getEmail());
        assertEquals("password", dto.getPs_password());
        assertEquals("Address1", dto.getAddress());
    }
}