package com.ciclo4.tirodeas.service;

import com.ciclo4.tirodeas.model.Accessory;
import com.ciclo4.tirodeas.repository.AccessoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class AccessoryService {

    @Autowired
    private AccessoryRepository repositorio;

    //Obtener todos los accesorios
    public List<Accessory> listAll() {
        return repositorio.listAll();
    }

    //Obtener un accesorio a partir de una reference(id)
    public Optional<Accessory> getAccesory(String reference) {
        return repositorio.getAccesory(reference);
    }

    //Crear un nuevo accesorio y sus validaciones
    public Accessory create(Accessory accesory) {
        if (accesory.getReference() == null) {
            return accesory;
        } else {
            return repositorio.create(accesory);
        }
    }

    //Actualizar un accesorio existente y sus validaciones
    public Accessory update(Accessory accesory) {

        if (accesory.getReference() != null) {
            Optional<Accessory> accesoryDb = repositorio.getAccesory(accesory.getReference());
            if (!accesoryDb.isEmpty()) {
                if (accesory.getBrand() != null) {
                    accesoryDb.get().setBrand(accesory.getBrand());
                }

                if (accesory.getCategory() != null) {
                    accesoryDb.get().setCategory(accesory.getCategory());
                }

                if (accesory.getMaterial() != null) {
                    accesoryDb.get().setMaterial(accesory.getMaterial());
                }

                if (accesory.getDescription() != null) {
                    accesoryDb.get().setDescription(accesory.getDescription());
                }
                if (accesory.getPrice() != 0.0) {
                    accesoryDb.get().setPrice(accesory.getPrice());
                }
                if (accesory.getQuantity() != 0) {
                    accesoryDb.get().setQuantity(accesory.getQuantity());
                }
                if (accesory.getPhotography() != null) {
                    accesoryDb.get().setPhotography(accesory.getPhotography());
                }
                accesoryDb.get().setAvailability(accesory.isAvailability());
                repositorio.update(accesoryDb.get());
                return accesoryDb.get();
            } else {
                return accesory;
            }
        } else {
            return accesory;
        }
    }

    //Eliminar un accesorio
    public boolean delete(String reference) {
        Boolean aBoolean = getAccesory(reference).map(accesory -> {
            repositorio.delete(accesory);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    //Reto 5: obtener accesorios (y su informacion) a partir de un precio dado menor o igual
    public List<Accessory> productsByPrice(double precio) {
        return repositorio.productsByPrice(precio);
    }

    //Reto 5: Filtrar accesorios por palabras clave en la descripcion
    public List<Accessory> findByDescriptionLike(String description) {
        return repositorio.findByDescriptionLike(description);
    }
    
    //Retorna un booleano para responder si una reference ya existe o no
    public boolean referenceVerification(String reference) {
        return repositorio.referenceVerification(reference);
    }
}
