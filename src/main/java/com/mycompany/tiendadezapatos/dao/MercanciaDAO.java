/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.MercanciaJpaController;
import com.mycompany.tiendadezapatos.model.Mercancia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JJAB
 */
public class MercanciaDAO {

    MercanciaJpaController mercanciaJpaController = new MercanciaJpaController();

    public void crearMercancia(Mercancia mercancia) {
        try {

            mercanciaJpaController.create(mercancia);

        } catch (Exception e) {
            System.out.println("No se puede crear la mercancia");
        }

    }

    /**
     * compara que el nuevo codigo no se repita en la base de datos
     *
     * @param codigo
     * @return verdadero si el codigo ya existe o falso si no existe
     */
    public boolean validarCodigoProveedor(String codigo) {
        int cantidad = mercanciaJpaController.findMercanciaEntities().size();

        for (int i = 0; i < cantidad; i++) {
            if (codigo.equals(mercanciaJpaController.findMercanciaEntities().get(i).getCodigo())) {
                return true;
            }

        }
        return false;
    }

    public Mercancia obtenerMercanciaPorID(int id) {
        return mercanciaJpaController.findMercancia(id);
    }

    public List<Mercancia> obtenerMercanciaPorProveedor(int id) {
        List<Mercancia> mercancia = new ArrayList<>();

        try {
            Query query = mercanciaJpaController.getEntityManager().createNamedQuery("Mercancia.obtenerMercanciaPorProveedor");
            query.setParameter("idproveedor", id);

            mercancia = query.getResultList();

        } catch (Exception e) {
            System.out.println("no se puede cargar los datos DAO " + e);

        }

        return mercancia;
    }

    public void actualizarStockMercancia(int id, int nuevostock, String precioCompra, String precioVenta) {

        try {
            BigDecimal bigCompra = new BigDecimal(precioCompra);
            BigDecimal bigVenta = new BigDecimal(precioVenta);

            Mercancia actualizarMercancia = mercanciaJpaController.findMercancia(id);
            actualizarMercancia.setStock(nuevostock);
            actualizarMercancia.setPrecioCompraUnidad(bigCompra);
            actualizarMercancia.setPrecioVentaUnidad(bigVenta);

            mercanciaJpaController.edit(actualizarMercancia);

        } catch (Exception e) {
            System.out.println("no se puede actualizar el stock DAO " + e);

        }
    }

    public List<Mercancia> buscarMercanciaPorCodigo(String codigo) {
        List<Mercancia> mercanciaList = new ArrayList<>();
        try {

            Query q = mercanciaJpaController.getEntityManager().createNamedQuery("Mercancia.findByCodigo");
            q.setParameter("codigo", codigo);

            mercanciaList = q.getResultList();

        } catch (Exception e) {
            System.out.println("No existe la mercancia");
        }
        return mercanciaList;
    }

}
