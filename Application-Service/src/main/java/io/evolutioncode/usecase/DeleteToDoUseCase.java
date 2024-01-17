package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.DeleteToDoPort;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Completable;

public class DeleteToDoUseCase implements DeleteToDoPort {

    private final ToDoRepositoryPort toDoRepository;
    private final ListByIdPort listByIdUseCase;

    public DeleteToDoUseCase(ToDoRepositoryPort toDoRepositoryPort, ListByIdPort listByIdPort) {
        this.toDoRepository = toDoRepositoryPort;
        this.listByIdUseCase = listByIdPort;
    }

    @Override
    public Completable execute(Integer id) {
        return  listByIdUseCase.execute(id)
                .map(ToDo::getId)
                .flatMapCompletable(toDoRepository::deleteToDo);
    }
}
