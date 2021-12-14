package com.ciclo4.tirodeas.repository;

import com.ciclo4.tirodeas.model.User;
import com.ciclo4.tirodeas.repository.crud.UserCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class UserRepository {

    @Autowired
    private UserCrudRepository crudInterface;

    //Permite obtener todos los usuarios
    public List<User> listAll() {
        return crudInterface.findAll();
    }

    //Permite obtener un usuario y sus datos a partir de su id
    public Optional<User> getUser(int id) {
        return crudInterface.findById(id);
    }

    //Permite crear un nuevo usuario
    public User create(User user) {
        return crudInterface.save(user);
    }

    //Permite actualizar un usuario
    public void update(User user) {
        crudInterface.save(user);
    }

    //Permite borrar un usuario
    public void delete(User user) {
        crudInterface.delete(user);
    }

    //Verifica si existe un email a partir de un email dado, retorna booleano
    public boolean emailExist(String email) {
        Optional<User> usuario = crudInterface.findByEmail(email);

        return !usuario.isEmpty();
    }

    //Retorna un usuario si la autenticacion de este es correcta, email y password coinciden
    public Optional<User> autenticateUser(String email, String password) {
        return crudInterface.findByEmailAndPassword(email, password);
    }

    //Rertorna los datos de la ultima ID
    public Optional<User> lastUserId() {
        return crudInterface.findTopByOrderByIdDesc();
    }

    //A partir de un mes retorna los datos de usuario que cumpla ese mes
    public List<User> listBirthtDayMonth(String month) {
        return crudInterface.findByMonthBirthtDay(month);
    }
}
