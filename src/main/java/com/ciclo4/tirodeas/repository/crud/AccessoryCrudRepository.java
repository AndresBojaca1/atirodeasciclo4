package com.ciclo4.tirodeas.repository.crud;

import com.ciclo4.tirodeas.model.Accessory;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
/**
 *
 * @author andre
 */
public interface AccessoryCrudRepository extends MongoRepository<Accessory, String> {
    
    //Funcion permite hallar precios menores o iguales a un valor definido
    public List<Accessory> findByPriceLessThanEqual(double precio);
    
    //Funcion permite determinar palabras dentro de la descripcion a partir de una peticion mongo
    @Query("{'description':{'$regex':'?0','$options':'i'}}")
    public List<Accessory> findByDescriptionLike(String description);
}
