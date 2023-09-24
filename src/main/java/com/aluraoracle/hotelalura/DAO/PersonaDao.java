package com.aluraoracle.hotelalura.DAO;

import com.aluraoracle.hotelalura.logica.Persona;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PersonaDao {

    private final EntityManagerFactory entityManagerFactory;

    public PersonaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
    }

    public Persona registrarPersona(Persona persona) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            if (!existeDniPasaporte(entityManager, persona.getDni(), persona.getPasaporte(),null)) {
                persona.setFlag(true); // Asegura que la persona esté activa al registrar.
                entityManager.persist(persona);
            } else {
                persona = null; // El DNI o pasaporte ya existen en la base de datos.
            }
            entityManager.getTransaction().commit();
            return persona;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public Persona actualizarPersona(Long id, Persona nuevaPersona) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Persona personaExistente = entityManager.find(Persona.class, id);

            if (personaExistente != null && personaExistente.isFlag()) {
                // Verificar si los nuevos valores de DNI y pasaporte no existen en la base de datos.
                if (!existeDniPasaporte(entityManager, nuevaPersona.getDni(), nuevaPersona.getPasaporte(),id)) {
                    // Actualizar todos los campos excepto el ID.
                    personaExistente.setDni(nuevaPersona.getDni());
                    personaExistente.setPasaporte(nuevaPersona.getPasaporte());
                    personaExistente.setNombre(nuevaPersona.getNombre());
                    personaExistente.setApellido(nuevaPersona.getApellido());
                    personaExistente.setNacionalidad(nuevaPersona.getNacionalidad());
                    personaExistente.setTelefono(nuevaPersona.getTelefono());
                    personaExistente.setFechaNacimiento(nuevaPersona.getFechaNacimiento());
                } else {
                    nuevaPersona = null; // El DNI o pasaporte ya existen en la base de datos.
                }
            } else {
                nuevaPersona = null; // La persona no existe o no está activa.
            }

            entityManager.getTransaction().commit();
            return nuevaPersona;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public boolean existeDniPasaporte(EntityManager entityManager, String dni, String pasaporte, Long id) {
        try {
            TypedQuery<Long> query = entityManager.createQuery(
                    "SELECT COUNT(p) FROM Persona p WHERE (p.dni = :dni OR p.pasaporte = :pasaporte) AND p.id != :id",
                    Long.class
            );
            query.setParameter("dni", dni);
            query.setParameter("pasaporte", pasaporte);
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);
            } else {
                query.setParameter("id", 0L);
            }
            Long count = query.getSingleResult();

            return count > 0; // Devuelve true si se encuentra el DNI o pasaporte, false si no se encuentra.
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean eliminarPersona(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try {
            Persona persona = entityManager.find(Persona.class, id);

            if (persona != null && persona.isFlag()) {
                persona.setFlag(false); // Marcar a la persona como inactiva en lugar de eliminarla físicamente.
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

    public Persona buscarPersonaPorDniOPasaporte(String dniOapasaporte, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Persona> query = entityManager.createQuery(
                    "SELECT p FROM Persona p WHERE (p.dni = :dniOapasaporte OR p.pasaporte = :dniOapasaporte) AND p.id != :id",
                    Persona.class
            );
            query.setParameter("dniOapasaporte", dniOapasaporte);
            if (Objects.nonNull(id)) {
                query.setParameter("id", id);
            } else {
                query.setParameter("id", 0L);
            }

            List<Persona> personas = query.getResultList();

            if (!personas.isEmpty()) {
                return personas.get(0); // Devuelve la primera persona si se encuentra y está activa.
            } else {
                return null; // Devuelve null si no se encuentra o está inactiva.
            }
        } finally {
            entityManager.close();
        }
    }
    

    public Persona buscarPersonaPorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            Persona persona = entityManager.find(Persona.class, id);

            if (persona != null && persona.isFlag()) {
                return persona; // Devuelve la persona si se encuentra y está activa.
            } else {
                return null; // Devuelve null si no se encuentra o está inactiva.
            }
        } finally {
            entityManager.close();
        }
    }
    
    public Persona buscarPersonaidUsuario(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Persona> query = entityManager.createQuery(
                    "SELECT p FROM Persona p WHERE p.usuario.id = :id",
                    Persona.class
            );
            query.setParameter("id", id);

            List<Persona> personas = query.getResultList();

            if (!personas.isEmpty()) {
                return personas.get(0); // Devuelve la primera persona si se encuentra y está activa.
            } else {
                return null; // Devuelve null si no se encuentra o está inactiva.
            }
        } finally {
            entityManager.close();
        }
    }

    public List<Persona> listarTodasLasPersonas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Persona> query = entityManager.createQuery(
                    "SELECT p FROM Persona p WHERE p.flag = true",
                    Persona.class
            );
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    public List<Persona> listarTodasLasPersonasConUsuarios() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<Persona> query = entityManager.createQuery(
                    "SELECT p FROM Persona p WHERE p.flag = true AND p.usuario.id != null",
                    Persona.class
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