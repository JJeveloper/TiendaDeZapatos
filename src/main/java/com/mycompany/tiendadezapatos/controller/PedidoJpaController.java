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
import com.mycompany.tiendadezapatos.model.Proveedor;
import com.mycompany.tiendadezapatos.model.Detallepedido;
import com.mycompany.tiendadezapatos.model.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getDetallepedidoList() == null) {
            pedido.setDetallepedidoList(new ArrayList<Detallepedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorIdproveedor = pedido.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor = em.getReference(proveedorIdproveedor.getClass(), proveedorIdproveedor.getIdproveedor());
                pedido.setProveedorIdproveedor(proveedorIdproveedor);
            }
            List<Detallepedido> attachedDetallepedidoList = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListDetallepedidoToAttach : pedido.getDetallepedidoList()) {
                detallepedidoListDetallepedidoToAttach = em.getReference(detallepedidoListDetallepedidoToAttach.getClass(), detallepedidoListDetallepedidoToAttach.getIddetallepedido());
                attachedDetallepedidoList.add(detallepedidoListDetallepedidoToAttach);
            }
            pedido.setDetallepedidoList(attachedDetallepedidoList);
            em.persist(pedido);
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getPedidoList().add(pedido);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            for (Detallepedido detallepedidoListDetallepedido : pedido.getDetallepedidoList()) {
                Pedido oldPedidoIdpedidoOfDetallepedidoListDetallepedido = detallepedidoListDetallepedido.getPedidoIdpedido();
                detallepedidoListDetallepedido.setPedidoIdpedido(pedido);
                detallepedidoListDetallepedido = em.merge(detallepedidoListDetallepedido);
                if (oldPedidoIdpedidoOfDetallepedidoListDetallepedido != null) {
                    oldPedidoIdpedidoOfDetallepedidoListDetallepedido.getDetallepedidoList().remove(detallepedidoListDetallepedido);
                    oldPedidoIdpedidoOfDetallepedidoListDetallepedido = em.merge(oldPedidoIdpedidoOfDetallepedidoListDetallepedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdpedido());
            Proveedor proveedorIdproveedorOld = persistentPedido.getProveedorIdproveedor();
            Proveedor proveedorIdproveedorNew = pedido.getProveedorIdproveedor();
            List<Detallepedido> detallepedidoListOld = persistentPedido.getDetallepedidoList();
            List<Detallepedido> detallepedidoListNew = pedido.getDetallepedidoList();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoListOldDetallepedido : detallepedidoListOld) {
                if (!detallepedidoListNew.contains(detallepedidoListOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoListOldDetallepedido + " since its pedidoIdpedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorIdproveedorNew != null) {
                proveedorIdproveedorNew = em.getReference(proveedorIdproveedorNew.getClass(), proveedorIdproveedorNew.getIdproveedor());
                pedido.setProveedorIdproveedor(proveedorIdproveedorNew);
            }
            List<Detallepedido> attachedDetallepedidoListNew = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListNewDetallepedidoToAttach : detallepedidoListNew) {
                detallepedidoListNewDetallepedidoToAttach = em.getReference(detallepedidoListNewDetallepedidoToAttach.getClass(), detallepedidoListNewDetallepedidoToAttach.getIddetallepedido());
                attachedDetallepedidoListNew.add(detallepedidoListNewDetallepedidoToAttach);
            }
            detallepedidoListNew = attachedDetallepedidoListNew;
            pedido.setDetallepedidoList(detallepedidoListNew);
            pedido = em.merge(pedido);
            if (proveedorIdproveedorOld != null && !proveedorIdproveedorOld.equals(proveedorIdproveedorNew)) {
                proveedorIdproveedorOld.getPedidoList().remove(pedido);
                proveedorIdproveedorOld = em.merge(proveedorIdproveedorOld);
            }
            if (proveedorIdproveedorNew != null && !proveedorIdproveedorNew.equals(proveedorIdproveedorOld)) {
                proveedorIdproveedorNew.getPedidoList().add(pedido);
                proveedorIdproveedorNew = em.merge(proveedorIdproveedorNew);
            }
            for (Detallepedido detallepedidoListNewDetallepedido : detallepedidoListNew) {
                if (!detallepedidoListOld.contains(detallepedidoListNewDetallepedido)) {
                    Pedido oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido = detallepedidoListNewDetallepedido.getPedidoIdpedido();
                    detallepedidoListNewDetallepedido.setPedidoIdpedido(pedido);
                    detallepedidoListNewDetallepedido = em.merge(detallepedidoListNewDetallepedido);
                    if (oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido != null && !oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido.equals(pedido)) {
                        oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido.getDetallepedidoList().remove(detallepedidoListNewDetallepedido);
                        oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido = em.merge(oldPedidoIdpedidoOfDetallepedidoListNewDetallepedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedido.getIdpedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Detallepedido> detallepedidoListOrphanCheck = pedido.getDetallepedidoList();
            for (Detallepedido detallepedidoListOrphanCheckDetallepedido : detallepedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Detallepedido " + detallepedidoListOrphanCheckDetallepedido + " in its detallepedidoList field has a non-nullable pedidoIdpedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorIdproveedor = pedido.getProveedorIdproveedor();
            if (proveedorIdproveedor != null) {
                proveedorIdproveedor.getPedidoList().remove(pedido);
                proveedorIdproveedor = em.merge(proveedorIdproveedor);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
