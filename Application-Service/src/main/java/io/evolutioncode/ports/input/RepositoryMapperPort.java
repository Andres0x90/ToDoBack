package io.evolutioncode.ports.input;

import io.evolutioncode.model.ToDo;

public interface RepositoryMapperPort<T> {
    T mapToData(ToDo toDo);
    ToDo mapToEntity(T toDoData);
}
