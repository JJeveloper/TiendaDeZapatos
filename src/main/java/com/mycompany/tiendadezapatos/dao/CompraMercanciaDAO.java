/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.CompraMercanciaJpaController;
import com.mycompany.tiendadezapatos.model.CompraMercancia;

/**
 *
 * @author JJAB
 */
public class CompraMercanciaDAO {

    CompraMercanciaJpaController compraMercanciaJpaController = new CompraMercanciaJpaController();

    public void crearCompraMercancia(CompraMercancia compraMercancia) {
        try {
            compraMercanciaJpaController.create(compraMercancia);
        } catch (Exception e) {
        }
    }

    public CompraMercancia obtenercompraMercanciaPorID(int id) {
        return compraMercanciaJpaController.findCompraMercancia(id);
    }

}
