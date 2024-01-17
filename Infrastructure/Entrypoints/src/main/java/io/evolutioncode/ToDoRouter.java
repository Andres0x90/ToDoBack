package io.evolutioncode;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.AddToDoPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Configuration
public class ToDoRouter {

    @Autowired
    private AddToDoPort addToDoPort;

    @Autowired
    private ToDoRepositoryPort toDoRepositoryPort;

    @Autowired
    private MapperPort mapperPort;

    @Bean
    public RouterFunction<ServerResponse> addToDo(){
        return RouterFunctions.route().POST("/todo", request ->
                        request.bodyToMono(ToDoDTO.class)
                                .flatMap(toDoDTO -> Mono.from(addToDoPort.execute(toDoDTO).toFlowable()))
                                .map(mapperPort::mapToResponse)
                                .flatMap(toDoResponse -> ServerResponse.created(URI.create("/todo/".concat(toDoResponse.getId().toString())))
                                        .bodyValue(toDoResponse)))
                .build();
    }


    @Bean
    public RouterFunction<ServerResponse> listAllToDo(){
        return RouterFunctions.route().GET("/todos", request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(toDoRepositoryPort.listAll(), ToDo.class)))
                .build();
    }
}
