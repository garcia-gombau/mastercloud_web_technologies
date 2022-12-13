package es.mastercloud.server.service;

import es.mastercloud.server.models.dao.EoloPlant;
import es.mastercloud.server.models.dto.TopographicDetailsDTO;
import es.mastercloud.server.service.repository.PlantRepository;
import es.mastercloud.server.utils.PlantStringBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class PlantService {

    private final WeatherClient weatherClient;
    private final TopoClient topoClient;
    private final PlantRepository plants;

    public PlantService(WeatherClient weatherClient, TopoClient topoClient, PlantRepository plants) {
        this.weatherClient = weatherClient;
        this.topoClient = topoClient;
        this.plants = plants;
    }

    public EoloPlant createPlant(String city) throws InterruptedException, ExecutionException {
        PlantStringBuilder plantStringBuilder = new PlantStringBuilder().setCity(city);
        Future<String> weather = weatherClient.getCityWeather(city);
        Future<TopographicDetailsDTO> landscape = topoClient.getTopography(city);
        while (true){
            if (plantStringBuilder.ready()){
                try {
                    Thread.sleep(new Random().nextLong(1000,3000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return plants.save(new EoloPlant(city, plantStringBuilder.build()));
            }
            if (weather.isDone()){
                plantStringBuilder = plantStringBuilder.setWeather(weather.get());
            }
            if (landscape.isDone()){
                plantStringBuilder = plantStringBuilder.setLandscape(landscape.get().landscape());
            }

        }
    }

    public List<EoloPlant> readPlants() {
        return plants.findAll();
    }

    public EoloPlant deleteEoloPlants(Long id) {
        Optional<EoloPlant> deleted = plants.findById(id);
        plants.deleteById(deleted.orElseThrow().getId());
        return deleted.get();
    }
}
