package es.mastercloud.toposervice.services;

import es.mastercloud.toposervice.services.repository.TopographicDetailsRepository;
import es.mastercloud.toposervice.models.dao.TopographicDetails;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;


@Service
public class TopographicDetailsService {

    private final TopographicDetailsRepository topographicDetailsRepository;

    TopographicDetailsService(TopographicDetailsRepository topographicDetailsRepository) {
        this.topographicDetailsRepository = topographicDetailsRepository;
    }

    public Mono<TopographicDetails> createTopographicDetails(TopographicDetails topographicDetails){
        return topographicDetailsRepository.save(topographicDetails);
    }

    public Flux<TopographicDetails> readTopographicDetails(){
        return topographicDetailsRepository.findAll().delaySequence(Duration.ofMillis(new Random().nextInt(1000,3000)));
    }

    public Mono<TopographicDetails> readTopographicDetails(String id){
        return topographicDetailsRepository.findById(formatId(id)).delayElement(Duration.ofMillis(new Random().nextInt(1000,3000)))
                .switchIfEmpty(
                        Mono.error(new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "User with id "+ id +" not found"
                        )));
    }

    public Mono<TopographicDetails> deleteTopographicDetails(String id){
        Mono<TopographicDetails> deletedDetail = topographicDetailsRepository.findById(formatId(id))
                .switchIfEmpty(
                    Mono.error(new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "User with id "+ formatId(id) +" not found"
                    )));
        return deletedDetail.flatMap(detail -> topographicDetailsRepository.deleteById(formatId(id)).then(Mono.just(detail)));
    }

    private String formatId(String id){
        return id.substring(0,1).toUpperCase()+id.substring(1,id.length()).toLowerCase();
    }
}
