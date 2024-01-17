package io.evolutioncode.config;

import io.evolutioncode.dto.mapper.Mapper;
import io.evolutioncode.ports.input.AddToDoPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.evolutioncode.usecase.AddToDoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public MapperPort mapperPort(){
        return new Mapper();
    }
    @Bean
    public AddToDoPort addToDoPort(ToDoRepositoryPort toDoRepositoryPort, MapperPort mapperPort){
        return new AddToDoUseCase(toDoRepositoryPort, mapperPort);
    }
}
