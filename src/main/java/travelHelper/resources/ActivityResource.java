package travelHelper.resources;

import travelHelper.entities.Activity;
import travelHelper.services.ActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/activities")
public class ActivityResource {


    @Inject
    private ActivityService activityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getActivities() {
        return this.activityService.getActivities();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Activity oneActivity(@PathParam("id") int id) {
        return this.activityService.getActivity(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Activity addActivity(Activity activity) {
        return this.activityService.addActivity(activity);
    }

    @DELETE
    @Path("/{id}")
    public void deleteActivity(@PathParam("id") int id) {
        this.activityService.deleteActivity(id);
    }

}
