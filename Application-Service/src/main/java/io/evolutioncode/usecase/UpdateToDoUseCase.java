package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.ListByIdPort;
import io.evolutioncode.ports.input.MapperPort;
import io.evolutioncode.ports.input.UpdateToDoPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Single;

public class UpdateToDoUseCase implements UpdateToDoPort {
    private final ToDoRepositoryPort toDoRepository;
    private final MapperPort mapper;
    private final ListByIdPort listByIdUseCase;

    public UpdateToDoUseCase(ToDoRepositoryPort toDoRepositoryPort, MapperPort mapperPort, ListByIdPort listByIdPort) {
        this.toDoRepository = toDoRepositoryPort;
        this.mapper = mapperPort;
        this.listByIdUseCase = listByIdPort;
    }

    @Override
    public Single<ToDo> execute(Integer id, ToDoDTO toDoDTO) {
        return listByIdUseCase.execute(id)
                .map(toDo -> mapper.mapToEntity(toDoDTO))
                .map(toDo -> toDo.toBuilder().id(id).build())
                .toSingle()
                .flatMap(toDoRepository::save);
    }
}
