package travelHelper.resources;

import travelHelper.entities.Destination;
import travelHelper.repos.destination.DestinationRepository;
import travelHelper.services.DestinationsService;

import javax.inject.Inject;
import javax.validation.Valid;
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination getOneDestination(@PathParam("id") int id) {
        return this.destinationsService.getOneDestination(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Destination addDestination(@Valid Destination destination) {
        return this.destinationsService.addDestination(destination);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDestination(@PathParam("id") int id){
        this.destinationsService.deleteDestination(id);
    }

}
