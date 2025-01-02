/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.Actualizarprecio;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Detallepedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class ActualizarprecioJpaController implements Serializable {

    public ActualizarprecioJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public ActualizarprecioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actualizarprecio actualizarprecio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido detallepedidoIddetallepedido = actualizarprecio.getDetallepedidoIddetallepedido();
            if (detallepedidoIddetallepedido != null) {
                detallepedidoIddetallepedido = em.getReference(detallepedidoIddetallepedido.getClass(), detallepedidoIddetallepedido.getIddetallepedido());
                actualizarprecio.setDetallepedidoIddetallepedido(detallepedidoIddetallepedido);
            }
            em.persist(actualizarprecio);
            if (detallepedidoIddetallepedido != null) {
                detallepedidoIddetallepedido.getActualizarprecioList().add(actualizarprecio);
                detallepedidoIddetallepedido = em.merge(detallepedidoIddetallepedido);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actualizarprecio actualizarprecio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actualizarprecio persistentActualizarprecio = em.find(Actualizarprecio.class, actualizarprecio.getActualizarprecio());
            Detallepedido detallepedidoIddetallepedidoOld = persistentActualizarprecio.getDetallepedidoIddetallepedido();
            Detallepedido detallepedidoIddetallepedidoNew = actualizarprecio.getDetallepedidoIddetallepedido();
            if (detallepedidoIddetallepedidoNew != null) {
                detallepedidoIddetallepedidoNew = em.getReference(detallepedidoIddetallepedidoNew.getClass(), detallepedidoIddetallepedidoNew.getIddetallepedido());
                actualizarprecio.setDetallepedidoIddetallepedido(detallepedidoIddetallepedidoNew);
            }
            actualizarprecio = em.merge(actualizarprecio);
            if (detallepedidoIddetallepedidoOld != null && !detallepedidoIddetallepedidoOld.equals(detallepedidoIddetallepedidoNew)) {
                detallepedidoIddetallepedidoOld.getActualizarprecioList().remove(actualizarprecio);
                detallepedidoIddetallepedidoOld = em.merge(detallepedidoIddetallepedidoOld);
            }
            if (detallepedidoIddetallepedidoNew != null && !detallepedidoIddetallepedidoNew.equals(detallepedidoIddetallepedidoOld)) {
                detallepedidoIddetallepedidoNew.getActualizarprecioList().add(actualizarprecio);
                detallepedidoIddetallepedidoNew = em.merge(detallepedidoIddetallepedidoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = actualizarprecio.getActualizarprecio();
                if (findActualizarprecio(id) == null) {
                    throw new NonexistentEntityException("The actualizarprecio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actualizarprecio actualizarprecio;
            try {
                actualizarprecio = em.getReference(Actualizarprecio.class, id);
                actualizarprecio.getActualizarprecio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actualizarprecio with id " + id + " no longer exists.", enfe);
            }
            Detallepedido detallepedidoIddetallepedido = actualizarprecio.getDetallepedidoIddetallepedido();
            if (detallepedidoIddetallepedido != null) {
                detallepedidoIddetallepedido.getActualizarprecioList().remove(actualizarprecio);
                detallepedidoIddetallepedido = em.merge(detallepedidoIddetallepedido);
            }
            em.remove(actualizarprecio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actualizarprecio> findActualizarprecioEntities() {
        return findActualizarprecioEntities(true, -1, -1);
    }

    public List<Actualizarprecio> findActualizarprecioEntities(int maxResults, int firstResult) {
        return findActualizarprecioEntities(false, maxResults, firstResult);
    }

    private List<Actualizarprecio> findActualizarprecioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actualizarprecio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Actualizarprecio findActualizarprecio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actualizarprecio.class, id);
        } finally {
            em.close();
        }
    }

    public int getActualizarprecioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actualizarprecio> rt = cq.from(Actualizarprecio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
