package es.mastercloud.toposervice.utils;

import es.mastercloud.toposervice.models.dao.TopographicDetails;
import es.mastercloud.toposervice.services.repository.TopographicDetailsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DataInitializer {
    private final TopographicDetailsRepository topographicDetailsRepository;

    public DataInitializer(TopographicDetailsRepository topographicDetailsRepository) {
        this.topographicDetailsRepository = topographicDetailsRepository;
    }

    @PostConstruct
    public void init(){
        Flux<TopographicDetails> details = Flux.just(
                new TopographicDetails("Madrid", "mountain"),
                new TopographicDetails("Barcelona", "flat"),
                new TopographicDetails("Jaca", "mountain"),
                new TopographicDetails("Andorra", "mountain"),
                new TopographicDetails("Valencia", "flat"),
                new TopographicDetails("Sevilla", "flat"),
                new TopographicDetails("Malaga", "flat"),
                new TopographicDetails("Murcia", "flat"),
                new TopographicDetails("Palma", "mountain"),
                new TopographicDetails("Bilbao", "flat"),
                new TopographicDetails("Alicante", "flat"),
                new TopographicDetails("Cordoba", "flat"),
                new TopographicDetails("Valladolid", "mountain"),
                new TopographicDetails("Vigo", "flat"),
                new TopographicDetails("Gijon", "flat"),
                new TopographicDetails("Vitoria", "mountain")
        );
        details.flatMap(this.topographicDetailsRepository::save).blockLast();
    }
}
