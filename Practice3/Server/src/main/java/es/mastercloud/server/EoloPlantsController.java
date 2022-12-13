package es.mastercloud.server;

import es.mastercloud.server.models.dto.PlantDTO;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
public class EoloPlantsController {

    @QueryMapping
    public Collection<PlantDTO> plants(){

    }
}
