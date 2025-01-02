/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.PedidoJpaController;
import com.mycompany.tiendadezapatos.model.Pedido;

/**
 *
 * @author JJAB
 */
public class PedidoDAO {

    PedidoJpaController pedidoJpaController = new PedidoJpaController();

    public void crearPedido(Pedido p) {
        try {

            pedidoJpaController.create(p);
            
        } catch (Exception e) {
        }
    }

}
