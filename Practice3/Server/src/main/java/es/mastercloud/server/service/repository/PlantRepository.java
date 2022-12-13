package es.mastercloud.server.service.repository;

import es.mastercloud.server.models.dao.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}
