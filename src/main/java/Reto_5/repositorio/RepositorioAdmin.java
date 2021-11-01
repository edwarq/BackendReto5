/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto_5.repositorio;

import Reto_5.crud.InterfaceAdmin;
import Reto_5.modelo.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edwar
 */

@Repository
public class RepositorioAdmin {
     @Autowired
    private InterfaceAdmin interfaceAdmin;
    
    public List<Admin> getAll(){
        return (List<Admin>) interfaceAdmin.findAll();
    }
    
    public Optional<Admin> getAdmin(int id){
        return interfaceAdmin.findById(id);
    }   
  
    public Admin save(Admin admin){
        return interfaceAdmin.save(admin);
    }
    
     public void delete(Admin admin){
        interfaceAdmin.delete(admin);
    }
}
