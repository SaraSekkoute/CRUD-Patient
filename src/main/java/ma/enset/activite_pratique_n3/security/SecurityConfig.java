package ma.enset.activite_pratique_n3.security;

import ma.enset.activite_pratique_n3.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.rememberMe;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    PatientRepository patientRepository;



    @Bean

    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){
        String encodedPassword = passwordEncoder.encode("1234");


        return new InMemoryUserDetailsManager(

                //spring security ne pas utiser un passport encodeur
               // User.withUsername("user1").password("{noop}1234").roles("USER").build(),
                User.withUsername("user1").password(encodedPassword).roles("USER").build(),
                User.withUsername("user2").password(encodedPassword).roles("USER").build(),
                User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                //.formLogin(form -> form.loginPage("/login").permitAll())
                .formLogin(Customizer.withDefaults())
                //.rememberMe(rememberMe -> rememberMe())
                // .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret"))
               // .logout(logout -> logout.deleteCookies("JSESSIONID"))
                 .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**","/h2-console/**").permitAll())
              // soit utiliser
              //  .authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
              //  .authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
                // ou annotation @EnableMethodSecurity(prePostEnabled = true) et @PreAuthorize("hasRole('Role_ADMIN')") en cotrolleur
                .exceptionHandling(ar->ar.accessDeniedPage("/notAuthorized"))
                 //interdire Tous les applications()Tous les requetes necessitent une authentification (sauf admin ,user et vous pouvez avoir boostrap et h2 console)
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .build();
    }

}
