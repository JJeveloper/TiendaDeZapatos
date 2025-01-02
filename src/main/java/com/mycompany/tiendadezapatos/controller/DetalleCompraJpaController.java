/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.CompraMercancia;
import com.mycompany.tiendadezapatos.model.DetalleCompra;
import com.mycompany.tiendadezapatos.model.Mercancia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class DetalleCompraJpaController implements Serializable {

    public DetalleCompraJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public DetalleCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleCompra detalleCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompraMercancia compraMercanciaIdcompraMercancia = detalleCompra.getCompraMercanciaIdcompraMercancia();
            if (compraMercanciaIdcompraMercancia != null) {
                compraMercanciaIdcompraMercancia = em.getReference(compraMercanciaIdcompraMercancia.getClass(), compraMercanciaIdcompraMercancia.getIdcompraMercancia());
                detalleCompra.setCompraMercanciaIdcompraMercancia(compraMercanciaIdcompraMercancia);
            }
            Mercancia mercanciaIdmercancia = detalleCompra.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia = em.getReference(mercanciaIdmercancia.getClass(), mercanciaIdmercancia.getIdmercancia());
                detalleCompra.setMercanciaIdmercancia(mercanciaIdmercancia);
            }
            em.persist(detalleCompra);
            if (compraMercanciaIdcompraMercancia != null) {
                compraMercanciaIdcompraMercancia.getDetalleCompraList().add(detalleCompra);
                compraMercanciaIdcompraMercancia = em.merge(compraMercanciaIdcompraMercancia);
            }
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetalleCompraList().add(detalleCompra);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleCompra detalleCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleCompra persistentDetalleCompra = em.find(DetalleCompra.class, detalleCompra.getIddetalleCompra());
            CompraMercancia compraMercanciaIdcompraMercanciaOld = persistentDetalleCompra.getCompraMercanciaIdcompraMercancia();
            CompraMercancia compraMercanciaIdcompraMercanciaNew = detalleCompra.getCompraMercanciaIdcompraMercancia();
            Mercancia mercanciaIdmercanciaOld = persistentDetalleCompra.getMercanciaIdmercancia();
            Mercancia mercanciaIdmercanciaNew = detalleCompra.getMercanciaIdmercancia();
            if (compraMercanciaIdcompraMercanciaNew != null) {
                compraMercanciaIdcompraMercanciaNew = em.getReference(compraMercanciaIdcompraMercanciaNew.getClass(), compraMercanciaIdcompraMercanciaNew.getIdcompraMercancia());
                detalleCompra.setCompraMercanciaIdcompraMercancia(compraMercanciaIdcompraMercanciaNew);
            }
            if (mercanciaIdmercanciaNew != null) {
                mercanciaIdmercanciaNew = em.getReference(mercanciaIdmercanciaNew.getClass(), mercanciaIdmercanciaNew.getIdmercancia());
                detalleCompra.setMercanciaIdmercancia(mercanciaIdmercanciaNew);
            }
            detalleCompra = em.merge(detalleCompra);
            if (compraMercanciaIdcompraMercanciaOld != null && !compraMercanciaIdcompraMercanciaOld.equals(compraMercanciaIdcompraMercanciaNew)) {
                compraMercanciaIdcompraMercanciaOld.getDetalleCompraList().remove(detalleCompra);
                compraMercanciaIdcompraMercanciaOld = em.merge(compraMercanciaIdcompraMercanciaOld);
            }
            if (compraMercanciaIdcompraMercanciaNew != null && !compraMercanciaIdcompraMercanciaNew.equals(compraMercanciaIdcompraMercanciaOld)) {
                compraMercanciaIdcompraMercanciaNew.getDetalleCompraList().add(detalleCompra);
                compraMercanciaIdcompraMercanciaNew = em.merge(compraMercanciaIdcompraMercanciaNew);
            }
            if (mercanciaIdmercanciaOld != null && !mercanciaIdmercanciaOld.equals(mercanciaIdmercanciaNew)) {
                mercanciaIdmercanciaOld.getDetalleCompraList().remove(detalleCompra);
                mercanciaIdmercanciaOld = em.merge(mercanciaIdmercanciaOld);
            }
            if (mercanciaIdmercanciaNew != null && !mercanciaIdmercanciaNew.equals(mercanciaIdmercanciaOld)) {
                mercanciaIdmercanciaNew.getDetalleCompraList().add(detalleCompra);
                mercanciaIdmercanciaNew = em.merge(mercanciaIdmercanciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleCompra.getIddetalleCompra();
                if (findDetalleCompra(id) == null) {
                    throw new NonexistentEntityException("The detalleCompra with id " + id + " no longer exists.");
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
            DetalleCompra detalleCompra;
            try {
                detalleCompra = em.getReference(DetalleCompra.class, id);
                detalleCompra.getIddetalleCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleCompra with id " + id + " no longer exists.", enfe);
            }
            CompraMercancia compraMercanciaIdcompraMercancia = detalleCompra.getCompraMercanciaIdcompraMercancia();
            if (compraMercanciaIdcompraMercancia != null) {
                compraMercanciaIdcompraMercancia.getDetalleCompraList().remove(detalleCompra);
                compraMercanciaIdcompraMercancia = em.merge(compraMercanciaIdcompraMercancia);
            }
            Mercancia mercanciaIdmercancia = detalleCompra.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetalleCompraList().remove(detalleCompra);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            em.remove(detalleCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleCompra> findDetalleCompraEntities() {
        return findDetalleCompraEntities(true, -1, -1);
    }

    public List<DetalleCompra> findDetalleCompraEntities(int maxResults, int firstResult) {
        return findDetalleCompraEntities(false, maxResults, firstResult);
    }

    private List<DetalleCompra> findDetalleCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleCompra.class));
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

    public DetalleCompra findDetalleCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleCompra> rt = cq.from(DetalleCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
