package com.ciclo4.tirodeas.repository;

import com.ciclo4.tirodeas.model.Order;
import com.ciclo4.tirodeas.repository.crud.OrderCrudRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class OrderRepository {

    @Autowired
    private OrderCrudRepository orderCrudRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //permite obtener toda la informacion de una orden
    public List<Order> getAll() {
        return (List<Order>) orderCrudRepository.findAll();
    }

    //Obtener una orden a partir de su id
    public Optional<Order> getOrder(int id) {
        return orderCrudRepository.findById(id);
    }

    //Crear una orden
    public Order create(Order order) {
        return orderCrudRepository.save(order);
    }

    //Actualizar una orden
    public void update(Order order) {
        orderCrudRepository.save(order);
    }

    //Borrar una orden
    public void delete(Order order) {
        orderCrudRepository.delete(order);
    }

    //Determinar la ultima ID 
    public Optional<Order> lastUserId() {
        return orderCrudRepository.findTopByOrderByIdDesc();
    }

    //Hallar orden por zona
    public List<Order> findByZone(String zona) {
        return orderCrudRepository.findByZone(zona);
    }

    //Reto 4: Permite determinar las ordenes de un ASE a partir de su ID
    public List<Order> ordersSalesManByID(Integer id) {

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    //Reto 4: Permite visualizar las el estado de las ordenes de un asesor con su ID
    public List<Order> ordersSalesManByState(String state, Integer id) {

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("salesMan.id").is(id)
                .and("status").is(state);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }

    //Reto 4: permite filtrar las ordenes de un asesor (id) por fecha.
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Query query = new Query();
        Criteria dateCriteria = Criteria.where("registerDay")
                .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
                .lt(LocalDate.parse(dateStr, dtf).plusDays(2).atStartOfDay())
                .and("salesMan.id").is(id);

        query.addCriteria(dateCriteria);
        List<Order> orders = mongoTemplate.find(query, Order.class);

        return orders;
    }
}
