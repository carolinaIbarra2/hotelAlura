package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.Reserva;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ReservaDao {

    private final EntityManagerFactory entityManagerFactory;

    public ReservaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public Reserva registrarReserva(Reserva reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            reserva.setFlag(true);
            entityManager.persist(reserva);
            entityManager.getTransaction().commit();
            return reserva;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Reserva actualizarReserva(Long id, Reserva nuevaReserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Reserva reservaExistente = entityManager.find(Reserva.class, id);

            if (reservaExistente != null && reservaExistente.isFlag()) {
                reservaExistente.setUsuario(nuevaReserva.getUsuario());
                reservaExistente.setHuesped(nuevaReserva.getHuesped());
                reservaExistente.setFechaCheckIn(nuevaReserva.getFechaCheckIn());
                reservaExistente.setFechaCheckOut(nuevaReserva.getFechaCheckOut());
            } else {
                nuevaReserva = null;
            }
            entityManager.getTransaction().commit();
            return nuevaReserva;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public boolean eliminarReserva(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Reserva reserva = entityManager.find(Reserva.class, id);
            if (reserva != null && reserva.isFlag()) {
                reserva.setFlag(false);
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

    public List<Reserva> listarTodasLasReservas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Reserva> query = entityManager.createQuery(
                    "SELECT r FROM Reserva r WHERE r.flag = true",
                    Reserva.class);
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
