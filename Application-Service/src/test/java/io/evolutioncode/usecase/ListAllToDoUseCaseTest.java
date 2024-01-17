package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListAllToDoUseCaseTest {

    @Mock
    ToDoRepositoryPort toDoRepositoryPort;

    @InjectMocks
    ListAllToDoUseCase listAllToDoPort;


    @Test
    public void execute() {
        Mockito.when(toDoRepositoryPort.listAll()).thenReturn(
                Flowable.just(ToDo.builder()
                                .id(1)
                                .description("test")
                                .checked(false)
                                .build(),
                        ToDo.builder()
                                .id(2)
                                .description("test2")
                                .checked(false)
                                .build(),
                        ToDo.builder()
                                .id(3)
                                .description("test3")
                                .checked(true)
                                .build())
        );

        StepVerifier.create(listAllToDoPort.execute())
                .expectNextMatches(toDo -> toDo.getId().equals(1))
                .expectNextMatches(toDo -> toDo.getId().equals(2))
                .expectNextMatches(toDo -> toDo.getId().equals(3))
                .verifyComplete();
    }
}