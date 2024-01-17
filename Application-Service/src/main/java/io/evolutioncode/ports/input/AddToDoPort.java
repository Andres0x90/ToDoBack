package io.evolutioncode.ports.input;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.dto.ToDoResponse;
import io.reactivex.rxjava3.core.Single;

public interface AddToDoPort {
    Single<ToDoResponse> execute(ToDoDTO toDoDTO);
}
