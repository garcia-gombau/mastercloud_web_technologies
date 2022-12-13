package es.mastercloud.server.service;

import es.mastercloud.server.models.dto.TopographicDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.Future;

@Service
public class TopoClient {
    @Value("${toposervice.endpoint.url}")
    private String url;

    @Async
    public Future<TopographicDetailsDTO> getTopography(String city){
        RestTemplate restTemplate = new RestTemplate();
        return new AsyncResult<>(restTemplate.getForObject(url + "/" + city, TopographicDetailsDTO.class));
    }
}