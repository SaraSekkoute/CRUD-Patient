package ma.enset.activite_pratique_n3;

import ma.enset.activite_pratique_n3.entities.Patient;
import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class ActivitePratiqueN3Application implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(ActivitePratiqueN3Application.class, args);
    }

    //  @Override

    public void run(String... args) throws Exception {
        /*Patient p1=new Patient();
        p1.setId(null);
        p1.setNom("aya");
        p1.setDateN(new Date());
        p1.setMalade(false);
        p1.setScore(120);

        patientRepository.save(p1);*/


    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
