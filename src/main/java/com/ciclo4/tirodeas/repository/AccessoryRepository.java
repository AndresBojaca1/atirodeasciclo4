package com.ciclo4.tirodeas.repository;

import com.ciclo4.tirodeas.model.Accessory;
import com.ciclo4.tirodeas.repository.crud.AccessoryCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
public class AccessoryRepository {

    @Autowired
    private AccessoryCrudRepository crudInterface;

    //Permite listar a todos los accesorios
    public List<Accessory> listAll() {
        return crudInterface.findAll();
    }

    //Obtener un accesorio a partir de su Reference (id)
    public Optional<Accessory> getAccesory(String reference) {
        return crudInterface.findById(reference);
    }

    //Crear un nuevo accesorio
    public Accessory create(Accessory accesory) {
        return crudInterface.save(accesory);
    }

    //Actualizar un nuevo accesorio
    public void update(Accessory accesory) {
        crudInterface.save(accesory);
    }

    //Eliminar un accesorio
    public void delete(Accessory accesory) {
        crudInterface.delete(accesory);
    }

    //Reto 5: permite determinar un producto por precio (lista de accesorio)
    public List<Accessory> productsByPrice(double precio) {
        return crudInterface.findByPriceLessThanEqual(precio);
    }

    //Reto 5: permite filtrar accesorios a partir de una palabra dada
    public List<Accessory> findByDescriptionLike(String description) {
        return crudInterface.findByDescriptionLike(description);
    }
    
    //Determina si una referencia existe o no y retorna booleano
    public boolean referenceVerification(String reference) {
        Optional<Accessory> accessory = crudInterface.findById(reference);
        return !accessory.isEmpty();
    }
}
