package com.ciclo4.tirodeas.repository.crud;

import com.ciclo4.tirodeas.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author andre
 */
public interface UserCrudRepository extends MongoRepository<User,Integer> {
    
    //Se definen todas las consultas adicionales que no estan predeterminadas en "MongoRepository"
    
    //Funcion retorna los datos de usuario (Optional) a partir de un email
    public Optional <User> findByEmail(String email);
    
    //Funcion retorna los datos de usuario (Optional) si coinciden email y password
    public Optional<User> findByEmailAndPassword(String email, String password);
    
    //Funcion permite determinar la maxima ID
    Optional<User> findTopByOrderByIdDesc();
    
    //Funcion permite determinar el mes de cumpleaños de un usuario generar lista
    List<User> findByMonthBirthtDay(String month);    
}
