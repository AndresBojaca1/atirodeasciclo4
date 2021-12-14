package com.ciclo4.tirodeas.service;

import com.ciclo4.tirodeas.model.Order;
import com.ciclo4.tirodeas.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //Obtener todas las ordenes y su informacion
    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    //Permite obtener una orden y su informacion a partir de una id
    public Optional<Order> getOrder(int id) {
        return orderRepository.getOrder(id);
    }

    //Permite crear una nueva orden
    public Order create(Order order) {

        Optional<Order> orderIdMaxima = orderRepository.lastUserId();
        if (order.getId() == null) {
            if (orderIdMaxima.isEmpty()) {
                order.setId(1);
            } 
            else {
                order.setId(orderIdMaxima.get().getId() + 1);
            }
        }

        Optional<Order> e = orderRepository.getOrder(order.getId());
        if (e.isEmpty()) {
            return orderRepository.create(order);
        } else {
            return order;
        }
    }

    //Actualiza una orden existente
    public Order update(Order order) {

        if (order.getId() != null) {
            Optional<Order> orderDb = orderRepository.getOrder(order.getId());
            if (!orderDb.isEmpty()) {
                if (order.getStatus() != null) {
                    orderDb.get().setStatus(order.getStatus());
                }
                orderRepository.update(orderDb.get());
                return orderDb.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    //Elimina una orden a partir de su id
    public boolean delete(int id) {
        Boolean aBoolean = getOrder(id).map(order -> {
            orderRepository.delete(order);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Reto 3: Ordenes de pedido asociadas a una zona
    public List<Order> findByZone(String zona) {
        return orderRepository.findByZone(zona);
    }

    //Reto 4: Permite listar ordenes de pedido de un asesor
    public List<Order> ordersSalesManByID(Integer id) {
        return orderRepository.ordersSalesManByID(id);
    }

    //Reto 4: Listar ordenes de pedido por estado de un asesor y su id
    public List<Order> ordersSalesManByState(String state, Integer id) {
        return orderRepository.ordersSalesManByState(state, id);
    }

    //Reto 4: ordenes de pedido por fecha de un asesor y su id
    public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
        return orderRepository.ordersSalesManByDate(dateStr, id);
    }
}
