package io.evolutioncode;

import io.evolutioncode.model.ToDo;
import io.evolutioncode.ports.input.RepositoryMapperPort;
import io.evolutioncode.ports.output.ToDoRepositoryPort;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ToDoRepositoryAdapter implements ToDoRepositoryPort {

    @Autowired
    private ToDoDataRepository toDoDataRepository;
    @Autowired
    private RepositoryMapperPort<ToDoData> toDoMapper;


    @Override
    public Single<ToDo> save(ToDo toDo) {
        return Single.just(toDo)
                .map(toDoMapper::mapToData)
                .map(toDoDataRepository::save)
                .map(toDoMapper::mapToEntity);
    }

    @Override
    public Maybe<ToDo> findById(Integer id) {
        return Maybe.just(toDoDataRepository.findById(id).orElseThrow(() ->
                        new RuntimeException("The ToDo with the id ".concat(id.toString()).concat(" was not found"))))
                .map(toDoMapper::mapToEntity);
    }

    @Override
    public Flowable<ToDo> listAll() {
        return Flowable.fromIterable(toDoDataRepository.findAll())
                .map(toDoMapper::mapToEntity);
    }


    @Override
    public Completable deleteToDo(Integer id) {
        return Completable.create(emitter -> toDoDataRepository.deleteById(id));
    }
}
