package com.aluraoracle.hotelalura.prueba;

import com.aluraoracle.hotelalura.DAO.HabitacionDao;
import com.aluraoracle.hotelalura.logica.EstadoHabitacion;
import com.aluraoracle.hotelalura.logica.Habitacion;
import com.aluraoracle.hotelalura.logica.TipoHabitacion;
import java.util.List;

public class PruebaHabitacion {

    public static void main(String[] args) {

        HabitacionDao habitacionDao = new HabitacionDao();
        listarHabitaciones(habitacionDao);
        habitacionDao.cerrarEntityManagerFactory();

    }

    private static void registrarHabitacion(HabitacionDao habitacionDao) {

        TipoHabitacion tipoHabitacion = new TipoHabitacion("doble", 100, true);
        tipoHabitacion.setId(1L);
        Habitacion nuevaHabitacion = new Habitacion(tipoHabitacion, "201", EstadoHabitacion.LIBRE, true);

        //Registrar la habitacion
        Habitacion habitacionRegistrada = habitacionDao.registrarHabitacion(nuevaHabitacion);

        if (habitacionRegistrada != null) {
            System.out.println("Habitación registrada con éxito. ID: " + habitacionRegistrada.getId());
        } else {
            System.out.println("La habitacion no se pudo registrar.");
        }
    }

    private static void actualizarHabitacion(HabitacionDao habitacionDao) {
        // Obtener el ID de la habitacion a actualizar (reemplaza con un ID válido)
        Long idHabitacion = 1L;

        // Crear una nueva Habitacion con los datos actualizados
        TipoHabitacion tipoHabitacionNuevo = new TipoHabitacion("doble", 100, true);
        tipoHabitacionNuevo.setId(1L);
        Habitacion habitacionNueva = new Habitacion(tipoHabitacionNuevo, "304", EstadoHabitacion.LIBRE, true);

        Habitacion habitacionActualizada = habitacionDao.actualizarHabitacion(idHabitacion, habitacionNueva);

        if (habitacionActualizada != null) {
            System.out.println("Habitación actualizada con éxito. ID: " + habitacionActualizada.getId());
        } else {
            System.out.println("La habitacion no se pudo registrar.");
        }
    }

    private static void eliminarHabitacion(HabitacionDao habitacionDao) {

        Long idHabitacion = 1L; // Obtener el ID de la habitacion a eliminar (reemplaza con un ID válido)

        boolean eliminacionExitosa = habitacionDao.eliminarHabitacion(idHabitacion);

        if (eliminacionExitosa) {
            System.out.println("Habitación eliminada lógicamente con éxito. ID: " + idHabitacion);
        } else {
            System.out.println("No se pudo eliminar la habitación o no existe.");
        }
    }

    private static void listarHabitaciones(HabitacionDao habitacionDao) {
        // Listar todas las habitaciones activas
        List<Habitacion> habitaciones = habitacionDao.listarHabitaciones();

        if (!habitaciones.isEmpty()) {
            System.out.println("Listado de habitaciones activas:");
            for (Habitacion habitacion : habitaciones) {
                System.out.println(habitacion.getId() + ": " + habitacion.getTipoHabitacion() + " " + habitacion.getNumeroHabitacion() + " " + habitacion.getEstadoHabitacion());
            }
        } else {
            System.out.println("No se encontraron habitaciones activas.");
        }
    }

}
