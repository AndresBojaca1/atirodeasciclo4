package com.ciclo4.tirodeas.controller;

import com.ciclo4.tirodeas.model.Order;
import com.ciclo4.tirodeas.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre
 */
@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    /**
     * Mediante un GET permite obtener todas las ordenes
     * @return 
     */
    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAll();
    }

    /**
     * mediante un GET permite obtener una orden a partir de una ID
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable("id") int id) {
        return orderService.getOrder(id);
    }

    /**
     * mediante un POST permite crear una nueva orden
     * @param gadget
     * @return 
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@RequestBody Order gadget) {
        return orderService.create(gadget);
    }

    /**
     * mediante un PUT permite actualizar una orden existente
     * @param gadget
     * @return 
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Order update(@RequestBody Order gadget) {
        return orderService.update(gadget);
    }

    /**
     * mediante un DELETE permite eliminar una orden a partir de una id en especifico
     * @param id
     * @return 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return orderService.delete(id);
    }
    
    /**
     * Reto 3: Permite generar las ordenes de pedido asociadas a los asesores de una zona
     * @param zona
     * @return 
     */
    @GetMapping("/zona/{zona}")  
    public List<Order> findByZone(@PathVariable("zona") String zona) {
        return orderService.findByZone(zona);
    }
    
    /**
     * Reto 3: Permite generar las ordenes de un asesor con una id en especifico
     * @param id
     * @return 
     */
    @GetMapping("/salesman/{id}")
    public List<Order> ordersSalesManByID(@PathVariable("id") Integer id) {
       return orderService.ordersSalesManByID(id);
    }
    
    /**
     * Reto 4: Mediante un get permite listar ordenes de pedido por por estado y asesor(id)
     * @param state
     * @param id
     * @return 
     */
    @GetMapping("/state/{state}/{id}")
    public List<Order> ordersSalesManByState(@PathVariable("state") String state, @PathVariable("id") Integer id) {
        return orderService.ordersSalesManByState(state,id);
    }
    
    /**
     * Reto 4: mediante un GET permite generar las ordenes de un asesor(id) y Fecha
     * @param dateStr
     * @param id
     * @return 
     */
    @GetMapping("/date/{date}/{id}")
    public List<Order> ordersSalesManByDate(@PathVariable("date") String dateStr, @PathVariable("id") Integer id){
        return orderService.ordersSalesManByDate(dateStr, id);
    }
}
