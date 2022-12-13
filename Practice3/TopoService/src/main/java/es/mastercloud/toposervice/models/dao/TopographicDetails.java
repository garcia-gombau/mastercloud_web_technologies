package es.mastercloud.toposervice.models.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("topographicdetails")
public class TopographicDetails {
    @Id
    private String id;

    private String  landscape;

    public TopographicDetails() {
    }

    public TopographicDetails(String id, String landscape) {
        this.id = id;
        this.landscape = landscape;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandscape() {
        return landscape;
    }

    public void setLandscape(String landscape) {
        this.landscape = landscape;
    }
}
