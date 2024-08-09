package travelHelper.entities;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;

@Getter
@Setter
public class Activity {

    private Integer activity_id;

    @NotNull(message = "Activity name is required!")
    @NotEmpty(message = "Activity name is required")
    private String name;

    private int destination_id;

    @NotNull(message = "destinationName is required!")
    @NotEmpty(message = "destinationName is required")
    private String destinationName;

    public Activity() {
    }

    public Activity(int activity_id, String name, int destination_id, String destinationName) {
        this.activity_id = activity_id;
        this.name = name;
        this.destination_id = destination_id;
        this.destinationName = destinationName;
    }
}
