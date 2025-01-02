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
import com.mycompany.tiendadezapatos.model.CompraMercancia;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.tiendadezapatos.model.Pedido;
import com.mycompany.tiendadezapatos.model.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getCompraMercanciaList() == null) {
            proveedor.setCompraMercanciaList(new ArrayList<CompraMercancia>());
        }
        if (proveedor.getPedidoList() == null) {
            proveedor.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CompraMercancia> attachedCompraMercanciaList = new ArrayList<CompraMercancia>();
            for (CompraMercancia compraMercanciaListCompraMercanciaToAttach : proveedor.getCompraMercanciaList()) {
                compraMercanciaListCompraMercanciaToAttach = em.getReference(compraMercanciaListCompraMercanciaToAttach.getClass(), compraMercanciaListCompraMercanciaToAttach.getIdcompraMercancia());
                attachedCompraMercanciaList.add(compraMercanciaListCompraMercanciaToAttach);
            }
            proveedor.setCompraMercanciaList(attachedCompraMercanciaList);
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : proveedor.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            proveedor.setPedidoList(attachedPedidoList);
            em.persist(proveedor);
            for (CompraMercancia compraMercanciaListCompraMercancia : proveedor.getCompraMercanciaList()) {
                Proveedor oldProveedorIdproveedorOfCompraMercanciaListCompraMercancia = compraMercanciaListCompraMercancia.getProveedorIdproveedor();
                compraMercanciaListCompraMercancia.setProveedorIdproveedor(proveedor);
                compraMercanciaListCompraMercancia = em.merge(compraMercanciaListCompraMercancia);
                if (oldProveedorIdproveedorOfCompraMercanciaListCompraMercancia != null) {
                    oldProveedorIdproveedorOfCompraMercanciaListCompraMercancia.getCompraMercanciaList().remove(compraMercanciaListCompraMercancia);
                    oldProveedorIdproveedorOfCompraMercanciaListCompraMercancia = em.merge(oldProveedorIdproveedorOfCompraMercanciaListCompraMercancia);
                }
            }
            for (Pedido pedidoListPedido : proveedor.getPedidoList()) {
                Proveedor oldProveedorIdproveedorOfPedidoListPedido = pedidoListPedido.getProveedorIdproveedor();
                pedidoListPedido.setProveedorIdproveedor(proveedor);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldProveedorIdproveedorOfPedidoListPedido != null) {
                    oldProveedorIdproveedorOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldProveedorIdproveedorOfPedidoListPedido = em.merge(oldProveedorIdproveedorOfPedidoListPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdproveedor());
            List<CompraMercancia> compraMercanciaListOld = persistentProveedor.getCompraMercanciaList();
            List<CompraMercancia> compraMercanciaListNew = proveedor.getCompraMercanciaList();
            List<Pedido> pedidoListOld = persistentProveedor.getPedidoList();
            List<Pedido> pedidoListNew = proveedor.getPedidoList();
            List<String> illegalOrphanMessages = null;
            for (CompraMercancia compraMercanciaListOldCompraMercancia : compraMercanciaListOld) {
                if (!compraMercanciaListNew.contains(compraMercanciaListOldCompraMercancia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CompraMercancia " + compraMercanciaListOldCompraMercancia + " since its proveedorIdproveedor field is not nullable.");
                }
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoListOldPedido + " since its proveedorIdproveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<CompraMercancia> attachedCompraMercanciaListNew = new ArrayList<CompraMercancia>();
            for (CompraMercancia compraMercanciaListNewCompraMercanciaToAttach : compraMercanciaListNew) {
                compraMercanciaListNewCompraMercanciaToAttach = em.getReference(compraMercanciaListNewCompraMercanciaToAttach.getClass(), compraMercanciaListNewCompraMercanciaToAttach.getIdcompraMercancia());
                attachedCompraMercanciaListNew.add(compraMercanciaListNewCompraMercanciaToAttach);
            }
            compraMercanciaListNew = attachedCompraMercanciaListNew;
            proveedor.setCompraMercanciaList(compraMercanciaListNew);
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            proveedor.setPedidoList(pedidoListNew);
            proveedor = em.merge(proveedor);
            for (CompraMercancia compraMercanciaListNewCompraMercancia : compraMercanciaListNew) {
                if (!compraMercanciaListOld.contains(compraMercanciaListNewCompraMercancia)) {
                    Proveedor oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia = compraMercanciaListNewCompraMercancia.getProveedorIdproveedor();
                    compraMercanciaListNewCompraMercancia.setProveedorIdproveedor(proveedor);
                    compraMercanciaListNewCompraMercancia = em.merge(compraMercanciaListNewCompraMercancia);
                    if (oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia != null && !oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia.equals(proveedor)) {
                        oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia.getCompraMercanciaList().remove(compraMercanciaListNewCompraMercancia);
                        oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia = em.merge(oldProveedorIdproveedorOfCompraMercanciaListNewCompraMercancia);
                    }
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Proveedor oldProveedorIdproveedorOfPedidoListNewPedido = pedidoListNewPedido.getProveedorIdproveedor();
                    pedidoListNewPedido.setProveedorIdproveedor(proveedor);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldProveedorIdproveedorOfPedidoListNewPedido != null && !oldProveedorIdproveedorOfPedidoListNewPedido.equals(proveedor)) {
                        oldProveedorIdproveedorOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldProveedorIdproveedorOfPedidoListNewPedido = em.merge(oldProveedorIdproveedorOfPedidoListNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdproveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdproveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<CompraMercancia> compraMercanciaListOrphanCheck = proveedor.getCompraMercanciaList();
            for (CompraMercancia compraMercanciaListOrphanCheckCompraMercancia : compraMercanciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the CompraMercancia " + compraMercanciaListOrphanCheckCompraMercancia + " in its compraMercanciaList field has a non-nullable proveedorIdproveedor field.");
            }
            List<Pedido> pedidoListOrphanCheck = proveedor.getPedidoList();
            for (Pedido pedidoListOrphanCheckPedido : pedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Pedido " + pedidoListOrphanCheckPedido + " in its pedidoList field has a non-nullable proveedorIdproveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
