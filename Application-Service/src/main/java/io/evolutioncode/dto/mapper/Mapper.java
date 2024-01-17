package io.evolutioncode.dto.mapper;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.dto.ToDoResponse;
import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.MapperPort;

public class Mapper implements MapperPort {
    @Override
    public ToDo mapToEntity(ToDoDTO toDoDTO) {
        return ToDo.builder()
                .description(toDoDTO.getDescription())
                .checked(toDoDTO.isChecked())
                .build();
    }

    @Override
    public ToDoResponse mapToResponse(ToDo toDo) {
        return ToDoResponse.builder()
                .id(toDo.getId())
                .description(toDo.getDescription())
                .checked(toDo.isChecked())
                .build();
    }
}
