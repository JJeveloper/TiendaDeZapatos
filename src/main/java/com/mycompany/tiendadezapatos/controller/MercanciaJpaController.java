/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.IllegalOrphanException;
import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.Mercancia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Proveedor;
import com.mycompany.tiendadezapatos.model.MercanciaHasFactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class MercanciaJpaController implements Serializable {

    public MercanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mercancia mercancia) {
        if (mercancia.getMercanciaHasFacturaList() == null) {
            mercancia.setMercanciaHasFacturaList(new ArrayList<MercanciaHasFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorIdproveedor = mercancia.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor = em.getReference(proveedorIdproveedor.getClass(), proveedorIdproveedor.getIdproveedor());
                mercancia.setProveedorIdproveedor(proveedorIdproveedor);
            }
            List<MercanciaHasFactura> attachedMercanciaHasFacturaList = new ArrayList<MercanciaHasFactura>();
            for (MercanciaHasFactura mercanciaHasFacturaListMercanciaHasFacturaToAttach : mercancia.getMercanciaHasFacturaList()) {
                mercanciaHasFacturaListMercanciaHasFacturaToAttach = em.getReference(mercanciaHasFacturaListMercanciaHasFacturaToAttach.getClass(), mercanciaHasFacturaListMercanciaHasFacturaToAttach.getMercanciaHasFacturaPK());
                attachedMercanciaHasFacturaList.add(mercanciaHasFacturaListMercanciaHasFacturaToAttach);
            }
            mercancia.setMercanciaHasFacturaList(attachedMercanciaHasFacturaList);
            em.persist(mercancia);
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getMercanciaList().add(mercancia);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            for (MercanciaHasFactura mercanciaHasFacturaListMercanciaHasFactura : mercancia.getMercanciaHasFacturaList()) {
                Mercancia oldMercanciaOfMercanciaHasFacturaListMercanciaHasFactura = mercanciaHasFacturaListMercanciaHasFactura.getMercancia();
                mercanciaHasFacturaListMercanciaHasFactura.setMercancia(mercancia);
                mercanciaHasFacturaListMercanciaHasFactura = em.merge(mercanciaHasFacturaListMercanciaHasFactura);
                if (oldMercanciaOfMercanciaHasFacturaListMercanciaHasFactura != null) {
                    oldMercanciaOfMercanciaHasFacturaListMercanciaHasFactura.getMercanciaHasFacturaList().remove(mercanciaHasFacturaListMercanciaHasFactura);
                    oldMercanciaOfMercanciaHasFacturaListMercanciaHasFactura = em.merge(oldMercanciaOfMercanciaHasFacturaListMercanciaHasFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mercancia mercancia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercancia persistentMercancia = em.find(Mercancia.class, mercancia.getIdmercancia());
            Proveedor proveedorIdproveedorOld = persistentMercancia.getProveedorIdproveedor();
            Proveedor proveedorIdproveedorNew = mercancia.getProveedorIdproveedor();
            List<MercanciaHasFactura> mercanciaHasFacturaListOld = persistentMercancia.getMercanciaHasFacturaList();
            List<MercanciaHasFactura> mercanciaHasFacturaListNew = mercancia.getMercanciaHasFacturaList();
            List<String> illegalOrphanMessages = null;
            for (MercanciaHasFactura mercanciaHasFacturaListOldMercanciaHasFactura : mercanciaHasFacturaListOld) {
                if (!mercanciaHasFacturaListNew.contains(mercanciaHasFacturaListOldMercanciaHasFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MercanciaHasFactura " + mercanciaHasFacturaListOldMercanciaHasFactura + " since its mercancia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorIdproveedorNew != null) {
                proveedorIdproveedorNew = em.getReference(proveedorIdproveedorNew.getClass(), proveedorIdproveedorNew.getIdproveedor());
                mercancia.setProveedorIdproveedor(proveedorIdproveedorNew);
            }
            List<MercanciaHasFactura> attachedMercanciaHasFacturaListNew = new ArrayList<MercanciaHasFactura>();
            for (MercanciaHasFactura mercanciaHasFacturaListNewMercanciaHasFacturaToAttach : mercanciaHasFacturaListNew) {
                mercanciaHasFacturaListNewMercanciaHasFacturaToAttach = em.getReference(mercanciaHasFacturaListNewMercanciaHasFacturaToAttach.getClass(), mercanciaHasFacturaListNewMercanciaHasFacturaToAttach.getMercanciaHasFacturaPK());
                attachedMercanciaHasFacturaListNew.add(mercanciaHasFacturaListNewMercanciaHasFacturaToAttach);
            }
            mercanciaHasFacturaListNew = attachedMercanciaHasFacturaListNew;
            mercancia.setMercanciaHasFacturaList(mercanciaHasFacturaListNew);
            mercancia = em.merge(mercancia);
            if (proveedorIdproveedorOld != null && !proveedorIdproveedorOld.equals(proveedorIdproveedorNew)) {
                proveedorIdproveedorOld.getMercanciaList().remove(mercancia);
                proveedorIdproveedorOld = em.merge(proveedorIdproveedorOld);
            }
            if (proveedorIdproveedorNew != null && !proveedorIdproveedorNew.equals(proveedorIdproveedorOld)) {
                proveedorIdproveedorNew.getMercanciaList().add(mercancia);
                proveedorIdproveedorNew = em.merge(proveedorIdproveedorNew);
            }
            for (MercanciaHasFactura mercanciaHasFacturaListNewMercanciaHasFactura : mercanciaHasFacturaListNew) {
                if (!mercanciaHasFacturaListOld.contains(mercanciaHasFacturaListNewMercanciaHasFactura)) {
                    Mercancia oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura = mercanciaHasFacturaListNewMercanciaHasFactura.getMercancia();
                    mercanciaHasFacturaListNewMercanciaHasFactura.setMercancia(mercancia);
                    mercanciaHasFacturaListNewMercanciaHasFactura = em.merge(mercanciaHasFacturaListNewMercanciaHasFactura);
                    if (oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura != null && !oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura.equals(mercancia)) {
                        oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura.getMercanciaHasFacturaList().remove(mercanciaHasFacturaListNewMercanciaHasFactura);
                        oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura = em.merge(oldMercanciaOfMercanciaHasFacturaListNewMercanciaHasFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mercancia.getIdmercancia();
                if (findMercancia(id) == null) {
                    throw new NonexistentEntityException("The mercancia with id " + id + " no longer exists.");
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
            Mercancia mercancia;
            try {
                mercancia = em.getReference(Mercancia.class, id);
                mercancia.getIdmercancia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mercancia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MercanciaHasFactura> mercanciaHasFacturaListOrphanCheck = mercancia.getMercanciaHasFacturaList();
            for (MercanciaHasFactura mercanciaHasFacturaListOrphanCheckMercanciaHasFactura : mercanciaHasFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mercancia (" + mercancia + ") cannot be destroyed since the MercanciaHasFactura " + mercanciaHasFacturaListOrphanCheckMercanciaHasFactura + " in its mercanciaHasFacturaList field has a non-nullable mercancia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorIdproveedor = mercancia.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getMercanciaList().remove(mercancia);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            em.remove(mercancia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mercancia> findMercanciaEntities() {
        return findMercanciaEntities(true, -1, -1);
    }

    public List<Mercancia> findMercanciaEntities(int maxResults, int firstResult) {
        return findMercanciaEntities(false, maxResults, firstResult);
    }

    private List<Mercancia> findMercanciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mercancia.class));
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

    public Mercancia findMercancia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mercancia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMercanciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mercancia> rt = cq.from(Mercancia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
