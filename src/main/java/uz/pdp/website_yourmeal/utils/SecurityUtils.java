package uz.pdp.website_yourmeal.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.website_yourmeal.model.User;

import java.util.Random;

public class SecurityUtils {
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            User principal = (User) authentication.getPrincipal(); //authuser
            return principal.getId();
        }
        return null;
    }
}
