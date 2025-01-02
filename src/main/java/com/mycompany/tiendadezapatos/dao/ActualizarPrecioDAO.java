/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.ActualizarprecioJpaController;
import com.mycompany.tiendadezapatos.model.Actualizarprecio;

/**
 *
 * @author JJAB
 */
public class ActualizarPrecioDAO {

    ActualizarprecioJpaController actualizarprecioJpaController = new ActualizarprecioJpaController();

    public void crearActualizarPrecio(Actualizarprecio actualizarprecio) {

        try {

            actualizarprecioJpaController.create(actualizarprecio);

        } catch (Exception e) {
        }

    }

}
