package io.evolutioncode;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.dto.ToDoResponse;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.*;
import io.reactivex.rxjava3.core.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ToDoRouter {

    @Autowired
    private AddToDoPort addToDoPort;

    @Autowired
    private ListAllToDoPort listAllToDoPort;

    @Autowired
    private ListByIdPort listByIdPort;

    @Autowired
    private UpdateToDoPort updateToDoPort;

    @Autowired
    private DeleteToDoPort deleteToDoPort;

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
                        .body(BodyInserters.fromPublisher(listAllToDoPort.execute()
                                .map(mapperPort::mapToResponse), ToDoResponse.class)))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> listByIdToDo(){
        return RouterFunctions.route().GET("/todo/{id}", request -> ServerResponse.ok()
                        .body(BodyInserters.fromPublisher(listByIdPort.execute(Integer.parseInt(request.pathVariable("id")))
                                .map(mapperPort::mapToResponse).toFlowable(), ToDoResponse.class)))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> updateToDo(){
        return RouterFunctions.route().PUT("/todo/{id}", request ->
                        request.bodyToMono(ToDoDTO.class)
                                .flatMap(toDoDTO -> Mono.from(updateToDoPort.execute(Integer.parseInt(request.pathVariable("id")), toDoDTO).toFlowable()))
                                .map(mapperPort::mapToResponse)
                                .flatMap(ServerResponse.ok()::bodyValue))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> deleteToDo(){
        Map<String, String> response = new HashMap<>();
        return RouterFunctions.route().DELETE("/todo/{id}", request ->
                        Mono.just(response)
                        .doOnNext(r -> deleteToDoPort.execute(Integer.parseInt(request.pathVariable("id"))).subscribe())
                        .doOnNext(r -> r.put("message", "The todo with id ".concat(request.pathVariable("id").concat( " was deleted successfully"))))
                        .flatMap(ServerResponse.ok()::bodyValue))
                .build();
    }
}
