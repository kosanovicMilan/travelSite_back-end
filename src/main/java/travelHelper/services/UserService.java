package travelHelper.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import org.jvnet.hk2.annotations.Service;
import travelHelper.entities.User;
import travelHelper.repos.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public String logIn(String email, String password) {

        String hashedPassword = DigestUtils.sha256Hex(password);
        System.out.println("hashedPassword: " + hashedPassword);

        User user = this.userRepository.findByEmail(email);

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

    public List<User> allUsers(){
        return this.userRepository.getAllUsers();
    }

    public User getOneUser(int id){
        return this.userRepository.getOneUser(id);
    }

    public User addUser(User user){
        return this.addUser(user);
    }

    public void deleteUser(int id){
        this.userRepository.deleteUser(id);
    }


    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);


        String email = jwt.getSubject();
        String type = jwt.getClaim("type").asString();
        String status = jwt.getClaim("status").asString();


        User user = this.userRepository.findByEmail(email);


        if(user == null){
            return false;
        }

        return true;
    }

}
