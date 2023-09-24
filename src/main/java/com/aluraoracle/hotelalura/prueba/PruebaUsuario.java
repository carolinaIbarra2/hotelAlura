package com.aluraoracle.hotelalura.prueba;

import com.aluraoracle.hotelalura.DAO.UsuarioDao;
import com.aluraoracle.hotelalura.logica.Usuario;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebaUsuario {

    public static void main(String[] args) {
        UsuarioDao usuarioDao = new UsuarioDao();

        //actualizarContrasena(usuarioDao);

        usuarioDao.cerrarEntityManagerFactory();

    }

    private static void registrarUsuario(UsuarioDao usuarioDao) {
        Usuario usuario = new Usuario("brytvasdx", "bry123", true);
        Usuario resultado = usuarioDao.registrarUsuario(usuario);

        if (resultado != null) {
            System.out.println("Usuario registrado con éxito: " + resultado.getUsername());
        } else {
            System.out.println("El usuario ya existe o no se pudo registrar.");
        }
    }

    private static void actualizarContrasena(UsuarioDao usuarioDao) {
        Long idUsuario = 1L; // ID del usuario que deseas actualizar.
        String nuevoUsername = "guimareyb"; // Nuevo nombre de usuario.
        String nuevaContraseña = "nueva_contraseña"; // Nueva contraseña.

        // Llama al método para actualizar el nombre de usuario y contraseña.
        Usuario usuarioActualizado = usuarioDao.actualizarUsuarioYContraseña(idUsuario, nuevoUsername, nuevaContraseña);

        if (usuarioActualizado != null) {
            System.out.println("Usuario y contraseña actualizados con éxito.");
        } else {
            System.out.println("No se pudo actualizar el usuario y contraseña.");
        }

        usuarioDao.cerrarEntityManagerFactory(); // Cierra el EntityManagerFactory cuando ya no lo necesitas.

    }

    private static void buscarUsuarioPorUsername(UsuarioDao usuarioDao) {
        String username = "nuevoUsuario";
        Usuario usuario = usuarioDao.buscarUsuarioPorUsername(username, null);

        if (usuario != null) {
            System.out.println("Usuario encontrado: " + usuario.getUsername());
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void login(UsuarioDao usuarioDao) {
        String username = "nuevoUsuario";
        String password = "nuevaContrasena";
        Usuario usuario = usuarioDao.login(username, password);

        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso: " + usuario.getUsername());
        } else {
            System.out.println("Inicio de sesión fallido.");
        }
    }
    
    public static void conections(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");

        // Obtiene el número de conexiones activas
        int activeConnections = (int) entityManagerFactory.unwrap(org.hibernate.SessionFactory.class)
                .getStatistics()
                .getConnectCount();

        System.out.println("Número de conexiones activas: " + activeConnections);

        // Cierra el EntityManagerFactory
        entityManagerFactory.close();
    }

}
