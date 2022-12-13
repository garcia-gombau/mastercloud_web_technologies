package es.mastercloud.server.service;

import es.mastercloud.weather.GetWeatherRequest;
import es.mastercloud.weather.WeatherServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;


@Service
public class WeatherClient {

    @Async
    public Future<String> getCityWeather(String city) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .usePlaintext()
                .build();

        WeatherServiceGrpc.WeatherServiceBlockingStub client =
                WeatherServiceGrpc.newBlockingStub(channel);

        GetWeatherRequest request = GetWeatherRequest.newBuilder()
                .setCity(city).build();

        return new AsyncResult<>(client.getWeather(request).getWeather());
    }

}
