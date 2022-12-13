package es.mastercloud.toposervice.services.repository;

import es.mastercloud.toposervice.models.dao.TopographicDetails;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TopographicDetailsRepository extends ReactiveMongoRepository<TopographicDetails, String> {

}
