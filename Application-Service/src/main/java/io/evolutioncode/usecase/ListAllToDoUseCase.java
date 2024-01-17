package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListAllToDoPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Flowable;

public class ListAllToDoUseCase implements ListAllToDoPort {
    private final ToDoRepositoryPort toDoRepository;

    public ListAllToDoUseCase(ToDoRepositoryPort toDoRepositoryPort) {
        this.toDoRepository = toDoRepositoryPort;
    }

    @Override
    public Flowable<ToDo> execute() {
        return toDoRepository.listAll();
    }
}
