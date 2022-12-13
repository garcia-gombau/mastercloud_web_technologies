package es.mastercloud.server.service;

import es.mastercloud.server.models.dto.TopographicDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.Future;

@Service
public class TopoClient {
    @Value("${toposervice.endpoint.url}")
    private String url;

    @Async
    public Future<String> getTopography(String city){
        RestTemplate restTemplate = new RestTemplate();
        return new AsyncResult<>(Objects.requireNonNull(
                restTemplate.getForObject(url + "/" + city, TopographicDetailsDTO.class)).landscape()
        );
    }
}