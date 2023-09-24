package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.TipoHabitacion;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TipoHabitacionDao {

    private final EntityManagerFactory entityManagerFactory;

    public TipoHabitacionDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public TipoHabitacion registrarTipoHabitacion(TipoHabitacion tipoHabitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            // Verificar si el nombre del tipo de habitación ya existe en la base de datos
            if (!existeTipoHabitacion(entityManager, tipoHabitacion.getNombre(), null)) {
                entityManager.persist(tipoHabitacion);
            } else {
                tipoHabitacion = null; // El nombre ya existe en la base de datos.
            }

            entityManager.getTransaction().commit();
            return tipoHabitacion;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public TipoHabitacion actualizarTipoHabitacion(Long id, String nuevoNombre, double nuevoPrecio) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TipoHabitacion tipoHabitacionExistente = entityManager.find(TipoHabitacion.class, id);

            if (tipoHabitacionExistente != null) {
                // Verificar si el nuevo nombre del tipo de habitación ya existe en la base de datos
                if (!existeTipoHabitacion(entityManager, nuevoNombre, id)) {
                    tipoHabitacionExistente.setNombre(nuevoNombre);
                    tipoHabitacionExistente.setPrecio(nuevoPrecio);
                } else {
                    tipoHabitacionExistente = null; // El nuevo nombre ya existe en la base de datos.
                }
            }

            entityManager.getTransaction().commit();
            return tipoHabitacionExistente;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public boolean existeTipoHabitacion(EntityManager entityManager, String nombre, Long id) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(th) FROM TipoHabitacion th WHERE th.nombre = :nombre AND th.id != :id",
                    Long.class
            );
            query.setParameter("nombre", nombre);
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);
            } else {
                query.setParameter("id", 0L);
            }
            Long count = query.getSingleResult();

            return count > 0; // Devuelve true si se encuentra el nombre, false si no se encuentra.
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean eliminarTipoHabitacion(Long idTipoHabitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            TipoHabitacion tipoHabitacion = entityManager.find(TipoHabitacion.class, idTipoHabitacion);

            if (tipoHabitacion != null && tipoHabitacion.isFlag()) {
                tipoHabitacion.setFlag(false); // Marcar el tipo de habitación como inactivo en lugar de eliminarlo físicamente.
                entityManager.getTransaction().commit();
                return true; // La eliminación lógica se realizó con éxito.
            }

            entityManager.getTransaction().rollback();
            return false; // No se encontró el tipo de habitación con el ID proporcionado o no está activo.
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public TipoHabitacion buscarTipoHabitacionPorNombre(String nombre) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<TipoHabitacion> query = entityManager.createQuery(
                    "SELECT th FROM TipoHabitacion th WHERE th.nombre = :nombre AND th.flag = true",
                    TipoHabitacion.class
            );
            query.setParameter("nombre", nombre);

            List<TipoHabitacion> tiposHabitacion = query.getResultList();

            if (!tiposHabitacion.isEmpty()) {
                return tiposHabitacion.get(0); // Devuelve el tipo de habitación si se encuentra y está activo.
            } else {
                return null; // Devuelve null si no se encuentra o está inactivo.
            }
        } finally {
            entityManager.close();
        }
    }

    public TipoHabitacion buscarTipoHabitacionPorId(Long idTipoHabitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TipoHabitacion tipoHabitacion = entityManager.find(TipoHabitacion.class, idTipoHabitacion);

            if (tipoHabitacion != null && tipoHabitacion.isFlag()) {
                return tipoHabitacion; // Devuelve el tipo de habitación si se encuentra y está activo.
            } else {
                return null; // Devuelve null si no se encuentra o está inactivo.
            }
        } finally {
            entityManager.close();
        }
    }

    public List<TipoHabitacion> listarTodasLasTipoHabitacion() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<TipoHabitacion> query = entityManager.createQuery(
                    "SELECT th FROM TipoHabitacion th WHERE th.flag = true",
                    TipoHabitacion.class
            );

            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    //metodo para cerrar el entityManagerFactory
    public void cerrarEntityManagerFactory() {
        entityManagerFactory.close();
    }

}
