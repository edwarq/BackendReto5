/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto_5.repositorio;

import Reto_5.cabin.resportes.ContadorCliente;
import Reto_5.modelo.Reservacion;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Reto_5.crud.InterfaceReservacion;
import Reto_5.modelo.Cliente;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Edwar
 */
@Repository
public class RepositorioReservacion {
       @Autowired
    private InterfaceReservacion crud4;

    public List<Reservacion> getAll(){
        return (List<Reservacion>) crud4.findAll();
    }
    public Optional<Reservacion> getReservation(int id){
        return crud4.findById(id);
    }
    public Reservacion save(Reservacion reservation){
        return crud4.save(reservation);
    }
    public void delete(Reservacion reservation){
        crud4.delete(reservation);
    }
    
     public List<Reservacion> ReservacionStatus(String status) {
        return crud4.findAllByStatus(status);
    }

    public List<Reservacion> ReservacionTiempo(Date a, Date b) {
        return crud4.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<ContadorCliente> getTopClientes() {
        List<ContadorCliente> respuesta = new ArrayList<>();
        List<Object[]> report = crud4.countTotalReservationsByClient();
        for (int i = 0; i < report.size(); i++) {
            respuesta.add(new ContadorCliente((Long) report.get(i)[1], (Cliente) report.get(i)[0]));
        }
        return respuesta;
    }
}
