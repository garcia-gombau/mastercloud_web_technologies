package es.mastercloud.server.service.repository;

import es.mastercloud.server.models.dao.EoloPlant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<EoloPlant, Long> {
}
