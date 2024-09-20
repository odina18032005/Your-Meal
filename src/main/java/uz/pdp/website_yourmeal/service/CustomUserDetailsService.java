package uz.pdp.website_yourmeal.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.website_yourmeal.model.User;
import uz.pdp.website_yourmeal.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findFirstByPhone(phone)
                .orElseThrow(() -> new BadCredentialsException("Phone or password incorrect"));
        UserDetails build = org.springframework.security.core.userdetails.User.withUsername(phone)
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
        return build;
    }
}
