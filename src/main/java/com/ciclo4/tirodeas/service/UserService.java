package com.ciclo4.tirodeas.service;

import com.ciclo4.tirodeas.model.User;
import com.ciclo4.tirodeas.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class UserService {

    /**
     * Instancia de la clase UserRepository, para utilizar elementos del repositorio como servicio
     */
    @Autowired
    private UserRepository repositorio;

    /**
     * Permite obtener una lista de todos los usuarios
     * @return 
     */
    public List<User> listAll() {
        return repositorio.listAll();
    }
    /**
     * Obtener toda la informacion de un usuario a partir de su id
     * @param id
     * @return 
     */
    public Optional<User> getUser(int id) {
        return repositorio.getUser(id);
    }

    /**
     * Permite crear un nuevo usuario
     * @param user
     * @return 
     */
    public User create(User user) {

        Optional<User> userIdMaximo = repositorio.lastUserId();
        if (user.getId() == null) {
            if (userIdMaximo.isEmpty()) {
                user.setId(1);
            } 
            else {
                user.setId(userIdMaximo.get().getId() + 1);
            }
        }

        Optional<User> e = repositorio.getUser(user.getId());
        if (e.isEmpty()) {
            if (emailExist(user.getEmail()) == false) {
                return repositorio.create(user);
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * *Permite actualizar un usuario existente
     * @param user
     * @return 
     */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = repositorio.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification() != null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getAddress() != null) {
                    userDb.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    userDb.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    userDb.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    userDb.get().setZone(user.getZone());
                }

                repositorio.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }

    /**
     * Permite eliminar un usuario a partir de su id
     * @param userId
     * @return 
     */
    public boolean delete(int userId) {
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    /**
     * Determina si un email existe o no y retorna booleano
     * @param email
     * @return 
     */
    public boolean emailExist(String email) {
        return repositorio.emailExist(email);
    }

    /**
     * Permite autenticar un usuario validacion de correo y contraseña correcta
     * @param email
     * @param password
     * @return 
     */
    public User autenticateUser(String email, String password) {
        Optional<User> usuario = repositorio.autenticateUser(email, password);

        if (usuario.isEmpty()) {
            return new User();
        } else {
            return usuario.get();
        }
    }

    /**
     * Permite determinar una lista de usuarios a partir de un mes dado y con referencia a cumpleaños
     * @param month
     * @return 
     */
    public List<User> listBirthtDayMonth(String month) {
        return repositorio.listBirthtDayMonth(month);
    }
}
