/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.IllegalOrphanException;
import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.CompraMercancia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Proveedor;
import com.mycompany.tiendadezapatos.model.DetalleCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class CompraMercanciaJpaController implements Serializable {

    public CompraMercanciaJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public CompraMercanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompraMercancia compraMercancia) {
        if (compraMercancia.getDetalleCompraList() == null) {
            compraMercancia.setDetalleCompraList(new ArrayList<DetalleCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorIdproveedor = compraMercancia.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor = em.getReference(proveedorIdproveedor.getClass(), proveedorIdproveedor.getIdproveedor());
                compraMercancia.setProveedorIdproveedor(proveedorIdproveedor);
            }
            List<DetalleCompra> attachedDetalleCompraList = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListDetalleCompraToAttach : compraMercancia.getDetalleCompraList()) {
                detalleCompraListDetalleCompraToAttach = em.getReference(detalleCompraListDetalleCompraToAttach.getClass(), detalleCompraListDetalleCompraToAttach.getIddetalleCompra());
                attachedDetalleCompraList.add(detalleCompraListDetalleCompraToAttach);
            }
            compraMercancia.setDetalleCompraList(attachedDetalleCompraList);
            em.persist(compraMercancia);
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getCompraMercanciaList().add(compraMercancia);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            for (DetalleCompra detalleCompraListDetalleCompra : compraMercancia.getDetalleCompraList()) {
                CompraMercancia oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListDetalleCompra = detalleCompraListDetalleCompra.getCompraMercanciaIdcompraMercancia();
                detalleCompraListDetalleCompra.setCompraMercanciaIdcompraMercancia(compraMercancia);
                detalleCompraListDetalleCompra = em.merge(detalleCompraListDetalleCompra);
                if (oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListDetalleCompra != null) {
                    oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListDetalleCompra.getDetalleCompraList().remove(detalleCompraListDetalleCompra);
                    oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListDetalleCompra = em.merge(oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListDetalleCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompraMercancia compraMercancia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompraMercancia persistentCompraMercancia = em.find(CompraMercancia.class, compraMercancia.getIdcompraMercancia());
            Proveedor proveedorIdproveedorOld = persistentCompraMercancia.getProveedorIdproveedor();
            Proveedor proveedorIdproveedorNew = compraMercancia.getProveedorIdproveedor();
            List<DetalleCompra> detalleCompraListOld = persistentCompraMercancia.getDetalleCompraList();
            List<DetalleCompra> detalleCompraListNew = compraMercancia.getDetalleCompraList();
            List<String> illegalOrphanMessages = null;
            for (DetalleCompra detalleCompraListOldDetalleCompra : detalleCompraListOld) {
                if (!detalleCompraListNew.contains(detalleCompraListOldDetalleCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCompra " + detalleCompraListOldDetalleCompra + " since its compraMercanciaIdcompraMercancia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorIdproveedorNew != null) {
                proveedorIdproveedorNew = em.getReference(proveedorIdproveedorNew.getClass(), proveedorIdproveedorNew.getIdproveedor());
                compraMercancia.setProveedorIdproveedor(proveedorIdproveedorNew);
            }
            List<DetalleCompra> attachedDetalleCompraListNew = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListNewDetalleCompraToAttach : detalleCompraListNew) {
                detalleCompraListNewDetalleCompraToAttach = em.getReference(detalleCompraListNewDetalleCompraToAttach.getClass(), detalleCompraListNewDetalleCompraToAttach.getIddetalleCompra());
                attachedDetalleCompraListNew.add(detalleCompraListNewDetalleCompraToAttach);
            }
            detalleCompraListNew = attachedDetalleCompraListNew;
            compraMercancia.setDetalleCompraList(detalleCompraListNew);
            compraMercancia = em.merge(compraMercancia);
            if (proveedorIdproveedorOld != null && !proveedorIdproveedorOld.equals(proveedorIdproveedorNew)) {
                proveedorIdproveedorOld.getCompraMercanciaList().remove(compraMercancia);
                proveedorIdproveedorOld = em.merge(proveedorIdproveedorOld);
            }
            if (proveedorIdproveedorNew != null && !proveedorIdproveedorNew.equals(proveedorIdproveedorOld)) {
                proveedorIdproveedorNew.getCompraMercanciaList().add(compraMercancia);
                proveedorIdproveedorNew = em.merge(proveedorIdproveedorNew);
            }
            for (DetalleCompra detalleCompraListNewDetalleCompra : detalleCompraListNew) {
                if (!detalleCompraListOld.contains(detalleCompraListNewDetalleCompra)) {
                    CompraMercancia oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra = detalleCompraListNewDetalleCompra.getCompraMercanciaIdcompraMercancia();
                    detalleCompraListNewDetalleCompra.setCompraMercanciaIdcompraMercancia(compraMercancia);
                    detalleCompraListNewDetalleCompra = em.merge(detalleCompraListNewDetalleCompra);
                    if (oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra != null && !oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra.equals(compraMercancia)) {
                        oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra.getDetalleCompraList().remove(detalleCompraListNewDetalleCompra);
                        oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra = em.merge(oldCompraMercanciaIdcompraMercanciaOfDetalleCompraListNewDetalleCompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compraMercancia.getIdcompraMercancia();
                if (findCompraMercancia(id) == null) {
                    throw new NonexistentEntityException("The compraMercancia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompraMercancia compraMercancia;
            try {
                compraMercancia = em.getReference(CompraMercancia.class, id);
                compraMercancia.getIdcompraMercancia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compraMercancia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<DetalleCompra> detalleCompraListOrphanCheck = compraMercancia.getDetalleCompraList();
            for (DetalleCompra detalleCompraListOrphanCheckDetalleCompra : detalleCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CompraMercancia (" + compraMercancia + ") cannot be destroyed since the DetalleCompra " + detalleCompraListOrphanCheckDetalleCompra + " in its detalleCompraList field has a non-nullable compraMercanciaIdcompraMercancia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorIdproveedor = compraMercancia.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getCompraMercanciaList().remove(compraMercancia);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            em.remove(compraMercancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompraMercancia> findCompraMercanciaEntities() {
        return findCompraMercanciaEntities(true, -1, -1);
    }

    public List<CompraMercancia> findCompraMercanciaEntities(int maxResults, int firstResult) {
        return findCompraMercanciaEntities(false, maxResults, firstResult);
    }

    private List<CompraMercancia> findCompraMercanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompraMercancia.class));
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

    public CompraMercancia findCompraMercancia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompraMercancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraMercanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompraMercancia> rt = cq.from(CompraMercancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
