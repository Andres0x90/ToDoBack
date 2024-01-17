package io.evolutioncode.ports.input;

import io.evolutioncode.model.ToDo;
import io.reactivex.rxjava3.core.Flowable;

public interface ListAllToDoPort {
    Flowable<ToDo> execute();
}
