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
import com.mycompany.tiendadezapatos.model.Mercancia;
import com.mycompany.tiendadezapatos.model.Pedido;
import com.mycompany.tiendadezapatos.model.Actualizarprecio;
import com.mycompany.tiendadezapatos.model.Detallepedido;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class DetallepedidoJpaController implements Serializable {

    public DetallepedidoJpaController() {
    }

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public DetallepedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepedido detallepedido) {
        if (detallepedido.getActualizarprecioList() == null) {
            detallepedido.setActualizarprecioList(new ArrayList<Actualizarprecio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mercancia mercanciaIdmercancia = detallepedido.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia = em.getReference(mercanciaIdmercancia.getClass(), mercanciaIdmercancia.getIdmercancia());
                detallepedido.setMercanciaIdmercancia(mercanciaIdmercancia);
            }
            Pedido pedidoIdpedido = detallepedido.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido = em.getReference(pedidoIdpedido.getClass(), pedidoIdpedido.getIdpedido());
                detallepedido.setPedidoIdpedido(pedidoIdpedido);
            }
            List<Actualizarprecio> attachedActualizarprecioList = new ArrayList<Actualizarprecio>();
            for (Actualizarprecio actualizarprecioListActualizarprecioToAttach : detallepedido.getActualizarprecioList()) {
                actualizarprecioListActualizarprecioToAttach = em.getReference(actualizarprecioListActualizarprecioToAttach.getClass(), actualizarprecioListActualizarprecioToAttach.getActualizarprecio());
                attachedActualizarprecioList.add(actualizarprecioListActualizarprecioToAttach);
            }
            detallepedido.setActualizarprecioList(attachedActualizarprecioList);
            em.persist(detallepedido);
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetallepedidoList().add(detallepedido);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDetallepedidoList().add(detallepedido);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            for (Actualizarprecio actualizarprecioListActualizarprecio : detallepedido.getActualizarprecioList()) {
                Detallepedido oldDetallepedidoIddetallepedidoOfActualizarprecioListActualizarprecio = actualizarprecioListActualizarprecio.getDetallepedidoIddetallepedido();
                actualizarprecioListActualizarprecio.setDetallepedidoIddetallepedido(detallepedido);
                actualizarprecioListActualizarprecio = em.merge(actualizarprecioListActualizarprecio);
                if (oldDetallepedidoIddetallepedidoOfActualizarprecioListActualizarprecio != null) {
                    oldDetallepedidoIddetallepedidoOfActualizarprecioListActualizarprecio.getActualizarprecioList().remove(actualizarprecioListActualizarprecio);
                    oldDetallepedidoIddetallepedidoOfActualizarprecioListActualizarprecio = em.merge(oldDetallepedidoIddetallepedidoOfActualizarprecioListActualizarprecio);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepedido detallepedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido persistentDetallepedido = em.find(Detallepedido.class, detallepedido.getIddetallepedido());
            Mercancia mercanciaIdmercanciaOld = persistentDetallepedido.getMercanciaIdmercancia();
            Mercancia mercanciaIdmercanciaNew = detallepedido.getMercanciaIdmercancia();
            Pedido pedidoIdpedidoOld = persistentDetallepedido.getPedidoIdpedido();
            Pedido pedidoIdpedidoNew = detallepedido.getPedidoIdpedido();
            List<Actualizarprecio> actualizarprecioListOld = persistentDetallepedido.getActualizarprecioList();
            List<Actualizarprecio> actualizarprecioListNew = detallepedido.getActualizarprecioList();
            List<String> illegalOrphanMessages = null;
            for (Actualizarprecio actualizarprecioListOldActualizarprecio : actualizarprecioListOld) {
                if (!actualizarprecioListNew.contains(actualizarprecioListOldActualizarprecio)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Actualizarprecio " + actualizarprecioListOldActualizarprecio + " since its detallepedidoIddetallepedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (mercanciaIdmercanciaNew != null) {
                mercanciaIdmercanciaNew = em.getReference(mercanciaIdmercanciaNew.getClass(), mercanciaIdmercanciaNew.getIdmercancia());
                detallepedido.setMercanciaIdmercancia(mercanciaIdmercanciaNew);
            }
            if (pedidoIdpedidoNew != null) {
                pedidoIdpedidoNew = em.getReference(pedidoIdpedidoNew.getClass(), pedidoIdpedidoNew.getIdpedido());
                detallepedido.setPedidoIdpedido(pedidoIdpedidoNew);
            }
            List<Actualizarprecio> attachedActualizarprecioListNew = new ArrayList<Actualizarprecio>();
            for (Actualizarprecio actualizarprecioListNewActualizarprecioToAttach : actualizarprecioListNew) {
                actualizarprecioListNewActualizarprecioToAttach = em.getReference(actualizarprecioListNewActualizarprecioToAttach.getClass(), actualizarprecioListNewActualizarprecioToAttach.getActualizarprecio());
                attachedActualizarprecioListNew.add(actualizarprecioListNewActualizarprecioToAttach);
            }
            actualizarprecioListNew = attachedActualizarprecioListNew;
            detallepedido.setActualizarprecioList(actualizarprecioListNew);
            detallepedido = em.merge(detallepedido);
            if (mercanciaIdmercanciaOld != null && !mercanciaIdmercanciaOld.equals(mercanciaIdmercanciaNew)) {
                mercanciaIdmercanciaOld.getDetallepedidoList().remove(detallepedido);
                mercanciaIdmercanciaOld = em.merge(mercanciaIdmercanciaOld);
            }
            if (mercanciaIdmercanciaNew != null && !mercanciaIdmercanciaNew.equals(mercanciaIdmercanciaOld)) {
                mercanciaIdmercanciaNew.getDetallepedidoList().add(detallepedido);
                mercanciaIdmercanciaNew = em.merge(mercanciaIdmercanciaNew);
            }
            if (pedidoIdpedidoOld != null && !pedidoIdpedidoOld.equals(pedidoIdpedidoNew)) {
                pedidoIdpedidoOld.getDetallepedidoList().remove(detallepedido);
                pedidoIdpedidoOld = em.merge(pedidoIdpedidoOld);
            }
            if (pedidoIdpedidoNew != null && !pedidoIdpedidoNew.equals(pedidoIdpedidoOld)) {
                pedidoIdpedidoNew.getDetallepedidoList().add(detallepedido);
                pedidoIdpedidoNew = em.merge(pedidoIdpedidoNew);
            }
            for (Actualizarprecio actualizarprecioListNewActualizarprecio : actualizarprecioListNew) {
                if (!actualizarprecioListOld.contains(actualizarprecioListNewActualizarprecio)) {
                    Detallepedido oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio = actualizarprecioListNewActualizarprecio.getDetallepedidoIddetallepedido();
                    actualizarprecioListNewActualizarprecio.setDetallepedidoIddetallepedido(detallepedido);
                    actualizarprecioListNewActualizarprecio = em.merge(actualizarprecioListNewActualizarprecio);
                    if (oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio != null && !oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio.equals(detallepedido)) {
                        oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio.getActualizarprecioList().remove(actualizarprecioListNewActualizarprecio);
                        oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio = em.merge(oldDetallepedidoIddetallepedidoOfActualizarprecioListNewActualizarprecio);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detallepedido.getIddetallepedido();
                if (findDetallepedido(id) == null) {
                    throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.");
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
            Detallepedido detallepedido;
            try {
                detallepedido = em.getReference(Detallepedido.class, id);
                detallepedido.getIddetallepedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Actualizarprecio> actualizarprecioListOrphanCheck = detallepedido.getActualizarprecioList();
            for (Actualizarprecio actualizarprecioListOrphanCheckActualizarprecio : actualizarprecioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Detallepedido (" + detallepedido + ") cannot be destroyed since the Actualizarprecio " + actualizarprecioListOrphanCheckActualizarprecio + " in its actualizarprecioList field has a non-nullable detallepedidoIddetallepedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Mercancia mercanciaIdmercancia = detallepedido.getMercanciaIdmercancia();
            if (mercanciaIdmercancia != null) {
                mercanciaIdmercancia.getDetallepedidoList().remove(detallepedido);
                mercanciaIdmercancia = em.merge(mercanciaIdmercancia);
            }
            Pedido pedidoIdpedido = detallepedido.getPedidoIdpedido();
            if (pedidoIdpedido != null) {
                pedidoIdpedido.getDetallepedidoList().remove(detallepedido);
                pedidoIdpedido = em.merge(pedidoIdpedido);
            }
            em.remove(detallepedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepedido> findDetallepedidoEntities() {
        return findDetallepedidoEntities(true, -1, -1);
    }

    public List<Detallepedido> findDetallepedidoEntities(int maxResults, int firstResult) {
        return findDetallepedidoEntities(false, maxResults, firstResult);
    }

    private List<Detallepedido> findDetallepedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepedido.class));
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

    public Detallepedido findDetallepedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepedido> rt = cq.from(Detallepedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
