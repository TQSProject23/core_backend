package tqs.loadconnect.core_backend.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PartnerStoreDTO Tests")
public class PartnerStoreDTOTest {

    @Test
    @DisplayName("Test equals() method")
    void testEquals() {
        PartnerStoreDTO dto1 = new PartnerStoreDTO(1, "Store 1", "store1@example.com", "Address 1");
        PartnerStoreDTO dto2 = new PartnerStoreDTO(1, "Store 1", "store1@example.com", "Address 1");

        assertTrue(dto1.equals(dto2));
    }

    @Test
    @DisplayName("Test hashCode() method")
    void testHashCode() {
        PartnerStoreDTO dto1 = new PartnerStoreDTO(1, "Store 1", "store1@example.com", "Address 1");
        PartnerStoreDTO dto2 = new PartnerStoreDTO(1, "Store 1", "store1@example.com", "Address 1");

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Test toString() method")
    void testToString() {
        PartnerStoreDTO dto = new PartnerStoreDTO(1, "Store 1", "store1@example.com", "Address 1");

        String expectedString = "PartnerStoreDTO(id=1, ps_name=Store 1, email=store1@example.com, address=Address 1)";
        assertEquals(expectedString, dto.toString());
    }

    @Test
    @DisplayName("Test @NoArgsConstructor")
    void testNoArgsConstructor() {
        PartnerStoreDTO dto = new PartnerStoreDTO();

        assertNull(dto.getPs_name());
        assertNull(dto.getEmail());
        assertNull(dto.getAddress());
    }

    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        PartnerStoreDTO dto = new PartnerStoreDTO();
        dto.setId(1);
        dto.setPs_name("Store 1");
        dto.setEmail("store1@example.com");
        dto.setAddress("Address 1");

        assertEquals(1, dto.getId());
        assertEquals("Store 1", dto.getPs_name());
        assertEquals("store1@example.com", dto.getEmail());
        assertEquals("Address 1", dto.getAddress());
    }
}
