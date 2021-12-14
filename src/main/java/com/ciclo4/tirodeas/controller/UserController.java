package com.ciclo4.tirodeas.controller;

import com.ciclo4.tirodeas.model.User;
import com.ciclo4.tirodeas.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
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
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author andre
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService servicio;

    /**
     * Mediante un GET permite obtener a todos los usuarios
     * @return 
     */
    @GetMapping("/all")
    public List<User> listAll() {
        return servicio.listAll();
    }

    /**
     * mediante un GET permite obtener a un usuario en particular a partir de su id
     * @param id
     * @return 
     */
    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") int id) {
        return servicio.getUser(id);
    }

    /**
     * mediante un POST permite crear un nuevo usuario
     * @param user
     * @return 
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return servicio.create(user);
    }

    /**
     * mediante un PUT permite actualizar un usuario existente
     * @param user
     * @return 
     */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user) {
        return servicio.update(user);
    }

    /**
     * mediante un GET permite obtener si un email existe o no, esto retorna un booleano
     * @param email
     * @return 
     */
    @GetMapping("/emailexist/{email}")
    public boolean emailExist(@PathVariable("email") String email) {
        return servicio.emailExist(email);
    }

    /**
     * Mediante un DELETE permite eliminar un usuario en particular a partir de su id
     * @param id
     * @return 
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return servicio.delete(id);
    }

    /**
     * mediante un GET permite validar si un correo y contraseña estan correctos
     * @param email
     * @param password
     * @return 
     */
    @GetMapping("/{email}/{password}")
    public User autenticateUser(@PathVariable("email") String email, @PathVariable("password") String password) {
        return servicio.autenticateUser(email, password);
    }

    /**
     * Reto 5: mediante un GET muestra una lista de usuarios qe cumplen años en un mes determinado
     * @param month
     * @return 
     */
    @GetMapping("/birthday/{month}")
    public List<User> listBirthtDayMonth(@PathVariable("month") String month) {
        return servicio.listBirthtDayMonth(month);
    }
}
