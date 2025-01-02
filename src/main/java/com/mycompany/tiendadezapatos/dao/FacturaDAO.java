/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.FacturaJpaController;
import com.mycompany.tiendadezapatos.model.Factura;

/**
 *
 * @author JJAB
 */
public class FacturaDAO {

    private FacturaJpaController facturaJpaController = new FacturaJpaController();

    public boolean crearFactura(Factura factura) {
        try {
            facturaJpaController.create(factura);
            return true;
        } catch (Exception e) {
            System.out.println("No se puede guardar la factura");
            return false;
        }

    }

}
