package es.mastercloud.weather;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


@GrpcService
public class WeatherGrpcService extends WeatherServiceGrpc.WeatherServiceImplBase {
    private final Logger logger = LoggerFactory.getLogger(WeatherGrpcService.class);
    private static final String VOWELS = "aeiou";
    @Override
    public void getWeather(GetWeatherRequest request, StreamObserver<Weather> responseObserver) {
        logger.debug("getWeather request: "+ request.getCity());

        Weather response = Weather.newBuilder().setCity(request.getCity()).setWeather(
                VOWELS.contains(request.getCity().toLowerCase().substring(0,1))?"Rainy":"Sunny"
        ).build();
        try {
            Thread.sleep(new Random().nextLong(1000,3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        responseObserver.onNext(response);


        logger.debug("getWeather response: "+ response.getWeather());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
