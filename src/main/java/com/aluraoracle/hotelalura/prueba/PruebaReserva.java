package com.aluraoracle.hotelalura.prueba;

import com.aluraoracle.hotelalura.DAO.ReservaDao;
import com.aluraoracle.hotelalura.logica.Persona;
import com.aluraoracle.hotelalura.logica.Reserva;
import com.aluraoracle.hotelalura.logica.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




public class PruebaReserva {
    
    public static void main(String[] args) {
        ReservaDao reservaDao = new ReservaDao();

        listarTodasLasReservas(reservaDao);

        reservaDao.cerrarEntityManagerFactory();
    }
    
    private static void registrarReserva(ReservaDao reservaDao){
        
        // Crear una nueva persona
        Usuario usuario = new Usuario("bryan", "bryan123", true);
        usuario.setId(1l);
         
        Date fechaNacimiento = parseFecha("1980-01-01");
        Date fechaCheckIn = parseFecha("2023-10-01");              
        Date fechaCheckOut = parseFecha("2023-10-05");
                        
        Persona personaUsuario = new Persona("1231413443", "4354sef", "bryan", "callirgos", "colombiano", "3124563453", fechaNacimiento,usuario, true);
        personaUsuario.setId(1l);
        Persona personaHuesped = new Persona("6234235871", "124sef", "pepito", "casas", "colombiano", "3125432453", fechaNacimiento,null, true);
        personaHuesped.setId(3l);
        
        Reserva reservaNueva = new Reserva(personaUsuario,personaHuesped,fechaCheckIn,fechaCheckOut, true); 
    
         // Registrar Resera
        Reserva reservaRegistrada = reservaDao.registrarReserva(reservaNueva);

        if (reservaRegistrada != null) {
            System.out.println("Reserva registrada con éxito. ID: " + reservaRegistrada.getId());
        } else {
            System.out.println("La reserva no se pudo registrar.");
        }
    }
        
    private static Date parseFecha(String fecha){        
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        }catch (ParseException e) {
            e.printStackTrace(); 
            return null;
        }
    }
    
    
    private static void actualizarReserva(ReservaDao reservaDao){
        // Obtener el ID de la persona a actualizar (reemplaza con un ID válido)
        Long idReserva = 4L;
        
         // Crear una nueva persona con los datos actualizados       
        Date nuevaFechaCheckIn = parseFecha("2023-11-01");              
        Date nuevaFechaCheckOut = parseFecha("2023-11-05");
        
        Persona actualizacionPersonaUsuario = new Persona();
        actualizacionPersonaUsuario.setId(2l);
        Persona actualizacionPersonaHuesped = new Persona();
        actualizacionPersonaHuesped.setId(4l);
        
        Reserva reservaNueva = new Reserva(actualizacionPersonaUsuario,actualizacionPersonaHuesped,nuevaFechaCheckIn,nuevaFechaCheckOut, true); 
    
        // Actualizar la reserva
        Reserva reservaActualizada = reservaDao.actualizarReserva(idReserva, reservaNueva);         
    }
    
    
    private static void eliminarReserva(ReservaDao reservaDao) {
        // Obtener el ID de la reserva a eliminar (reemplaza con un ID válido)
        Long idReserva = 3L;

        // Eliminar lógicamente la reserva
        boolean eliminacionExitosa = reservaDao.eliminarReserva(idReserva);

        if (eliminacionExitosa) {
            System.out.println("Reserva eliminada lógicamente con éxito. ID: " + idReserva);
        } else {
            System.out.println("No se pudo eliminar la reserva o no existe.");
        }
    }

    
        private static void listarTodasLasReservas(ReservaDao reservaDao) {
        // Listar todas las reservas activas
        List<Reserva> reservas = reservaDao.listarTodasLasReservas();

        if (!reservas.isEmpty()) {
            System.out.println("Listado de reservas activas:");
            for (Reserva reserva : reservas) {
                System.out.println(reserva.getUsuario().getNombre() + ": " + reserva.getHuesped().getNombre() + " " + 
                        reserva.getFechaCheckIn()+" hasta " + reserva.getFechaCheckOut());
            }
        } else {
            System.out.println("No se encontraron reservas activas.");
        }
    }
    
}
    
    

