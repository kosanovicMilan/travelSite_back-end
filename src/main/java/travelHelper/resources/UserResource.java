package travelHelper.resources;

import travelHelper.entities.User;
import travelHelper.request.LogInRequest;
import travelHelper.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LogInRequest logInRequest) {
        Map<String, String> response = new HashMap<>();

        System.out.println("Ovo je direkt stiglo: " +logInRequest.getEmail() + " " + logInRequest.getPassword());

        String jwt = this.userService.logIn(logInRequest.getEmail(), logInRequest.getPassword());
        if (jwt == null) {
            response.put("message", "We don't have a valid credentials");
            return Response.status(422,"Unprocessable Entity").entity(response).build();
        }
        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> allUsers(){
        return this.userService.allUsers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User oneUser(@PathParam("id") int id){
        return this.userService.getOneUser(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(@Valid User user){
        return this.userService.addUser(user);
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") int id){
        this.userService.deleteUser(id);
    }
}
