/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Factura;
import com.mycompany.tiendadezapatos.model.Mercancia;
import com.mycompany.tiendadezapatos.model.MercanciaHasFactura;
import com.mycompany.tiendadezapatos.model.MercanciaHasFacturaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class MercanciaHasFacturaJpaController implements Serializable {

    public MercanciaHasFacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MercanciaHasFactura mercanciaHasFactura) throws PreexistingEntityException, Exception {
        if (mercanciaHasFactura.getMercanciaHasFacturaPK() == null) {
            mercanciaHasFactura.setMercanciaHasFacturaPK(new MercanciaHasFacturaPK());
        }
        mercanciaHasFactura.getMercanciaHasFacturaPK().setFacturaIdfactura(mercanciaHasFactura.getFactura().getIdfactura());
        mercanciaHasFactura.getMercanciaHasFacturaPK().setMercanciaIdmercancia(mercanciaHasFactura.getMercancia().getIdmercancia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura factura = mercanciaHasFactura.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                mercanciaHasFactura.setFactura(factura);
            }
            Mercancia mercancia = mercanciaHasFactura.getMercancia();
            if (mercancia != null) {
                mercancia = em.getReference(mercancia.getClass(), mercancia.getIdmercancia());
                mercanciaHasFactura.setMercancia(mercancia);
            }
            em.persist(mercanciaHasFactura);
            if (factura != null) {
                factura.getMercanciaHasFacturaList().add(mercanciaHasFactura);
                factura = em.merge(factura);
            }
            if (mercancia != null) {
                mercancia.getMercanciaHasFacturaList().add(mercanciaHasFactura);
                mercancia = em.merge(mercancia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMercanciaHasFactura(mercanciaHasFactura.getMercanciaHasFacturaPK()) != null) {
                throw new PreexistingEntityException("MercanciaHasFactura " + mercanciaHasFactura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MercanciaHasFactura mercanciaHasFactura) throws NonexistentEntityException, Exception {
        mercanciaHasFactura.getMercanciaHasFacturaPK().setFacturaIdfactura(mercanciaHasFactura.getFactura().getIdfactura());
        mercanciaHasFactura.getMercanciaHasFacturaPK().setMercanciaIdmercancia(mercanciaHasFactura.getMercancia().getIdmercancia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MercanciaHasFactura persistentMercanciaHasFactura = em.find(MercanciaHasFactura.class, mercanciaHasFactura.getMercanciaHasFacturaPK());
            Factura facturaOld = persistentMercanciaHasFactura.getFactura();
            Factura facturaNew = mercanciaHasFactura.getFactura();
            Mercancia mercanciaOld = persistentMercanciaHasFactura.getMercancia();
            Mercancia mercanciaNew = mercanciaHasFactura.getMercancia();
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                mercanciaHasFactura.setFactura(facturaNew);
            }
            if (mercanciaNew != null) {
                mercanciaNew = em.getReference(mercanciaNew.getClass(), mercanciaNew.getIdmercancia());
                mercanciaHasFactura.setMercancia(mercanciaNew);
            }
            mercanciaHasFactura = em.merge(mercanciaHasFactura);
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getMercanciaHasFacturaList().remove(mercanciaHasFactura);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getMercanciaHasFacturaList().add(mercanciaHasFactura);
                facturaNew = em.merge(facturaNew);
            }
            if (mercanciaOld != null && !mercanciaOld.equals(mercanciaNew)) {
                mercanciaOld.getMercanciaHasFacturaList().remove(mercanciaHasFactura);
                mercanciaOld = em.merge(mercanciaOld);
            }
            if (mercanciaNew != null && !mercanciaNew.equals(mercanciaOld)) {
                mercanciaNew.getMercanciaHasFacturaList().add(mercanciaHasFactura);
                mercanciaNew = em.merge(mercanciaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MercanciaHasFacturaPK id = mercanciaHasFactura.getMercanciaHasFacturaPK();
                if (findMercanciaHasFactura(id) == null) {
                    throw new NonexistentEntityException("The mercanciaHasFactura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MercanciaHasFacturaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MercanciaHasFactura mercanciaHasFactura;
            try {
                mercanciaHasFactura = em.getReference(MercanciaHasFactura.class, id);
                mercanciaHasFactura.getMercanciaHasFacturaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mercanciaHasFactura with id " + id + " no longer exists.", enfe);
            }
            Factura factura = mercanciaHasFactura.getFactura();
            if (factura != null) {
                factura.getMercanciaHasFacturaList().remove(mercanciaHasFactura);
                factura = em.merge(factura);
            }
            Mercancia mercancia = mercanciaHasFactura.getMercancia();
            if (mercancia != null) {
                mercancia.getMercanciaHasFacturaList().remove(mercanciaHasFactura);
                mercancia = em.merge(mercancia);
            }
            em.remove(mercanciaHasFactura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MercanciaHasFactura> findMercanciaHasFacturaEntities() {
        return findMercanciaHasFacturaEntities(true, -1, -1);
    }

    public List<MercanciaHasFactura> findMercanciaHasFacturaEntities(int maxResults, int firstResult) {
        return findMercanciaHasFacturaEntities(false, maxResults, firstResult);
    }

    private List<MercanciaHasFactura> findMercanciaHasFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MercanciaHasFactura.class));
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

    public MercanciaHasFactura findMercanciaHasFactura(MercanciaHasFacturaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MercanciaHasFactura.class, id);
        } finally {
            em.close();
        }
    }

    public int getMercanciaHasFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MercanciaHasFactura> rt = cq.from(MercanciaHasFactura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
