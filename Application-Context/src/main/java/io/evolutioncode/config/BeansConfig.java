package io.evolutioncode.config;

import io.evolutioncode.dto.mapper.Mapper;
import io.evolutioncode.ports.input.*;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.evolutioncode.usecase.*;
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

    @Bean
    public ListAllToDoPort listAllToDoPort(ToDoRepositoryPort toDoRepositoryPort){
        return new ListAllToDoUseCase(toDoRepositoryPort);
    }

    @Bean
    public ListByIdPort listByIdPort(ToDoRepositoryPort toDoRepositoryPort){
        return new ListByIdUseCase(toDoRepositoryPort);
    }

    @Bean
    public UpdateToDoPort updateToDoPort(ToDoRepositoryPort toDoRepositoryPort, MapperPort mapperPort, ListByIdPort listByIdPort){
        return new UpdateToDoUseCase(toDoRepositoryPort, mapperPort, listByIdPort);
    }

    @Bean
    public DeleteToDoPort deleteToDoPort(ToDoRepositoryPort toDoRepositoryPort, ListByIdPort listByIdPort){
        return new DeleteToDoUseCase(toDoRepositoryPort, listByIdPort);
    }
}
