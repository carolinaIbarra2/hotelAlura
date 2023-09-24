/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.Habitacion;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author carit
 */
public class HabitacionDao {

    private final EntityManagerFactory entityManagerFactory;

    public HabitacionDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public Habitacion registrarHabitacion(Habitacion habitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            if (!existeHabitacion(entityManager, habitacion.getNumeroHabitacion(), null)) {
                habitacion.setFlag(true);
                entityManager.persist(habitacion);
            } else {
                // es un valor que para indicarnos que no se puede insertar esa habitacion porque ya existe en la bd
                habitacion = null;
            }
            entityManager.getTransaction().commit();
            return habitacion;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Habitacion actualizarHabitacion(Long id, Habitacion nuevaHabitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Habitacion habitacionExistente = entityManager.find(Habitacion.class, id);

            if (habitacionExistente != null && habitacionExistente.isFlag()) {
                if (!existeHabitacion(entityManager, nuevaHabitacion.getNumeroHabitacion(), id)) {
                    // actualizar
                    habitacionExistente.setTipoHabitacion(nuevaHabitacion.getTipoHabitacion());
                    habitacionExistente.setNumeroHabitacion(nuevaHabitacion.getNumeroHabitacion());
                    habitacionExistente.setEstadoHabitacion(nuevaHabitacion.getEstadoHabitacion());

                } else {
                    nuevaHabitacion = null; //no se actualizo por que ya existe el nro de habitacion en bd
                }
            } else {
                nuevaHabitacion = null;
            }

            entityManager.getTransaction().commit();
            return nuevaHabitacion;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    // true == si existe  // false == no existe
    private boolean existeHabitacion(EntityManager entityManager, String numeroHabitacion, Long id) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(h) FROM Habitacion h WHERE h.numeroHabitacion = :numeroHabitacion AND h.id != :id",
                    Long.class);
            query.setParameter("numeroHabitacion", numeroHabitacion);
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);// actualizar
            } else {
                query.setParameter("id", 0L);// registrar
            }
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean eliminarHabitacion(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Habitacion habitacion = entityManager.find(Habitacion.class, id);
            if (habitacion != null && habitacion.isFlag()) {
                habitacion.setFlag(false);
                entityManager.getTransaction().commit();
                return true;
            }

            entityManager.getTransaction().rollback();
            return false;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public List<Habitacion> listarHabitaciones() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Habitacion> query = entityManager.createQuery(
                    "SELECT h FROM Habitacion h WHERE h.flag = true",
                    Habitacion.class);
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
