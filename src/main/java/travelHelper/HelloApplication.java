package travelHelper;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    ///OBJASNJENJE ZA INJECTTT i BIND
//    bind(MySqlSubjectRepository.class): Ovim se registruje MySqlSubjectRepository kao klasa koja implementira neki interfejs ili nasleđuje neku apstraktnu klasu.
//
//.to(SubjectRepository.class): Ovim se definiše da svaki put kada se zatraži instanca SubjectRepository interfejsa, DI kontejner treba da obezbedi instancu klase MySqlSubjectRepository.
//
//.in(Singleton.class): Ovim se definiše da DI kontejner treba da kreira samo jednu instancu (Singleton) MySqlSubjectRepository klase, i da istu instancu koristi svaki put kada se zatraži SubjectRepository.


}