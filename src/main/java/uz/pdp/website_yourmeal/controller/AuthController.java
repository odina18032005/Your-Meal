package uz.pdp.website_yourmeal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.website_yourmeal.dto.AuthenticationDto;
import uz.pdp.website_yourmeal.dto.UserDto;
import uz.pdp.website_yourmeal.model.User;
import uz.pdp.website_yourmeal.repository.UserRepository;
import uz.pdp.website_yourmeal.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/signup")
    public User signup(@RequestBody UserDto userDto){
        String encode = passwordEncoder.encode(userDto.password());
        User build = User.builder().firstName(userDto.firstName()).lastName(userDto.lastName()).phone(userDto.phone()).password(encode).build();
        return userRepository.save(build);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationDto> authentication(@RequestParam("phone")String phone, @RequestParam("password")String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phone,password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtUtil.generateToken(phone);
        return ResponseEntity.ok(new AuthenticationDto(accessToken));
    }
}
