package io.evolutioncode.ports.input;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.model.ToDo;
import io.reactivex.rxjava3.core.Single;

public interface AddToDoPort {
    Single<ToDo> execute(ToDoDTO toDoDTO);
}
