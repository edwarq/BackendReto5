/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reto_5.crud;

import Reto_5.modelo.Cabin;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Edwar
 */
public interface InterfaceCabin extends CrudRepository<Cabin,Integer> {
    
}
