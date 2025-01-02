/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.ClienteJpaController;
import com.mycompany.tiendadezapatos.model.Cliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author JJAB
 */
public class ClienteDAO {

    ClienteJpaController clienteJpaController = new ClienteJpaController();

    public boolean crearCliente(String cedula, String nombre, String telefono, String correo) {

        try {

            if (validarCedula(cedula) && !nombre.trim().isEmpty() && validarTelefono(telefono) && validarCorreo(correo)) {

                Cliente cliente = new Cliente();
                cliente.setCedula(cedula);
                cliente.setNombres(nombre);
                cliente.setTelefono(telefono);
                cliente.setCorreo(correo);
                cliente.setFechaderegistro(new Date());

                clienteJpaController.create(cliente);
                return true;

            }

        } catch (Exception e) {
            System.out.println("No se puede crear el cliente DAO: " + e);
        }
        return false;

    }

    public List<Cliente> buscarClientePorCedula(String cedula) {
        List<Cliente> clienteList = new ArrayList<>();
        try {

            Query q = clienteJpaController.getEntityManager().createNamedQuery("Cliente.findByCedula");
            q.setParameter("cedula", cedula);
            clienteList = q.getResultList();

        } catch (Exception e) {
            System.out.println("No existe la cedula del cliente");
        }

        return clienteList;
    }

    public Cliente obtenerClientePorID(int id) {
        return clienteJpaController.findCliente(id);
    }


    /*
     * Validacion Cedula
     * con expresion regular que permite numeros de 0 hasta el 9
     * longitud de 10 numeros que corresponden a una cedula
     */
    private boolean validarCedula(String cedula) {
        return cedula.matches("(0[1-9]|1[0-9]|2[0-4]|30)([0-9]{8})");
    }


    /*
     * Validacion Telefono
     * Los numeros de celular inician con 09
     * expresion regular que permite numeros de 0 hasta el 9
     * longitud de 10 numeros que corresponden a un numero de celular
     */
    private boolean validarTelefono(String telefono) {
        return telefono.matches("^(09)(\\d{8})$");
    }

    /*
     * Validacion correo electronico
     * con expresion regular
     * comprueba que la dirección de correo electrónico tenga un usuario
     * (parte antes de @), un dominio (parte después de @),
     * y un dominio de nivel superior (TLD) válido (como .com, .org, .es, etc.
     */
    private boolean validarCorreo(String correo) {
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(?:\\.[a-zA-Z]{2,})?$");
    }

}
