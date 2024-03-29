package com.ciclo4.tirodeas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
/**
 *
 * @author andre
 */
@Document(collection = "accessories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accessory {
   @Id
    private String reference;
    private String brand;
    private String category;
    private String material;
    private String description;
    private boolean availability = true;
    private double price;
    private int quantity;
    private String photography; 
}
