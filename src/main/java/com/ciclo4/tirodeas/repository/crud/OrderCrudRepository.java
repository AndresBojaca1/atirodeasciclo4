package com.ciclo4.tirodeas.repository.crud;

import com.ciclo4.tirodeas.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 *
 * @author andre
 */
public interface OrderCrudRepository extends MongoRepository<Order, Integer> {
    
    //Funcion retorna las ordenes de pedido a partir de zona por vendendor/asesor
    @Query("{'salesMan.zone': ?0}")
    List<Order> findByZone(final String zone);
    
    //Funcion retorna ordenes a partir de su status
    @Query("{status: ?0}")
    List<Order> findByStatus(final String status);
    
    //Funcion permite obtener el mayor ID 
    Optional<Order> findTopByOrderByIdDesc();
}
