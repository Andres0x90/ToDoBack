package io.evolutioncode;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.output.RepositoryMapperPort;
import org.springframework.stereotype.Component;

@Component
public class ToDoMapper implements RepositoryMapperPort<ToDoData> {
    @Override
    public ToDoData mapToData(ToDo toDo) {
        return ToDoData.builder()
                .id(toDo.getId())
                .description(toDo.getDescription())
                .checked(toDo.isChecked())
                .build();
    }

    @Override
    public ToDo mapToEntity(ToDoData toDoData) {
        return ToDo.builder()
                .id(toDoData.getId())
                .description(toDoData.getDescription())
                .checked(toDoData.isChecked())
                .build();
    }
}
