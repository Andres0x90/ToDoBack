package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteToDoUseCaseTest {

    @Mock
    ToDoRepositoryPort toDoRepositoryPort;

    @Mock
    ListByIdPort listByIdPort;

    @InjectMocks
    DeleteToDoUseCase deleteToDoPort;


    @Test
    public void execute() {
        Mockito.when(listByIdPort.execute(1)).thenReturn(
                Maybe.just(ToDo.builder()
                                .id(1)
                                .description("test")
                                .checked(false)
                                .build()));

        Mockito.when(toDoRepositoryPort.deleteToDo(1)).thenReturn(Completable.complete());

        StepVerifier.create(Flowable.fromCompletable(deleteToDoPort.execute(1)))
                .verifyComplete();
    }

}