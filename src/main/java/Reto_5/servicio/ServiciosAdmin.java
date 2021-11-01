/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto_5.servicio;

import Reto_5.modelo.Admin;
import Reto_5.repositorio.RepositorioAdmin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwar
 */
@Service
public class ServiciosAdmin {
    @Autowired
    private RepositorioAdmin repositoryAdmin;
 
    public List<Admin> getAll(){
       return repositoryAdmin.getAll();
    }
    
     public Optional<Admin> getAdmin(int AdminId) {
        return repositoryAdmin.getAdmin(AdminId);
    }
    
    public Admin save(Admin admin) {
        if (admin.getIdAdmin() == null) {
            return repositoryAdmin.save(admin);
        } else {
            Optional<Admin> admin1 = repositoryAdmin.getAdmin(admin.getIdAdmin());
            if (admin1.isEmpty()) {
                return repositoryAdmin.save(admin);
            } else {
                return admin;
            }
        }
    }
    
       public Admin update(Admin admin){
        if(admin.getIdAdmin()!=null){
            Optional<Admin> e= repositoryAdmin.getAdmin(admin.getIdAdmin());
            if(!e.isEmpty()){

                if(admin.getName()!=null){
                    e.get().setName(admin.getName());
                }
                if(admin.getEmail()!=null){
                    e.get().setEmail(admin.getEmail());
                }
                if(admin.getPassword()!=null){
                    e.get().setPassword(admin.getPassword());
                }
                repositoryAdmin.save(e.get());
                return e.get();
            }else{
                return admin;
            }
        }else{
            return admin;
        }
    }

    public boolean deleteAdmin(int adminId) {
        Boolean aBoolean = getAdmin(adminId).map(admin -> {
            repositoryAdmin.delete(admin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
