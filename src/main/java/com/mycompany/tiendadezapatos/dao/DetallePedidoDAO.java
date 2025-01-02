/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.DetallepedidoJpaController;
import com.mycompany.tiendadezapatos.model.Detallepedido;

/**
 *
 * @author JJAB
 */
public class DetallePedidoDAO {

    DetallepedidoJpaController detallepedidoJpaController = new DetallepedidoJpaController();

    public void crearDetallePedido(Detallepedido detallePedido) {

        try {

            detallepedidoJpaController.create(detallePedido);

        } catch (Exception e) {

        }

    }
    
//    public Detallepedido obtenerDetallePedioPorId(int id){
//        try {
//            return detallepedidoJpaController.findDetallepedido(id);
//        } catch (Exception e) {
//            
//        }
//        return null;
//    }

}
