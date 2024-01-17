package io.evolutioncode.ports.output;

import io.evolutioncode.model.ToDo;

public interface RepositoryMapperPort<T> {
    T mapToData(ToDo toDo);
    ToDo mapToEntity(T toDoData);
}
