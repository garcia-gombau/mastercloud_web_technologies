package es.mastercloud.toposervice.controller;

import es.mastercloud.toposervice.models.dao.TopographicDetails;
import es.mastercloud.toposervice.models.dto.TopographicDetailsDTO;
import es.mastercloud.toposervice.services.TopographicDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/topographicdetails")
public class TopographicDetailsController {
    private final TopographicDetailsService detailsService;

    public TopographicDetailsController(TopographicDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TopographicDetailsDTO> createTopographicDetailsDTO(@RequestBody Mono<TopographicDetailsDTO> detailsDto){
        return detailsDto.map(this::toDetails).flatMap(detailsService::createTopographicDetails).map(this::toDTO);
    }

    @GetMapping("/")
    public Flux<TopographicDetailsDTO> readTopographicDetailsDTO(){
        return detailsService.readTopographicDetails().map(this::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<TopographicDetailsDTO> readTopographicDetailsDTO(@PathVariable String id){
        return detailsService.readTopographicDetails(id).map(this::toDTO);
    }

    @DeleteMapping ("/{id}")
    public Mono<TopographicDetailsDTO> deleteTopographicDetailsDTO(@PathVariable String id){
        return detailsService.deleteTopographicDetails(id).map(this::toDTO);
    }

    private TopographicDetails toDetails(TopographicDetailsDTO dto){
        return new TopographicDetails(dto.id(), dto.landscape());
    }

    private TopographicDetailsDTO toDTO(TopographicDetails details){
        return new TopographicDetailsDTO(details.getId(), details.getLandscape());
    }
}
