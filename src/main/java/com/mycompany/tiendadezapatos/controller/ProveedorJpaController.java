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
import com.mycompany.tiendadezapatos.model.Marca;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.tiendadezapatos.model.Mercancia;
import com.mycompany.tiendadezapatos.model.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JJAB
 */
public class ProveedorJpaController implements Serializable {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_TiendaDeZapatos_jar_1.0-SNAPSHOTPU");

    public ProveedorJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getMarcaList() == null) {
            proveedor.setMarcaList(new ArrayList<Marca>());
        }
        if (proveedor.getMercanciaList() == null) {
            proveedor.setMercanciaList(new ArrayList<Mercancia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Marca> attachedMarcaList = new ArrayList<Marca>();
            for (Marca marcaListMarcaToAttach : proveedor.getMarcaList()) {
                marcaListMarcaToAttach = em.getReference(marcaListMarcaToAttach.getClass(), marcaListMarcaToAttach.getIdmarca());
                attachedMarcaList.add(marcaListMarcaToAttach);
            }
            proveedor.setMarcaList(attachedMarcaList);
            List<Mercancia> attachedMercanciaList = new ArrayList<Mercancia>();
            for (Mercancia mercanciaListMercanciaToAttach : proveedor.getMercanciaList()) {
                mercanciaListMercanciaToAttach = em.getReference(mercanciaListMercanciaToAttach.getClass(), mercanciaListMercanciaToAttach.getIdmercancia());
                attachedMercanciaList.add(mercanciaListMercanciaToAttach);
            }
            proveedor.setMercanciaList(attachedMercanciaList);
            em.persist(proveedor);
            for (Marca marcaListMarca : proveedor.getMarcaList()) {
                Proveedor oldProveedorIdproveedorOfMarcaListMarca = marcaListMarca.getProveedorIdproveedor();
                marcaListMarca.setProveedorIdproveedor(proveedor);
                marcaListMarca = em.merge(marcaListMarca);
                if (oldProveedorIdproveedorOfMarcaListMarca != null) {
                    oldProveedorIdproveedorOfMarcaListMarca.getMarcaList().remove(marcaListMarca);
                    oldProveedorIdproveedorOfMarcaListMarca = em.merge(oldProveedorIdproveedorOfMarcaListMarca);
                }
            }
            for (Mercancia mercanciaListMercancia : proveedor.getMercanciaList()) {
                Proveedor oldProveedorIdproveedorOfMercanciaListMercancia = mercanciaListMercancia.getProveedorIdproveedor();
                mercanciaListMercancia.setProveedorIdproveedor(proveedor);
                mercanciaListMercancia = em.merge(mercanciaListMercancia);
                if (oldProveedorIdproveedorOfMercanciaListMercancia != null) {
                    oldProveedorIdproveedorOfMercanciaListMercancia.getMercanciaList().remove(mercanciaListMercancia);
                    oldProveedorIdproveedorOfMercanciaListMercancia = em.merge(oldProveedorIdproveedorOfMercanciaListMercancia);
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
            List<Marca> marcaListOld = persistentProveedor.getMarcaList();
            List<Marca> marcaListNew = proveedor.getMarcaList();
            List<Mercancia> mercanciaListOld = persistentProveedor.getMercanciaList();
            List<Mercancia> mercanciaListNew = proveedor.getMercanciaList();
            List<String> illegalOrphanMessages = null;
            for (Marca marcaListOldMarca : marcaListOld) {
                if (!marcaListNew.contains(marcaListOldMarca)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Marca " + marcaListOldMarca + " since its proveedorIdproveedor field is not nullable.");
                }
            }
            for (Mercancia mercanciaListOldMercancia : mercanciaListOld) {
                if (!mercanciaListNew.contains(mercanciaListOldMercancia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mercancia " + mercanciaListOldMercancia + " since its proveedorIdproveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Marca> attachedMarcaListNew = new ArrayList<Marca>();
            for (Marca marcaListNewMarcaToAttach : marcaListNew) {
                marcaListNewMarcaToAttach = em.getReference(marcaListNewMarcaToAttach.getClass(), marcaListNewMarcaToAttach.getIdmarca());
                attachedMarcaListNew.add(marcaListNewMarcaToAttach);
            }
            marcaListNew = attachedMarcaListNew;
            proveedor.setMarcaList(marcaListNew);
            List<Mercancia> attachedMercanciaListNew = new ArrayList<Mercancia>();
            for (Mercancia mercanciaListNewMercanciaToAttach : mercanciaListNew) {
                mercanciaListNewMercanciaToAttach = em.getReference(mercanciaListNewMercanciaToAttach.getClass(), mercanciaListNewMercanciaToAttach.getIdmercancia());
                attachedMercanciaListNew.add(mercanciaListNewMercanciaToAttach);
            }
            mercanciaListNew = attachedMercanciaListNew;
            proveedor.setMercanciaList(mercanciaListNew);
            proveedor = em.merge(proveedor);
            for (Marca marcaListNewMarca : marcaListNew) {
                if (!marcaListOld.contains(marcaListNewMarca)) {
                    Proveedor oldProveedorIdproveedorOfMarcaListNewMarca = marcaListNewMarca.getProveedorIdproveedor();
                    marcaListNewMarca.setProveedorIdproveedor(proveedor);
                    marcaListNewMarca = em.merge(marcaListNewMarca);
                    if (oldProveedorIdproveedorOfMarcaListNewMarca != null && !oldProveedorIdproveedorOfMarcaListNewMarca.equals(proveedor)) {
                        oldProveedorIdproveedorOfMarcaListNewMarca.getMarcaList().remove(marcaListNewMarca);
                        oldProveedorIdproveedorOfMarcaListNewMarca = em.merge(oldProveedorIdproveedorOfMarcaListNewMarca);
                    }
                }
            }
            for (Mercancia mercanciaListNewMercancia : mercanciaListNew) {
                if (!mercanciaListOld.contains(mercanciaListNewMercancia)) {
                    Proveedor oldProveedorIdproveedorOfMercanciaListNewMercancia = mercanciaListNewMercancia.getProveedorIdproveedor();
                    mercanciaListNewMercancia.setProveedorIdproveedor(proveedor);
                    mercanciaListNewMercancia = em.merge(mercanciaListNewMercancia);
                    if (oldProveedorIdproveedorOfMercanciaListNewMercancia != null && !oldProveedorIdproveedorOfMercanciaListNewMercancia.equals(proveedor)) {
                        oldProveedorIdproveedorOfMercanciaListNewMercancia.getMercanciaList().remove(mercanciaListNewMercancia);
                        oldProveedorIdproveedorOfMercanciaListNewMercancia = em.merge(oldProveedorIdproveedorOfMercanciaListNewMercancia);
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
            List<Marca> marcaListOrphanCheck = proveedor.getMarcaList();
            for (Marca marcaListOrphanCheckMarca : marcaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Marca " + marcaListOrphanCheckMarca + " in its marcaList field has a non-nullable proveedorIdproveedor field.");
            }
            List<Mercancia> mercanciaListOrphanCheck = proveedor.getMercanciaList();
            for (Mercancia mercanciaListOrphanCheckMercancia : mercanciaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Mercancia " + mercanciaListOrphanCheckMercancia + " in its mercanciaList field has a non-nullable proveedorIdproveedor field.");
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
