package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.Usuario;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UsuarioDao {

    private final EntityManagerFactory entityManagerFactory;  //interactuar con la base de datos

    public UsuarioDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public Usuario registrarUsuario(Usuario usuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            if (!existeUsername(entityManager, usuario.getUsername(), null)) {
                usuario.setFlag(true); // Asegura que el usuario esté activo al registrar.
                entityManager.persist(usuario);
            } else {
                usuario = null;// El username ya existe en la base de datos.
            }
            entityManager.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Usuario actualizarUsuarioYContraseña(Long id, String nuevoUsername, String nuevaContraseña) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Usuario usuario = entityManager.find(Usuario.class, id);
            if (usuario != null && usuario.isFlag()) {
                // Verificar si el nuevo nombre de usuario ya existe en la base de datos.
                if (!existeUsername(entityManager, nuevoUsername,id)) {
                    usuario.setUsername(nuevoUsername);
                    usuario.setPassword(nuevaContraseña);
                } else {
                    usuario = null;// El username ya existe en la base de datos.
                }
            }
            entityManager.getTransaction().commit();
            return usuario;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    private boolean existeUsername(EntityManager entityManager, String username, Long id) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.username = :username AND u.id != :id",
                    Long.class
            );
            query.setParameter("username", username); 
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);
            } else {
                query.setParameter("id", 0L);
            }
            Long count = query.getSingleResult();

            System.out.println(count > 0);
            return count > 0; // Devuelve true si se encuentra el username, false si no se encuentra.
        } catch (Exception e) {
            throw e;
        }
    }
    
    public boolean eliminarUsuario(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Usuario usuario = entityManager.find(Usuario.class, id);

            if (usuario != null && usuario.isFlag()) {
                usuario.setFlag(false); // Marcar a la persona como inactiva en lugar de eliminarla físicamente.
                entityManager.getTransaction().commit();
                return true; // La eliminación lógica se realizó con éxito.
            }

            entityManager.getTransaction().rollback();
            return false; // No se encontró a la persona con el ID proporcionado o no está activa.
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Usuario buscarUsuarioPorUsername(String username, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :username AND u.id != :id",
                    Usuario.class
            );
            query.setParameter("username", username);
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);
            } else {
                query.setParameter("id", 0L);
            }
            List<Usuario> usuarios = query.getResultList();

            if (usuarios.size() == 1) {
                return usuarios.get(0); // Devuelve el usuario si se encuentra y está activo.
            } else {
                return null; // Devuelve null si no se encuentra o está inactivo.
            }
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Usuario login(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Usuario> query = entityManager.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :username AND u.password = :password AND u.flag = 1",
                    Usuario.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<Usuario> usuarios = query.getResultList();

            if (usuarios.size() == 1) {
                return usuarios.get(0); // Devuelve el usuario si las credenciales son correctas y el usuario está activo.
            } else {
                return null; // Devuelve null si las credenciales son incorrectas o el usuario está inactivo.
            }
        } finally {
            entityManager.close();
        }
    }

    //metodo para cerrar el entityManagerFactory
    public void cerrarEntityManagerFactory() {
        entityManagerFactory.close();
    }

}
