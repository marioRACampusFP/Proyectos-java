package es.evento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigFiesta {

    @Bean
    public Local local() {
        return new Local();
    }

    @Bean
    public Fiesta fiesta() {
        Fiesta f = new Fiesta();
        f.setLocal(local()); // Inyección de dependencias
        return f;
    }
}