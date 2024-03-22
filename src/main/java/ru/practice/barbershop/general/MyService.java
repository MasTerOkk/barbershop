package ru.practice.barbershop.general;

import java.util.Optional;

/**
 * This is interface witch represents general logic of service
 * @param <T> entity
 */
public interface MyService<T> {

    /**
     * Get entity with all collections
     * @param id entity id
     * @return Entity
     */
//    T getByIdAllCollections(Long id);
    /**
     * Get entity without collections
     * @param id entity id
     * @return Entity
     */
    T getById(Long id);

    /**
     * Save or update entity to database
     * @param entity entity object
     */
    void save(T entity);

}
