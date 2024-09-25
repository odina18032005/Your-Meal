package uz.pdp.website_yourmeal.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import uz.pdp.website_yourmeal.dto.ErrorBodyDto;

import java.io.PrintWriter;
import java.time.LocalDateTime;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    private final String[] WHITE_LIST = new String[]{
            "/auth/**",
            "/error",
//            "/product/**",
            "/api-docs",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };

    private final ObjectMapper objectMapper;
    private final UserDetailsService userDetailsService;


    private final AccessDeniedHandler accessDeniedHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Lazy
    public SecurityConfig(ObjectMapper objectMapper, @Qualifier("customUserDetailsService") UserDetailsService userDetailsService, AccessDeniedHandler accessDeniedHandler, AuthenticationEntryPoint authenticationEntryPoint, JwtRequestFilter jwtRequestFilter) {
        this.objectMapper = objectMapper;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((auth)->{
                    auth
                            .requestMatchers(WHITE_LIST).permitAll()
                            .requestMatchers("/**").authenticated();
                }
        );
        http.userDetailsService(userDetailsService);
        http.exceptionHandling((handler)->{
            handler.accessDeniedHandler(accessDeniedHandler);
            handler.authenticationEntryPoint(authenticationEntryPoint);
        });
        http.logout( logout -> logout
                .logoutUrl("/auth/logout")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout","POST"))
                .permitAll()
        );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }



    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return  (request, response, exception) -> {
            ErrorBodyDto bodyDto = new ErrorBodyDto(
                    HttpStatus.FORBIDDEN.value(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    exception.getClass().toString(),
                    exception.getMessage(),
                    LocalDateTime.now());
            response.setStatus(403);
            PrintWriter writer = response.getWriter();
            ObjectWriter objectWriter = objectMapper.writer();
            objectWriter.writeValue(writer,bodyDto);

        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return (request, response, exception) -> {
            ErrorBodyDto bodyDto = new ErrorBodyDto(
                    HttpStatus.UNAUTHORIZED.value(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    exception.getClass().toString(),
                    exception.getMessage(),
                    LocalDateTime.now());
            response.setStatus(401);
            PrintWriter writer = response.getWriter();
            ObjectWriter objectWriter = objectMapper.writer();
            objectWriter.writeValue(writer,bodyDto);

        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Value("${aws.access.key}")
    private String ACCESS_KEY;
    @Value("${aws.secret.key}")
    private String SECRET_KEY;

    @Bean
    public AmazonS3 amazonS3(){
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
        return AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
