package es.mastercloud.server.service;

import es.mastercloud.server.models.dao.Plant;
import es.mastercloud.server.models.dto.PlantDTO;
import es.mastercloud.server.service.repository.PlantRepository;
import es.mastercloud.server.utils.PlantStringBuilder;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    public PlantDTO createPlant(String city) throws InterruptedException, ExecutionException {
        PlantStringBuilder plantStringBuilder = new PlantStringBuilder().setCity(city);
        Future<String> weather = weatherClient.getCityWeather(city);
        Future<String> landscape = topoClient.getTopography(city);
        while(true){
            if (weather.isDone()){
                plantStringBuilder = plantStringBuilder.setWeather(weather.get());
            }
            if (landscape.isDone()){
                plantStringBuilder = plantStringBuilder.setLandscape(landscape.get());
            }
            if (weather.isDone() && landscape.isDone()){
                return plants.save(new Plant(city, plantStringBuilder.build())).toDTO();
            }
        }
    }

}
