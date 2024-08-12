package travelHelper.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import travelHelper.resources.DestinationsResource;
import travelHelper.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
@Provider
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

            System.out.println("Ovo je kao token u Authorization: " + token);

            if (token == null || !token.startsWith("Bearer ")) {
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                System.out.println("Token nije dobar!");
                return;
            }
            token = token.replace("Bearer ", "");

            //OVde ako prodje onda je nasao nekog ko stvarno postoji!
            if(!userService.isAuthorized(token)){
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }

            //i ovde ce da proverava tipove i status tog kog je nasao!
            if(!this.authorityCheck(containerRequestContext,token)){
                containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                System.out.println("Nije prosao autority check! ili je inactive user!");
            }

        }catch (Exception e){
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext requestContext) {

        //Posle dodaj onda da ne treba ni za home page ili kako god posto tad ti ne treba login
        if(requestContext.getUriInfo().getPath().contains("login")){
            return false;
        }

        //sve ostale proveravaj!
        return true;
    }

    private boolean authorityCheck(ContainerRequestContext requestContext, String token){

        DecodedJWT jwt = JWT.decode(token);
        String type = jwt.getClaim("type").asString();
        String status = jwt.getClaim("status").asString();
        String path = requestContext.getUriInfo().getPath();

        if(status.equals("inactive")){
            return false;
        }

        List<Object> matchedResources = requestContext.getUriInfo().getMatchedResources();
        for(Object matchedResource : matchedResources){
            if(matchedResource instanceof DestinationsResource && type.equals("User") ){
                return false;
            }
        }
        return true;
    }

}
