package io.evolutioncode;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoDataRepository extends CrudRepository<ToDoData, Integer> {
}
