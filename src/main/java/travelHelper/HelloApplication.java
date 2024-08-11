package travelHelper;


import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import travelHelper.repos.destination.DestinationRepository;
import travelHelper.repos.destination.MySqlDestinationRepository;
import travelHelper.repos.user.MySqlUserRepository;
import travelHelper.repos.user.UserRepository;
import travelHelper.services.DestinationsService;
import travelHelper.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static org.glassfish.hk2.utilities.ServiceLocatorUtilities.bind;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

            ///OBJASNJENJE ZA INJECTTT i BIND

    //    bind(MySqlSubjectRepository.class): Ovim se registruje
    //    MySqlSubjectRepository kao klasa koja implementira
    //    neki interfejs ili nasleđuje neku apstraktnu klasu.
    //
    //.to(SubjectRepository.class): Ovim se definiše da svaki put
    // kada se zatraži instanca SubjectRepository interfejsa,
    // DI kontejner treba da obezbedi instancu klase MySqlSubjectRepository.
    //
    //.in(Singleton.class): Ovim se definiše da DI kontejner
    // treba da kreira samo jednu instancu (Singleton) MySqlSubjectRepository klase,
    // i da istu instancu koristi svaki put kada se zatraži SubjectRepository.


    public HelloApplication() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE,true);

        AbstractBinder binder = new AbstractBinder() {

            @Override
            protected void configure() {
                this.bind(MySqlDestinationRepository.class)
                        .to(DestinationRepository.class)
                        .in(Singleton.class);
                this.bind(MySqlUserRepository.class)
                        .to(UserRepository.class)
                        .in(Singleton.class);

                this.bindAsContract(DestinationsService.class);
                this.bindAsContract(UserService.class);
            }
        };
        register(binder);



        packages("travelHelper");
    }
}