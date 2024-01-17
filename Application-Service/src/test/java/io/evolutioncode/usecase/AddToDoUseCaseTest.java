package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.AddToDoPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class AddToDoUseCaseTest {

    @Mock
    ToDoRepositoryPort toDoRepositoryPort;
    @Mock
    MapperPort mapperPort;
    @InjectMocks
    AddToDoUseCase addToDoPort;


    @Test
    public void execute() {

        Mockito.when(mapperPort.mapToEntity(Mockito.any())).thenReturn(ToDo.builder()
                        .description("test")
                        .checked(false)
                .build());

        Mockito.when(toDoRepositoryPort.save(Mockito.any())).thenReturn(
                Single.just(ToDo.builder()
                        .id(1)
                        .description("test")
                        .checked(false)
                        .build())
        );

        StepVerifier.create(addToDoPort.execute(ToDoDTO.builder()
                .description("test")
                .checked(false)
                .build()).toFlowable())
                .expectNextMatches(toDo -> toDo.getId().equals(1))
                .verifyComplete();
    }
}