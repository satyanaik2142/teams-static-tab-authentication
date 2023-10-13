package edu.srn.db.repo;

import edu.srn.db.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("Select a from Tenant a where a.tenantId = ?1")
    Tenant findByTenantId(String tenantId);
}
