package spittr.data;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * Created by evgenypavlenko on 10/7/17.
 */
//@Entity
public class Spittle {
    //@Id
    private Long id;
    private String message;
    private LocalDateTime time;
    private Double latitude;
    private Double longitude;

    public Spittle() {}


    public Spittle(Long id, String message, LocalDateTime time) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public Spittle(Long id, String message, LocalDateTime time, Double latitude, Double longitude) {
        this.id = id;
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
