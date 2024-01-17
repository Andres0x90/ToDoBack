package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.input.UpdateToDoPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Maybe;
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
class UpdateToDoUseCaseTest {

    @Mock
    ToDoRepositoryPort toDoRepositoryPort;
    @Mock
    MapperPort mapperPort;
    @Mock
    ListByIdPort listByIdPort;
    @InjectMocks
    UpdateToDoUseCase updateToDoPort;


    @Test
    public void execute() {

        Mockito.when(listByIdPort.execute(1)).thenReturn(Maybe.just(ToDo.builder()
                .id(1)
                .description("test")
                .checked(false)
                .build()));

        Mockito.when(mapperPort.mapToEntity(Mockito.any())).thenReturn(ToDo.builder()
                .description("test")
                .checked(false)
                .build());

        Mockito.when(toDoRepositoryPort.save(Mockito.any())).thenReturn(
                Single.just(ToDo.builder()
                        .id(1)
                        .description("test updated")
                        .checked(true)
                        .build())
        );

        StepVerifier.create(updateToDoPort.execute( 1,ToDoDTO.builder()
                        .description("test")
                        .checked(false)
                        .build()).toFlowable())
                .expectNextMatches(toDo -> toDo.getId().equals(1) && toDo.getDescription().equals("test updated")
                && toDo.isChecked())
                .verifyComplete();
    }
}