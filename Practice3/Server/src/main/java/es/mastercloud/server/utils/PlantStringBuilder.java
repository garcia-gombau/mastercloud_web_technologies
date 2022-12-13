package es.mastercloud.server.utils;

public class PlantStringBuilder {
     private String city = "";
     private String weather = "";
     private String landscape = "";

     public PlantStringBuilder setCity(String city) {
         this.city = city;
         return this;
     }

    public PlantStringBuilder setWeather(String weather) {
        this.weather = weather;
        return this;
    }
    public PlantStringBuilder setLandscape(String landscape) {
        this.landscape = landscape;
        return this;
    }

    public String build() {
        String built = this.city +"-"+ this.weather +"-"+ this.landscape;
        return built.charAt(0)<='m'?built.toLowerCase():built.toUpperCase();
    }

}
