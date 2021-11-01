/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto_5.servicio;

import Reto_5.cabin.resportes.ContadorCliente;
import Reto_5.cabin.resportes.StatusReservacion;
import Reto_5.modelo.Reservacion;
import Reto_5.repositorio.RepositorioReservacion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwar
 */

@Service
public class ServiciosReservacion {
     @Autowired
    private RepositorioReservacion metodosCrud;

    public List<Reservacion> getAll(){
        return metodosCrud.getAll();
    }

    public Optional<Reservacion> getReservation(int reservationId) {
        return metodosCrud.getReservation(reservationId);
    }

    public Reservacion save(Reservacion reservation){
        if(reservation.getIdReservation()==null){
            return metodosCrud.save(reservation);
        }else{
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(e.isEmpty()){
                return metodosCrud.save(reservation);
            }else{
                return reservation;
            }
        }
    }

    public Reservacion update(Reservacion reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservacion> e= metodosCrud.getReservation(reservation.getIdReservation());
            if(!e.isEmpty()){

                if(reservation.getStartDate()!=null){
                    e.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    e.get().setStatus(reservation.getStatus());
                }
                metodosCrud.save(e.get());
                return e.get();
            }else{
                return reservation;
            }
        }else{
            return reservation;
        }
    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            metodosCrud.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
     public StatusReservacion getReporteStatusReservaciones() {
        List<Reservacion> completed = metodosCrud.ReservacionStatus("completed");
        List<Reservacion> cancelled = metodosCrud.ReservacionStatus("cancelled");
        return new StatusReservacion(completed.size(), cancelled.size());
    }

    public List<Reservacion> getReportesTiempoReservaciones(String datoA, String datoB) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date datoInicial = new Date();
        Date datoFinal = new Date();

        try {
            datoInicial = parser.parse(datoA);
            datoFinal = parser.parse(datoB);
        } catch (ParseException evt) {
            evt.printStackTrace();
        }
        if (datoInicial.before(datoFinal)) {
            return metodosCrud.ReservacionTiempo(datoInicial, datoFinal);
        } else {
            return new ArrayList<>();
        }
    }

    public List<ContadorCliente> servicioTopClientes() {
        return metodosCrud.getTopClientes();
    }
}
