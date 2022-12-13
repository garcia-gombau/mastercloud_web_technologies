package es.mastercloud.server;

import es.mastercloud.server.models.dao.EoloPlant;
import es.mastercloud.server.service.PlantService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
public class EoloPlantsController {
    private PlantService plantService;

    public EoloPlantsController(PlantService plantService) {
        this.plantService = plantService;
    }

    @MutationMapping
    public EoloPlant createEoloPlant(@Argument EoloPlant eoloPlant) throws ExecutionException, InterruptedException {
        return plantService.createPlant(eoloPlant.getCity());
    }

    @MutationMapping
    public EoloPlant deleteEoloPlant(@Argument Long id) throws ExecutionException, InterruptedException {
        return plantService.deleteEoloPlants(id);
    }

    @QueryMapping
    public List<EoloPlant> eoloPlants(){
        return plantService.readPlants();
    }
}
