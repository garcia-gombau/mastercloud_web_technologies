package es.mastercloud.server.service;

import es.mastercloud.server.models.dto.PlantDTO;
import es.mastercloud.server.service.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PlantService {

    private final PlantRepository plans;

    public PlantService(PlantRepository plans) {
        this.plans = plans;
    }

    public Collection<PlantDTO> createPlant(String city){
        return null;
    }

    public Collection<> get(){

    }

}
