package travelHelper.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
public class Destination {

    private int destination_id;

    @NotNull(message = "Destination name is required!")
    @NotEmpty(message = "Destination name is required!")
    private String name;

    @NotNull(message = "Destination country is required!")
    @NotEmpty(message = "Destination country is required!")
    private String country;

    @NotNull(message = "Destination description is required!")
    @NotEmpty(message = "Destination description is required!")
    private String description;

    public Destination() {
    }

    public Destination(int destination_id, String name, String country, String description) {
        this.destination_id = destination_id;
        this.name = name;
        this.country = country;
        this.description = description;
    }
}
