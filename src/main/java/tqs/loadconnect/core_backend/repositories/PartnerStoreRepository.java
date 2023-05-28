package tqs.loadconnect.core_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.loadconnect.core_backend.models.PartnerStore;

@Repository
public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long> {
}
