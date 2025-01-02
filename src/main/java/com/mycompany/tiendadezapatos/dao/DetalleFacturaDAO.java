/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.DetalleFacturaJpaController;
import com.mycompany.tiendadezapatos.model.DetalleFactura;

/**
 *
 * @author JJAB
 */
public class DetalleFacturaDAO {

    private DetalleFacturaJpaController detalleFacturaJpaController = new DetalleFacturaJpaController();

    public boolean crearDetalleFactura(DetalleFactura detalleFactura) {
        try {
            detalleFacturaJpaController.create(detalleFactura);
            return true;
        } catch (Exception e) {
            System.out.println("No se puede guardar el detalleFactura");
            return false;
        }

    }
}
