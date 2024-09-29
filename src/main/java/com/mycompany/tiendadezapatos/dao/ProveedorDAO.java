/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.dao;

import com.mycompany.tiendadezapatos.controller.ProveedorJpaController;
import com.mycompany.tiendadezapatos.controller.exceptions.IllegalOrphanException;
import com.mycompany.tiendadezapatos.controller.exceptions.NonexistentEntityException;
import com.mycompany.tiendadezapatos.model.Proveedor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author JJAB
 */
public class ProveedorDAO {

    ProveedorJpaController proveedorJpaController = new ProveedorJpaController();
    Proveedor proveedor = new Proveedor();

    public List obtenerProveedor() {
        List<Proveedor> lista = new ArrayList<>();

        try {

            lista = proveedorJpaController.findProveedorEntities();

        } catch (Exception e) {
            System.out.println("Hubo un problema al obtener los datos " + e);

        }

        return lista;

    }

    public List<Proveedor> hacerBusquedaProveedor(String nombre) {

        List<Proveedor> lista = new ArrayList();
        try {

            Query query = proveedorJpaController.getEntityManager().createNamedQuery("Proveedor.BuscarProveedor");
            query.setParameter("nombre", "%" + nombre + "%");
            query.setParameter("ruc", "%" + nombre + "%");

            lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("no se puede cargar los datos DAO " + e);
        }
        return lista;
    }

    public boolean crearProveedor(String ruc, String nombre, String telefono, String correo) {
        try {

            if (validarRUC(ruc) && !nombre.trim().isEmpty() && validarTelefono(telefono) && validarCorreo(correo)) {

                proveedor.setRuc(ruc);

                Random r = new Random();

                proveedor.setCodigoproveedor(r.nextInt(10000 + 1, 99999 + 1) + "");

                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                proveedor.setCorreo(correo);
                proveedor.setFechaderegistro(new Date());

                proveedorJpaController.create(proveedor);

                return true;
            }

        } catch (Exception e) {
            System.out.println("no se pudo guardar los datos " + e);
            JOptionPane.showMessageDialog(null, "No se pudo guardar los datos");

        }

        return false;
    }

    public void actualizarProveedor(int id, String nombre, String telefono, String correo) {

        try {

            Proveedor actualizarProveedor = proveedorJpaController.findProveedor(id);
            actualizarProveedor.setNombre(nombre);
            actualizarProveedor.setTelefono(telefono);
            actualizarProveedor.setCorreo(correo);
            proveedorJpaController.edit(actualizarProveedor);

        } catch (NonexistentEntityException ex) {
            System.out.println("NonexistentEntityException: No se pudo actualizar " + ex);
        } catch (Exception ex) {
            System.out.println("Exception: No se pudo actualizar " + ex);
        }

    }

    public boolean eliminarProveedor(int id) {

        try {

            proveedorJpaController.destroy(id);

            return true;

        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            System.out.println("No se puede eliminar el proveedor DAO" + ex);
        }
        return false;
    }


    /*
     * Validacion RUC
     * expresion regular que permite numeros de 0 hasta el 9
     * el RUC termina con 001
     * longitud de 12 numeros que corresponden a una cedula mas 001
     */
    private boolean validarRUC(String RUC) {
        return RUC.matches("^(0[1-9]|1[0-9]|2[0-4]|30)(\\d{8})001");
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
