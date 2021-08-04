package jul.MightyPineapple.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.Collections;

@RestController
public class HomeController {
    @GetMapping("/check")
    public String home(@AuthenticationPrincipal OidcUser user, Model model) {
        if(user != null){
            //model.addAttribute(user.getEmail());
            return "Welcome, "+ user.getFullName() + "!"
                    + "\n" + "Your email is " + user.getEmail();
        }
       return "Data might be null!";
    }


    @GetMapping("/")
    @PreAuthorize("hasAuthority('SCOPE_profile')")
    public ModelAndView userDetails(OAuth2AuthenticationToken authentication) {
        return new ModelAndView("userProfile" , Collections.singletonMap("details", authentication.getPrincipal().getAttributes()));
    }
}
