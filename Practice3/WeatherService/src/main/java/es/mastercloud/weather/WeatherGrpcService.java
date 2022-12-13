package es.mastercloud.weather;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



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

        logger.debug("getWeather response: "+ response.getWeather());

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
