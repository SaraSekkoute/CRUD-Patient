package ma.enset.activite_pratique_n3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/notAuthorized")
    public  String  notAuthorized()
    {
        return "notAuthorized";
    }

}
