package es.mastercloud.server.models.dto;

import es.mastercloud.server.models.dao.Plant;

public class PlantDTO {
    private long id;

    String city;
    String planning;

    public PlantDTO() {}

    public PlantDTO(long id, String city, String planning) {
        this.id = id;
        this.city = city;
        this.planning = planning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlanning() {
        return planning;
    }

    public void setPlanning(String planning) {
        this.planning = planning;
    }

    public Plant toDao(){
        new Plant(this.id, this.city, this.planning);
    }
}
