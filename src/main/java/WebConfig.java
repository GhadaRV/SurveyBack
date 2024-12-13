
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allows all endpoints
                .allowedOrigins("http://localhost:8082") // URL of your frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Methods you want to allow
                .allowedHeaders("*")
                .allowCredentials(true);// Allow any headers
    }
}
