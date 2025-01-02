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
import com.mycompany.tiendadezapatos.model.Detallepedido;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.tiendadezapatos.model.DetalleFactura;
import com.mycompany.tiendadezapatos.model.DetalleCompra;
import com.mycompany.tiendadezapatos.model.Mercancia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class MercanciaJpaController implements Serializable {

    public MercanciaJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public MercanciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mercancia mercancia) {
        if (mercancia.getDetallepedidoList() == null) {
            mercancia.setDetallepedidoList(new ArrayList<Detallepedido>());
        }
        if (mercancia.getDetalleFacturaList() == null) {
            mercancia.setDetalleFacturaList(new ArrayList<DetalleFactura>());
        }
        if (mercancia.getDetalleCompraList() == null) {
            mercancia.setDetalleCompraList(new ArrayList<DetalleCompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallepedido> attachedDetallepedidoList = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListDetallepedidoToAttach : mercancia.getDetallepedidoList()) {
                detallepedidoListDetallepedidoToAttach = em.getReference(detallepedidoListDetallepedidoToAttach.getClass(), detallepedidoListDetallepedidoToAttach.getIddetallepedido());
                attachedDetallepedidoList.add(detallepedidoListDetallepedidoToAttach);
            }
            mercancia.setDetallepedidoList(attachedDetallepedidoList);
            List<DetalleFactura> attachedDetalleFacturaList = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListDetalleFacturaToAttach : mercancia.getDetalleFacturaList()) {
                detalleFacturaListDetalleFacturaToAttach = em.getReference(detalleFacturaListDetalleFacturaToAttach.getClass(), detalleFacturaListDetalleFacturaToAttach.getIddetalleFactura());
                attachedDetalleFacturaList.add(detalleFacturaListDetalleFacturaToAttach);
            }
            mercancia.setDetalleFacturaList(attachedDetalleFacturaList);
            List<DetalleCompra> attachedDetalleCompraList = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListDetalleCompraToAttach : mercancia.getDetalleCompraList()) {
                detalleCompraListDetalleCompraToAttach = em.getReference(detalleCompraListDetalleCompraToAttach.getClass(), detalleCompraListDetalleCompraToAttach.getIddetalleCompra());
                attachedDetalleCompraList.add(detalleCompraListDetalleCompraToAttach);
            }
            mercancia.setDetalleCompraList(attachedDetalleCompraList);
            em.persist(mercancia);
            for (Detallepedido detallepedidoListDetallepedido : mercancia.getDetallepedidoList()) {
                Mercancia oldMercanciaIdmercanciaOfDetallepedidoListDetallepedido = detallepedidoListDetallepedido.getMercanciaIdmercancia();
                detallepedidoListDetallepedido.setMercanciaIdmercancia(mercancia);
                detallepedidoListDetallepedido = em.merge(detallepedidoListDetallepedido);
                if (oldMercanciaIdmercanciaOfDetallepedidoListDetallepedido != null) {
                    oldMercanciaIdmercanciaOfDetallepedidoListDetallepedido.getDetallepedidoList().remove(detallepedidoListDetallepedido);
                    oldMercanciaIdmercanciaOfDetallepedidoListDetallepedido = em.merge(oldMercanciaIdmercanciaOfDetallepedidoListDetallepedido);
                }
            }
            for (DetalleFactura detalleFacturaListDetalleFactura : mercancia.getDetalleFacturaList()) {
                Mercancia oldMercanciaIdmercanciaOfDetalleFacturaListDetalleFactura = detalleFacturaListDetalleFactura.getMercanciaIdmercancia();
                detalleFacturaListDetalleFactura.setMercanciaIdmercancia(mercancia);
                detalleFacturaListDetalleFactura = em.merge(detalleFacturaListDetalleFactura);
                if (oldMercanciaIdmercanciaOfDetalleFacturaListDetalleFactura != null) {
                    oldMercanciaIdmercanciaOfDetalleFacturaListDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListDetalleFactura);
                    oldMercanciaIdmercanciaOfDetalleFacturaListDetalleFactura = em.merge(oldMercanciaIdmercanciaOfDetalleFacturaListDetalleFactura);
                }
            }
            for (DetalleCompra detalleCompraListDetalleCompra : mercancia.getDetalleCompraList()) {
                Mercancia oldMercanciaIdmercanciaOfDetalleCompraListDetalleCompra = detalleCompraListDetalleCompra.getMercanciaIdmercancia();
                detalleCompraListDetalleCompra.setMercanciaIdmercancia(mercancia);
                detalleCompraListDetalleCompra = em.merge(detalleCompraListDetalleCompra);
                if (oldMercanciaIdmercanciaOfDetalleCompraListDetalleCompra != null) {
                    oldMercanciaIdmercanciaOfDetalleCompraListDetalleCompra.getDetalleCompraList().remove(detalleCompraListDetalleCompra);
                    oldMercanciaIdmercanciaOfDetalleCompraListDetalleCompra = em.merge(oldMercanciaIdmercanciaOfDetalleCompraListDetalleCompra);
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
            List<Detallepedido> detallepedidoListOld = persistentMercancia.getDetallepedidoList();
            List<Detallepedido> detallepedidoListNew = mercancia.getDetallepedidoList();
            List<DetalleFactura> detalleFacturaListOld = persistentMercancia.getDetalleFacturaList();
            List<DetalleFactura> detalleFacturaListNew = mercancia.getDetalleFacturaList();
            List<DetalleCompra> detalleCompraListOld = persistentMercancia.getDetalleCompraList();
            List<DetalleCompra> detalleCompraListNew = mercancia.getDetalleCompraList();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoListOldDetallepedido : detallepedidoListOld) {
                if (!detallepedidoListNew.contains(detallepedidoListOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoListOldDetallepedido + " since its mercanciaIdmercancia field is not nullable.");
                }
            }
            for (DetalleFactura detalleFacturaListOldDetalleFactura : detalleFacturaListOld) {
                if (!detalleFacturaListNew.contains(detalleFacturaListOldDetalleFactura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleFactura " + detalleFacturaListOldDetalleFactura + " since its mercanciaIdmercancia field is not nullable.");
                }
            }
            for (DetalleCompra detalleCompraListOldDetalleCompra : detalleCompraListOld) {
                if (!detalleCompraListNew.contains(detalleCompraListOldDetalleCompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetalleCompra " + detalleCompraListOldDetalleCompra + " since its mercanciaIdmercancia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detallepedido> attachedDetallepedidoListNew = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListNewDetallepedidoToAttach : detallepedidoListNew) {
                detallepedidoListNewDetallepedidoToAttach = em.getReference(detallepedidoListNewDetallepedidoToAttach.getClass(), detallepedidoListNewDetallepedidoToAttach.getIddetallepedido());
                attachedDetallepedidoListNew.add(detallepedidoListNewDetallepedidoToAttach);
            }
            detallepedidoListNew = attachedDetallepedidoListNew;
            mercancia.setDetallepedidoList(detallepedidoListNew);
            List<DetalleFactura> attachedDetalleFacturaListNew = new ArrayList<DetalleFactura>();
            for (DetalleFactura detalleFacturaListNewDetalleFacturaToAttach : detalleFacturaListNew) {
                detalleFacturaListNewDetalleFacturaToAttach = em.getReference(detalleFacturaListNewDetalleFacturaToAttach.getClass(), detalleFacturaListNewDetalleFacturaToAttach.getIddetalleFactura());
                attachedDetalleFacturaListNew.add(detalleFacturaListNewDetalleFacturaToAttach);
            }
            detalleFacturaListNew = attachedDetalleFacturaListNew;
            mercancia.setDetalleFacturaList(detalleFacturaListNew);
            List<DetalleCompra> attachedDetalleCompraListNew = new ArrayList<DetalleCompra>();
            for (DetalleCompra detalleCompraListNewDetalleCompraToAttach : detalleCompraListNew) {
                detalleCompraListNewDetalleCompraToAttach = em.getReference(detalleCompraListNewDetalleCompraToAttach.getClass(), detalleCompraListNewDetalleCompraToAttach.getIddetalleCompra());
                attachedDetalleCompraListNew.add(detalleCompraListNewDetalleCompraToAttach);
            }
            detalleCompraListNew = attachedDetalleCompraListNew;
            mercancia.setDetalleCompraList(detalleCompraListNew);
            mercancia = em.merge(mercancia);
            for (Detallepedido detallepedidoListNewDetallepedido : detallepedidoListNew) {
                if (!detallepedidoListOld.contains(detallepedidoListNewDetallepedido)) {
                    Mercancia oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido = detallepedidoListNewDetallepedido.getMercanciaIdmercancia();
                    detallepedidoListNewDetallepedido.setMercanciaIdmercancia(mercancia);
                    detallepedidoListNewDetallepedido = em.merge(detallepedidoListNewDetallepedido);
                    if (oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido != null && !oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido.equals(mercancia)) {
                        oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido.getDetallepedidoList().remove(detallepedidoListNewDetallepedido);
                        oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido = em.merge(oldMercanciaIdmercanciaOfDetallepedidoListNewDetallepedido);
                    }
                }
            }
            for (DetalleFactura detalleFacturaListNewDetalleFactura : detalleFacturaListNew) {
                if (!detalleFacturaListOld.contains(detalleFacturaListNewDetalleFactura)) {
                    Mercancia oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura = detalleFacturaListNewDetalleFactura.getMercanciaIdmercancia();
                    detalleFacturaListNewDetalleFactura.setMercanciaIdmercancia(mercancia);
                    detalleFacturaListNewDetalleFactura = em.merge(detalleFacturaListNewDetalleFactura);
                    if (oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura != null && !oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura.equals(mercancia)) {
                        oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura.getDetalleFacturaList().remove(detalleFacturaListNewDetalleFactura);
                        oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura = em.merge(oldMercanciaIdmercanciaOfDetalleFacturaListNewDetalleFactura);
                    }
                }
            }
            for (DetalleCompra detalleCompraListNewDetalleCompra : detalleCompraListNew) {
                if (!detalleCompraListOld.contains(detalleCompraListNewDetalleCompra)) {
                    Mercancia oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra = detalleCompraListNewDetalleCompra.getMercanciaIdmercancia();
                    detalleCompraListNewDetalleCompra.setMercanciaIdmercancia(mercancia);
                    detalleCompraListNewDetalleCompra = em.merge(detalleCompraListNewDetalleCompra);
                    if (oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra != null && !oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra.equals(mercancia)) {
                        oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra.getDetalleCompraList().remove(detalleCompraListNewDetalleCompra);
                        oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra = em.merge(oldMercanciaIdmercanciaOfDetalleCompraListNewDetalleCompra);
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
            List<Detallepedido> detallepedidoListOrphanCheck = mercancia.getDetallepedidoList();
            for (Detallepedido detallepedidoListOrphanCheckDetallepedido : detallepedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mercancia (" + mercancia + ") cannot be destroyed since the Detallepedido " + detallepedidoListOrphanCheckDetallepedido + " in its detallepedidoList field has a non-nullable mercanciaIdmercancia field.");
            }
            List<DetalleFactura> detalleFacturaListOrphanCheck = mercancia.getDetalleFacturaList();
            for (DetalleFactura detalleFacturaListOrphanCheckDetalleFactura : detalleFacturaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mercancia (" + mercancia + ") cannot be destroyed since the DetalleFactura " + detalleFacturaListOrphanCheckDetalleFactura + " in its detalleFacturaList field has a non-nullable mercanciaIdmercancia field.");
            }
            List<DetalleCompra> detalleCompraListOrphanCheck = mercancia.getDetalleCompraList();
            for (DetalleCompra detalleCompraListOrphanCheckDetalleCompra : detalleCompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Mercancia (" + mercancia + ") cannot be destroyed since the DetalleCompra " + detalleCompraListOrphanCheckDetalleCompra + " in its detalleCompraList field has a non-nullable mercanciaIdmercancia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
