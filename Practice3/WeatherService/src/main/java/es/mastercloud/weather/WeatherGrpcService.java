package es.mastercloud.weather;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

@GrpcService
public class WeatherGrpcService extends WeatherServiceGrpc.WeatherServiceImplBase {
    private static final String VOWELS = "aeiou";

    @Override
    public void getWeather(GetWeatherRequest request, StreamObserver<Weather> responseObserver) {
        responseObserver.onNext(Weather.newBuilder().setCity(request.getCity()).setWeather(
                VOWELS.contains(request.getCity().toLowerCase().substring(0,1))?"Rainy":"Sunny"
        ).build());
        responseObserver.onCompleted();
    }
}
