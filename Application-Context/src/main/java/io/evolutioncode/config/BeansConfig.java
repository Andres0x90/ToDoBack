package io.evolutioncode.config;

import io.evolutioncode.ports.input.AddToDoPort;
import io.evolutioncode.usecase.AddToDoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public AddToDoPort addToDoPort(){
        return new AddToDoUseCase();
    }
}
