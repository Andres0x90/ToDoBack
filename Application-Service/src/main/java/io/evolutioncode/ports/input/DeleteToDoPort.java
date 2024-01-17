package io.evolutioncode.ports.input;

import io.reactivex.rxjava3.core.Completable;

public interface DeleteToDoPort {
    Completable execute(Integer id);
}
