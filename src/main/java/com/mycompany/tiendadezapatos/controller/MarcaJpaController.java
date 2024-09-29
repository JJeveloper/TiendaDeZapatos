/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.Marca;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class MarcaJpaController implements Serializable {

    public MarcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorIdproveedor = marca.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor = em.getReference(proveedorIdproveedor.getClass(), proveedorIdproveedor.getIdproveedor());
                marca.setProveedorIdproveedor(proveedorIdproveedor);
            }
            em.persist(marca);
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getMarcaList().add(marca);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marca marca) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca persistentMarca = em.find(Marca.class, marca.getIdmarca());
            Proveedor proveedorIdproveedorOld = persistentMarca.getProveedorIdproveedor();
            Proveedor proveedorIdproveedorNew = marca.getProveedorIdproveedor();
            if (proveedorIdproveedorNew != null) {
                proveedorIdproveedorNew = em.getReference(proveedorIdproveedorNew.getClass(), proveedorIdproveedorNew.getIdproveedor());
                marca.setProveedorIdproveedor(proveedorIdproveedorNew);
            }
            marca = em.merge(marca);
            if (proveedorIdproveedorOld != null && !proveedorIdproveedorOld.equals(proveedorIdproveedorNew)) {
                proveedorIdproveedorOld.getMarcaList().remove(marca);
                proveedorIdproveedorOld = em.merge(proveedorIdproveedorOld);
            }
            if (proveedorIdproveedorNew != null && !proveedorIdproveedorNew.equals(proveedorIdproveedorOld)) {
                proveedorIdproveedorNew.getMarcaList().add(marca);
                proveedorIdproveedorNew = em.merge(proveedorIdproveedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marca.getIdmarca();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getIdmarca();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            Proveedor proveedorIdproveedor = marca.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getMarcaList().remove(marca);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            em.remove(marca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
