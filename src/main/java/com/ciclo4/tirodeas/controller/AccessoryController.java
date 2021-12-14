/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ciclo4.tirodeas.controller;

import com.ciclo4.tirodeas.model.Accessory;
import com.ciclo4.tirodeas.service.AccessoryService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author andre
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/accessory")
public class AccessoryController {
    
    @Autowired
    private AccessoryService servicio;

    //Mediante un GET permite obtener todos los accesorios
    @GetMapping("/all")
    public List<Accessory> listAll() {
        return servicio.listAll();
    }

    /**
     * Mediante un GET permite obtener toda la informacion de un accesorio a partir de su reference(id)
     * @param reference
     * @return 
     */
    @GetMapping("/{reference}")
    public Optional<Accessory> getAccesory(@PathVariable("reference") String reference) {
        return servicio.getAccesory(reference);
    }

    /**
     * mediante un POST permite crear un nuevo accesorio
     * @param accessory
     * @return 
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Accessory create(@RequestBody Accessory accessory) {
        return servicio.create(accessory);
    }

    /**
     * Mediante un PUT permite actualizar un accesorio existente
     * @param accessory
     * @return 
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Accessory update(@RequestBody Accessory accessory) {
        return servicio.update(accessory);
    }

    /**
     * Con el uso de la reference(id) permite eliminar un accesorio mediante el uso de un DELETE
     * @param reference
     * @return 
     */
    @DeleteMapping("/{reference}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("reference") String reference) {
        return servicio.delete(reference);
    }
    
    /**
     * Mediante un GET permite filtrar informacion y obtener accesorios con un precio menor o igual al indicado
     * @param precio
     * @return 
     */
    @GetMapping("/price/{price}")
    public List<Accessory> productsByPrice(@PathVariable("price") double precio){
        return servicio.productsByPrice(precio);
    }
    
    /**
     * mediante un GET permite filtrar informacion y obtener accesorios que contengan una palabra en especifico
     * @param description
     * @return 
     */
    @GetMapping("/description/{description}")
    public List<Accessory> findByDescriptionLike(@PathVariable("description") String description){
	return servicio.findByDescriptionLike(description);
    }
    
    /**
     * mediante un GET permite determinar si una reference ya existe o no
     * @param reference
     * @return 
     */
    @GetMapping("/verificateId/{reference}")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean referenceVerification(@PathVariable("reference") String reference) {
        return servicio.referenceVerification(reference);
    }
}
