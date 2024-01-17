package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Maybe;

public class ListByIdUseCase implements ListByIdPort {
    private final ToDoRepositoryPort toDoRepository;

    public ListByIdUseCase(ToDoRepositoryPort toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public Maybe<ToDo> execute(Integer id) {
        return toDoRepository.findById(id);
    }
}
