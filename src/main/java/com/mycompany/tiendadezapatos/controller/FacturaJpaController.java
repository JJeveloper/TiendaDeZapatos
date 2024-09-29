/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.controller;

import com.mycompany.tiendadezapatos.controller.exceptions.IllegalOrphanException;
import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.tiendadezapatos.model.Cliente;
import com.mycompany.tiendadezapatos.model.Factura;
import com.mycompany.tiendadezapatos.model.MercanciaHasFactura;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JJAB
 */
public class FacturaJpaController implements Serializable {

    public FacturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Factura factura) {
        if (factura.getMercanciaHasFacturaList() == null) {
            factura.setMercanciaHasFacturaList(new ArrayList<MercanciaHasFactura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteIdcliente = factura.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente = em.getReference(clienteIdcliente.getClass(), clienteIdcliente.getIdcliente());
                factura.setClienteIdcliente(clienteIdcliente);
            }
            List<MercanciaHasFactura> attachedMercanciaHasFacturaList = new ArrayList<MercanciaHasFactura>();
            for (MercanciaHasFactura mercanciaHasFacturaListMercanciaHasFacturaToAttach : factura.getMercanciaHasFacturaList()) {
                mercanciaHasFacturaListMercanciaHasFacturaToAttach = em.getReference(mercanciaHasFacturaListMercanciaHasFacturaToAttach.getClass(), mercanciaHasFacturaListMercanciaHasFacturaToAttach.getMercanciaHasFacturaPK());
                attachedMercanciaHasFacturaList.add(mercanciaHasFacturaListMercanciaHasFacturaToAttach);
            }
            factura.setMercanciaHasFacturaList(attachedMercanciaHasFacturaList);
            em.persist(factura);
            if (clienteIdcliente != null) {
                clienteIdcliente.getFacturaList().add(factura);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            for (MercanciaHasFactura mercanciaHasFacturaListMercanciaHasFactura : factura.getMercanciaHasFacturaList()) {
                Factura oldFacturaOfMercanciaHasFacturaListMercanciaHasFactura = mercanciaHasFacturaListMercanciaHasFactura.getFactura();
                mercanciaHasFacturaListMercanciaHasFactura.setFactura(factura);
                mercanciaHasFacturaListMercanciaHasFactura = em.merge(mercanciaHasFacturaListMercanciaHasFactura);
                if (oldFacturaOfMercanciaHasFacturaListMercanciaHasFactura != null) {
                    oldFacturaOfMercanciaHasFacturaListMercanciaHasFactura.getMercanciaHasFacturaList().remove(mercanciaHasFacturaListMercanciaHasFactura);
                    oldFacturaOfMercanciaHasFacturaListMercanciaHasFactura = em.merge(oldFacturaOfMercanciaHasFacturaListMercanciaHasFactura);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Factura factura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Factura persistentFactura = em.find(Factura.class, factura.getIdfactura());
            Cliente clienteIdclienteOld = persistentFactura.getClienteIdcliente();
            Cliente clienteIdclienteNew = factura.getClienteIdcliente();
            List<MercanciaHasFactura> mercanciaHasFacturaListOld = persistentFactura.getMercanciaHasFacturaList();
            List<MercanciaHasFactura> mercanciaHasFacturaListNew = factura.getMercanciaHasFacturaList();
            List<String> illegalOrphanMessages = null;
            for (MercanciaHasFactura mercanciaHasFacturaListOldMercanciaHasFactura : mercanciaHasFacturaListOld) {
                if (!mercanciaHasFacturaListNew.contains(mercanciaHasFacturaListOldMercanciaHasFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MercanciaHasFactura " + mercanciaHasFacturaListOldMercanciaHasFactura + " since its factura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteIdclienteNew != null) {
                clienteIdclienteNew = em.getReference(clienteIdclienteNew.getClass(), clienteIdclienteNew.getIdcliente());
                factura.setClienteIdcliente(clienteIdclienteNew);
            }
            List<MercanciaHasFactura> attachedMercanciaHasFacturaListNew = new ArrayList<MercanciaHasFactura>();
            for (MercanciaHasFactura mercanciaHasFacturaListNewMercanciaHasFacturaToAttach : mercanciaHasFacturaListNew) {
                mercanciaHasFacturaListNewMercanciaHasFacturaToAttach = em.getReference(mercanciaHasFacturaListNewMercanciaHasFacturaToAttach.getClass(), mercanciaHasFacturaListNewMercanciaHasFacturaToAttach.getMercanciaHasFacturaPK());
                attachedMercanciaHasFacturaListNew.add(mercanciaHasFacturaListNewMercanciaHasFacturaToAttach);
            }
            mercanciaHasFacturaListNew = attachedMercanciaHasFacturaListNew;
            factura.setMercanciaHasFacturaList(mercanciaHasFacturaListNew);
            factura = em.merge(factura);
            if (clienteIdclienteOld != null && !clienteIdclienteOld.equals(clienteIdclienteNew)) {
                clienteIdclienteOld.getFacturaList().remove(factura);
                clienteIdclienteOld = em.merge(clienteIdclienteOld);
            }
            if (clienteIdclienteNew != null && !clienteIdclienteNew.equals(clienteIdclienteOld)) {
                clienteIdclienteNew.getFacturaList().add(factura);
                clienteIdclienteNew = em.merge(clienteIdclienteNew);
            }
            for (MercanciaHasFactura mercanciaHasFacturaListNewMercanciaHasFactura : mercanciaHasFacturaListNew) {
                if (!mercanciaHasFacturaListOld.contains(mercanciaHasFacturaListNewMercanciaHasFactura)) {
                    Factura oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura = mercanciaHasFacturaListNewMercanciaHasFactura.getFactura();
                    mercanciaHasFacturaListNewMercanciaHasFactura.setFactura(factura);
                    mercanciaHasFacturaListNewMercanciaHasFactura = em.merge(mercanciaHasFacturaListNewMercanciaHasFactura);
                    if (oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura != null && !oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura.equals(factura)) {
                        oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura.getMercanciaHasFacturaList().remove(mercanciaHasFacturaListNewMercanciaHasFactura);
                        oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura = em.merge(oldFacturaOfMercanciaHasFacturaListNewMercanciaHasFactura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = factura.getIdfactura();
                if (findFactura(id) == null) {
                    throw new NonexistentEntityException("The factura with id " + id + " no longer exists.");
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
            Factura factura;
            try {
                factura = em.getReference(Factura.class, id);
                factura.getIdfactura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The factura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MercanciaHasFactura> mercanciaHasFacturaListOrphanCheck = factura.getMercanciaHasFacturaList();
            for (MercanciaHasFactura mercanciaHasFacturaListOrphanCheckMercanciaHasFactura : mercanciaHasFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Factura (" + factura + ") cannot be destroyed since the MercanciaHasFactura " + mercanciaHasFacturaListOrphanCheckMercanciaHasFactura + " in its mercanciaHasFacturaList field has a non-nullable factura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteIdcliente = factura.getClienteIdcliente();
            if (clienteIdcliente != null) {
                clienteIdcliente.getFacturaList().remove(factura);
                clienteIdcliente = em.merge(clienteIdcliente);
            }
            em.remove(factura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Factura> findFacturaEntities() {
        return findFacturaEntities(true, -1, -1);
    }

    public List<Factura> findFacturaEntities(int maxResults, int firstResult) {
        return findFacturaEntities(false, maxResults, firstResult);
    }

    private List<Factura> findFacturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Factura.class));
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

    public Factura findFactura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Factura.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Factura> rt = cq.from(Factura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
