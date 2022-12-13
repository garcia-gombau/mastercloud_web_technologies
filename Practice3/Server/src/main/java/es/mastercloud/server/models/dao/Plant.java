package es.mastercloud.server.models.dao;

import es.mastercloud.server.models.dto.PlantDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String city;
    String planning;

    public Plant() {}

    public Plant(String city, String planning) {
        this.city = city;
        this.planning = planning;
    }

    public Plant(long id, String city, String planning) {
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

    public PlantDTO toDTO(){
        return new PlantDTO(this.id, this.city, this.planning);
    }
}
