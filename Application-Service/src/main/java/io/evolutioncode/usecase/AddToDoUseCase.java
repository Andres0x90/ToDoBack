package io.evolutioncode.usecase;

import io.evolutioncode.dto.ToDoDTO;
import io.evolutioncode.dto.ToDoResponse;
import io.evolutioncode.ports.input.AddToDoPort;
import io.reactivex.rxjava3.core.Single;

public class AddToDoUseCase implements AddToDoPort {
    @Override
    public Single<ToDoResponse> execute(ToDoDTO toDoDTO) {
        return null;
    }
}
