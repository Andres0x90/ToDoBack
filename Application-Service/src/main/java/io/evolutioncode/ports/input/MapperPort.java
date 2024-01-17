package io.evolutioncode.ports.input;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.dto.ToDoResponse;
import io.evolutioncode.model.ToDo;

public interface MapperPort {
    ToDo mapToEntity(ToDoDTO toDoDTO);
    ToDoResponse mapToResponse(ToDo toDo);
}
