package ru.practice.barbershop.general;


import java.util.List;

/**
 * This is interface witch represents general logic of service
 * @param <D> dto
 * @param <E> entity
 */
public interface MyService<D,E> {


    /**
     * Get entity
     * @param id entity id
     * @return Entity
     */
    D getDtoById(Long id);

    /**
     * Get entity
     * @param id entity id
     * @return Entity
     */
    E getEntityById(Long id);

    /**
     * Save dto in database
     * @param dto object
     */
    D save(D dto);

    /**
     * Update dto in database
     * @param dto object
     */
    D update(D dto);

    /**
     * Get list of dto
     * @return List of dto
     */
    List<D> getAllDto();

}
