package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ListByIdToDoUseCaseTest {

    @Mock
    ToDoRepositoryPort toDoRepositoryPort;

    @InjectMocks
    ListByIdUseCase listByIdPort;


    @Test
    public void execute() {
        Mockito.when(toDoRepositoryPort.findById(1)).thenReturn(
                Maybe.just(ToDo.builder()
                                .id(1)
                                .description("test")
                                .checked(false)
                                .build()
        ));

        StepVerifier.create(listByIdPort.execute(1).toFlowable())
                .expectNextMatches(toDo -> toDo.getId().equals(1))
                .verifyComplete();
    }
}