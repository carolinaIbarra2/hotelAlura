/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraoracle.hotelalura.prueba;

import com.aluraoracle.hotelalura.DAO.PersonaDao;
import com.aluraoracle.hotelalura.logica.EstadoHabitacion;
import com.aluraoracle.hotelalura.logica.Persona;
import com.aluraoracle.hotelalura.logica.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guima
 */
public class PersonaPrueba {

    public static void main(String[] args) {
        //PersonaDao personaDao = new PersonaDao();

        // Registrar una nueva persona
        //listarTodasLasPersonas(personaDao);

        // Cerrar el EntityManagerFactory
        //personaDao.cerrarEntityManagerFactory();
        
        System.out.println(EstadoHabitacion.LIBRE);
    }

    private static void registrarPersona(PersonaDao personaDao) {
        // Crear una nueva persona
        Usuario usuario = new Usuario("nuevoUsuario", "contrasena", true);
        usuario.setId(1l);
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Persona nuevaPersona = new Persona("12345678", "AB123456", "Nombre", "Apellido",
                "Nacionalidad", "1234567890", fechaNacimiento, usuario, true);

        // Registrar la persona
        Persona personaRegistrada = personaDao.registrarPersona(nuevaPersona);

        if (personaRegistrada != null) {
            System.out.println("Persona registrada con éxito. ID: " + personaRegistrada.getId());
        } else {
            System.out.println("La persona no se pudo registrar.");
        }
    }

    private static void actualizarPersona(PersonaDao personaDao) {
        // Obtener el ID de la persona a actualizar (reemplaza con un ID válido)
        Long idPersona = 1L;

        // Crear una nueva persona con los datos actualizados
        Date nuevaFechaNacimiento = null;
        try {
            nuevaFechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse("1985-05-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Persona nuevaPersona = new Persona("87654321", "CD987654", "NuevoNombre", "NuevoApellido",
                "NuevaNacionalidad", "9876543210", nuevaFechaNacimiento, null, true);

        // Actualizar la persona
        Persona personaActualizada = personaDao.actualizarPersona(idPersona, nuevaPersona);

        if (personaActualizada != null) {
            System.out.println("Persona actualizada con éxito. ID: " + personaActualizada.getId());
        } else {
            System.out.println("No se pudo actualizar la persona.");
        }
    }

    private static void eliminarPersona(PersonaDao personaDao) {
        // Obtener el ID de la persona a eliminar (reemplaza con un ID válido)
        Long idPersona = 1L;

        // Eliminar lógicamente la persona
        boolean eliminacionExitosa = personaDao.eliminarPersona(idPersona);

        if (eliminacionExitosa) {
            System.out.println("Persona eliminada lógicamente con éxito. ID: " + idPersona);
        } else {
            System.out.println("No se pudo eliminar la persona o no existe.");
        }
    }

    private static void buscarPersonaPorDniOPasaporte(PersonaDao personaDao) {
        // DNI o pasaporte a buscar (reemplaza con un valor válido)
        String dniOapasaporte = "12345678";

        // Buscar persona por DNI o pasaporte
        Persona personaEncontrada = personaDao.buscarPersonaPorDniOPasaporte(dniOapasaporte, null);

        if (personaEncontrada != null) {
            System.out.println("Persona encontrada por DNI o pasaporte: " + personaEncontrada.getNombre() + " " + personaEncontrada.getApellido());
        } else {
            System.out.println("Persona no encontrada por DNI o pasaporte: " + dniOapasaporte);
        }
    }

    private static void buscarPersonaPorId(PersonaDao personaDao) {
        // ID de la persona a buscar (reemplaza con un ID válido)
        Long idPersona = 1L;

        // Buscar persona por ID
        Persona personaEncontrada = personaDao.buscarPersonaPorId(idPersona);

        if (personaEncontrada != null) {
            System.out.println("Persona encontrada por ID: " + personaEncontrada.getNombre() + " " + personaEncontrada.getApellido());
        } else {
            System.out.println("Persona no encontrada por ID: " + idPersona);
        }
    }

    private static void listarTodasLasPersonas(PersonaDao personaDao) {
        // Listar todas las personas activas
        List<Persona> personas = personaDao.listarTodasLasPersonas();

        if (!personas.isEmpty()) {
            System.out.println("Listado de personas activas:");
            for (Persona persona : personas) {
                System.out.println(persona.getId() + ": " + persona.getNombre() + " " + persona.getApellido());
            }
        } else {
            System.out.println("No se encontraron personas activas.");
        }
    }

}