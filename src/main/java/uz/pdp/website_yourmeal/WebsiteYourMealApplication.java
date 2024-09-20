package uz.pdp.website_yourmeal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import uz.pdp.website_yourmeal.utils.SecurityUtils;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
public class WebsiteYourMealApplication {

    private final SecurityUtils securityUtils;

    public WebsiteYourMealApplication(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteYourMealApplication.class, args);
    }

    public CommandLineRunner insertQuery(DataSource dataSource) {
        return args -> {
            Resource resource = new ClassPathResource("query.sql");
            System.out.println("=================================================================");
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        };
    }
    @Bean
    public AuditorAware<String> auditorAware(){
        return ()-> Optional.ofNullable(securityUtils.getUser());
    }
}
