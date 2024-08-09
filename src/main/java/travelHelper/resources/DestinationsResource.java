package travelHelper.resources;

import travelHelper.entities.Destination;
import travelHelper.repos.destination.DestinationRepository;
import travelHelper.services.DestinationsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.awt.*;

@Path("/destinations")
public class DestinationsResource {


    @Inject
    private DestinationsService destinationsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destination> getDestinations() {
        return this.destinationsService.getAllDestinations();
    }


}
