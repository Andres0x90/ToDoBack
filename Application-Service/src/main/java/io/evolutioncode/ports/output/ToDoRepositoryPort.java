package io.evolutioncode.ports.output;

import io.evolutioncode.model.ToDo;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface ToDoRepositoryPort {
    Single<ToDo> save(ToDo toDo);
    Maybe<ToDo> findById(Integer id);
    Flowable<ToDo> listAll();
    Completable deleteToDo(Integer id);
}
