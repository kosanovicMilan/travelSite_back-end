package travelHelper.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import travelHelper.resources.DestinationsResource;
import travelHelper.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Auth implements ContainerRequestFilter {

    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if(!this.isAuthRequired(containerRequestContext)){
            return;
        }
        try{

            String token = containerRequestContext.getHeaderString("Authorization");
            if (token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }



            if(!userService.isAuthorized(token)){
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

                return;
            }


            DecodedJWT jwt = JWT.decode(token);
            String type = jwt.getClaim("type").asString();
            String status = jwt.getClaim("status").asString();
            String path = containerRequestContext.getUriInfo().getPath();

            if(!this.userService.routeAccess(type,path)){
                containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());

            }

        }catch (Exception e){
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext) {

        //Posle dodaj onda da ne treba ni za home page ili kako god posto tad ti ne treba login
        if(requestContext.getUriInfo().getPath().contains("login") ){
            return false;
        }

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        for(Object matchedResource : matchedResources){
            if(matchedResource instanceof DestinationsResource){
                return true;
            }
        }
        return true;
    }

}
