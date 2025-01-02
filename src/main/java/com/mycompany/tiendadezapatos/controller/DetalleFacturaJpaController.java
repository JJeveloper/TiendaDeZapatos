/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.DetalleFactura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Factura;
import com.mycompany.tiendadezapatos.model.Mercancia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class DetalleFacturaJpaController implements Serializable {

    public DetalleFacturaJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public DetalleFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleFactura detalleFactura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura facturaIdfactura = detalleFactura.getFacturaIdfactura();
            if (facturaIdfactura != null) {
                facturaIdfactura = em.getReference(facturaIdfactura.getClass(), facturaIdfactura.getIdfactura());
                detalleFactura.setFacturaIdfactura(facturaIdfactura);
            }
            Mercancia mercanciaIdmercancia = detalleFactura.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia = em.getReference(mercanciaIdmercancia.getClass(), mercanciaIdmercancia.getIdmercancia());
                detalleFactura.setMercanciaIdmercancia(mercanciaIdmercancia);
            }
            em.persist(detalleFactura);
            if (facturaIdfactura != null) {
                facturaIdfactura.getDetalleFacturaList().add(detalleFactura);
                facturaIdfactura = em.merge(facturaIdfactura);
            }
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetalleFacturaList().add(detalleFactura);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleFactura detalleFactura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleFactura persistentDetalleFactura = em.find(DetalleFactura.class, detalleFactura.getIddetalleFactura());
            Factura facturaIdfacturaOld = persistentDetalleFactura.getFacturaIdfactura();
            Factura facturaIdfacturaNew = detalleFactura.getFacturaIdfactura();
            Mercancia mercanciaIdmercanciaOld = persistentDetalleFactura.getMercanciaIdmercancia();
            Mercancia mercanciaIdmercanciaNew = detalleFactura.getMercanciaIdmercancia();
            if (facturaIdfacturaNew != null) {
                facturaIdfacturaNew = em.getReference(facturaIdfacturaNew.getClass(), facturaIdfacturaNew.getIdfactura());
                detalleFactura.setFacturaIdfactura(facturaIdfacturaNew);
            }
            if (mercanciaIdmercanciaNew != null) {
                mercanciaIdmercanciaNew = em.getReference(mercanciaIdmercanciaNew.getClass(), mercanciaIdmercanciaNew.getIdmercancia());
                detalleFactura.setMercanciaIdmercancia(mercanciaIdmercanciaNew);
            }
            detalleFactura = em.merge(detalleFactura);
            if (facturaIdfacturaOld != null && !facturaIdfacturaOld.equals(facturaIdfacturaNew)) {
                facturaIdfacturaOld.getDetalleFacturaList().remove(detalleFactura);
                facturaIdfacturaOld = em.merge(facturaIdfacturaOld);
            }
            if (facturaIdfacturaNew != null && !facturaIdfacturaNew.equals(facturaIdfacturaOld)) {
                facturaIdfacturaNew.getDetalleFacturaList().add(detalleFactura);
                facturaIdfacturaNew = em.merge(facturaIdfacturaNew);
            }
            if (mercanciaIdmercanciaOld != null && !mercanciaIdmercanciaOld.equals(mercanciaIdmercanciaNew)) {
                mercanciaIdmercanciaOld.getDetalleFacturaList().remove(detalleFactura);
                mercanciaIdmercanciaOld = em.merge(mercanciaIdmercanciaOld);
            }
            if (mercanciaIdmercanciaNew != null && !mercanciaIdmercanciaNew.equals(mercanciaIdmercanciaOld)) {
                mercanciaIdmercanciaNew.getDetalleFacturaList().add(detalleFactura);
                mercanciaIdmercanciaNew = em.merge(mercanciaIdmercanciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleFactura.getIddetalleFactura();
                if (findDetalleFactura(id) == null) {
                    throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.");
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
            DetalleFactura detalleFactura;
            try {
                detalleFactura = em.getReference(DetalleFactura.class, id);
                detalleFactura.getIddetalleFactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleFactura with id " + id + " no longer exists.", enfe);
            }
            Factura facturaIdfactura = detalleFactura.getFacturaIdfactura();
            if (facturaIdfactura != null) {
                facturaIdfactura.getDetalleFacturaList().remove(detalleFactura);
                facturaIdfactura = em.merge(facturaIdfactura);
            }
            Mercancia mercanciaIdmercancia = detalleFactura.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetalleFacturaList().remove(detalleFactura);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            em.remove(detalleFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleFactura> findDetalleFacturaEntities() {
        return findDetalleFacturaEntities(true, -1, -1);
    }

    public List<DetalleFactura> findDetalleFacturaEntities(int maxResults, int firstResult) {
        return findDetalleFacturaEntities(false, maxResults, firstResult);
    }

    private List<DetalleFactura> findDetalleFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleFactura.class));
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

    public DetalleFactura findDetalleFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleFactura> rt = cq.from(DetalleFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
