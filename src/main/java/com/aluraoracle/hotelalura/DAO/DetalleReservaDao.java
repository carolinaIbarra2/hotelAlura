/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.DetalleReserva;
import com.aluraoracle.hotelalura.logica.Habitacion;
import com.aluraoracle.hotelalura.logica.Reserva;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author carit
 */
public class DetalleReservaDao {

    private final EntityManagerFactory entityManagerFactory;
    

    public DetalleReservaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public DetalleReserva registrarDetalleReserva(DetalleReserva detalleReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Habitacion habitacionExistente = entityManager.find(Habitacion.class, detalleReserva.getHabitacion().getId());
            Reserva reservaExistente = entityManager.find(Reserva.class, detalleReserva.getReserva().getId());
            // Verificar si la habitación está disponible para la fecha de reserva.

            if ((reservaExistente != null && reservaExistente.isFlag()) && (habitacionExistente != null && habitacionExistente.isFlag())) {
                if (habitacionDisponible(entityManager, detalleReserva.getHabitacion().getId(), detalleReserva.getReserva().getFechaCheckIn(), detalleReserva.getReserva().getFechaCheckOut())) {
                    entityManager.persist(detalleReserva);
                } else {
                    detalleReserva = null; // La habitación no está disponible para la fecha de reserva.
                }
            } else {
                detalleReserva = null;
            }

            entityManager.getTransaction().commit();
            return detalleReserva;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public DetalleReserva actualizarDetalleReserva(DetalleReserva detalleReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            // Utiliza el método merge para actualizar el detalle de reserva.
            detalleReserva = entityManager.merge(detalleReserva);
            entityManager.getTransaction().commit();
            return detalleReserva;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e; // Maneja la excepción según tus necesidades.
        } finally {
            entityManager.close();
        }
    }

    private boolean habitacionDisponible(EntityManager entityManager, Long idHabitacion, Date fechaCheckIn, Date fechaCheckOut) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT count(*) FROM DetalleReserva dr "
                    + "INNER JOIN dr.reserva r "
                    + "WHERE dr.habitacion.id = :habitacionId "
                    + "AND (:checkIn BETWEEN r.fechaCheckIn AND r.fechaCheckOut "
                    + "OR :checkOut BETWEEN r.fechaCheckIn AND r.fechaCheckOut)",
                    Long.class);

            query.setParameter("habitacionId", idHabitacion);
            query.setParameter("checkIn", fechaCheckIn);
            query.setParameter("checkOut", fechaCheckOut);

            Long count = query.getSingleResult();
            return count == 0;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean habitacionDisponible(Long idHabitacion, Date fechaCheckIn, Date fechaCheckOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT count(*) FROM DetalleReserva dr "
                    + "INNER JOIN dr.reserva r "
                    + "WHERE dr.habitacion.id = :habitacionId "
                    + "AND (:checkIn BETWEEN r.fechaCheckIn AND r.fechaCheckOut "
                    + "OR :checkOut BETWEEN r.fechaCheckIn AND r.fechaCheckOut)",
                    Long.class);

            query.setParameter("habitacionId", idHabitacion);
            query.setParameter("checkIn", fechaCheckIn);
            query.setParameter("checkOut", fechaCheckOut);
            
            Long count = query.getSingleResult();
            return count == 0;
        } catch (Exception e) {
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public boolean eliminarDetalleReserva(Long idDetalleReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            DetalleReserva detalleReserva = entityManager.find(DetalleReserva.class, idDetalleReserva);

            if (detalleReserva != null && detalleReserva.isFlag()) {
                // Cambia el flag a false para la eliminación lógica.
                detalleReserva.setFlag(false);
                entityManager.getTransaction().commit();
                return true; // Eliminación lógica realizada con éxito.
            }

            entityManager.getTransaction().rollback();
            return false; // No se encontró el detalle de reserva o no está activo.
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e; // Maneja la excepción según tus necesidades.
        } finally {
            entityManager.close();
        }
    }

    public List<DetalleReserva> listarDetallesReservaActivos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<DetalleReserva> query = entityManager.createQuery(
                    "SELECT dr FROM DetalleReserva dr WHERE dr.flag = true",
                    DetalleReserva.class);

            return query.getResultList();
        } catch (Exception e) {
            // Maneja la excepción según tus necesidades.
            throw e;
        } finally {
            entityManager.close();
        }
    }

    //metodo para cerrar el entityManagerFactory
    public void cerrarEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
