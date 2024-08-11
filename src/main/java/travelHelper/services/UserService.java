package travelHelper.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import travelHelper.entities.User;
import travelHelper.repos.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String logIn(String email, String password) {

        String hashedPassword = DigestUtils.sha256Hex(password);
        System.out.println("hashedPassword: " + hashedPassword);

        User user = userRepository.findByEmail(email);

        System.out.println("Ovo je userov pass: " + user.getPassword());
        if(user == null || !user.getPassword().equals(hashedPassword)) {
            System.out.println("kaze da je null ili da nisu iste sifre!");
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);

        Algorithm algorithm = Algorithm.HMAC256("secret");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("type",user.getType())
                .withClaim("status",user.getStatus())
                .sign(algorithm);

    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);


        String email = jwt.getSubject();
        String type = jwt.getClaim("type").asString();
        String status = jwt.getClaim("status").asString();


        User user = userRepository.findByEmail(email);


        if(user == null || !status.equals("active")){
            return false;
        }

        return true;
    }

    public boolean routeAccess(String type, String route){

        if(type.equals("admin")) {
            return true; //admin ima prava za sve!
        }else if (type.equals("user") && route.contains("editUsers")){
            return false;
        }else if(type.equals("user")){
            return true;
        }

        return false;
    }

}
