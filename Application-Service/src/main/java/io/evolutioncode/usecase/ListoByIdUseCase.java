package io.evolutioncode.usecase;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public class ListoByIdUseCase implements ListByIdPort {
    private final ToDoRepositoryPort toDoRepository;

    public ListoByIdUseCase(ToDoRepositoryPort toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public Maybe<ToDo> execute(Integer id) {
        return toDoRepository.findById(id);
    }
}
