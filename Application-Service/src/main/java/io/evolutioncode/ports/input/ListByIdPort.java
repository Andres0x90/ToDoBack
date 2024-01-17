package io.evolutioncode.ports.input;

import io.evolutioncode.model.ToDo;
import io.reactivex.rxjava3.core.Maybe;

public interface ListByIdPort {
    Maybe<ToDo> execute(Integer id);
}
