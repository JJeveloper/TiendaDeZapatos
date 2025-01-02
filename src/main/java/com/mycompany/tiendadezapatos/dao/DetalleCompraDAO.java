/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.DetalleCompraJpaController;
import com.mycompany.tiendadezapatos.model.DetalleCompra;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JJAB
 */
public class DetalleCompraDAO {

    DetalleCompraJpaController detalleCompraJpaController = new DetalleCompraJpaController();

    public void crearDetalleCompra(DetalleCompra detalleCompra) {
        try {
            detalleCompraJpaController.create(detalleCompra);
        } catch (Exception e) {
            System.out.println("no se pudo guardar detalle compra DAO " + e);
        }

    }

//    public List<DetalleCompra> obtenerDetalleCompraPorMercancia(int id) {
//        List<DetalleCompra> compras = new ArrayList<>();
//        try {
//
//            compras = detalleCompraJpaController.findDetalleCompraEntities();
//            System.out.println("compras " + compras);
//            Query query = detalleCompraJpaController.getEntityManager().createNamedQuery("DetalleCompra.obtenerPorMercancia");
//            query.setParameter("id", id);
//            compras = query.getResultList();
//        } catch (Exception e) {
//            System.out.println("No se puede obtener resultados dao detalle compra: " + e);
//        }
//        return compras;
//    }

}
