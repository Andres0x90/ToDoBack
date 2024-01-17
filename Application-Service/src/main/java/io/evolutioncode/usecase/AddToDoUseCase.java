package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.AddToDoPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Single;

public class AddToDoUseCase implements AddToDoPort {

    private final ToDoRepositoryPort toDoRepository;
    private final MapperPort mapper;

    public AddToDoUseCase(ToDoRepositoryPort toDoRepositoryPort, MapperPort mapperPort) {
        this.toDoRepository = toDoRepositoryPort;
        this.mapper = mapperPort;
    }

    @Override
    public Single<ToDo> execute(ToDoDTO toDoDTO) {
        return Single.just(toDoDTO)
                .map(mapper::mapToEntity)
                .flatMap(toDoRepository::save);
    }
}
