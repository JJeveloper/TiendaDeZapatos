/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author JJAB
 */
@Embeddable
public class MercanciaHasFacturaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "factura_idfactura")
    private int facturaIdfactura;
    @Basic(optional = false)
    @Column(name = "mercancia_idmercancia")
    private int mercanciaIdmercancia;

    public MercanciaHasFacturaPK() {
    }

    public MercanciaHasFacturaPK(int facturaIdfactura, int mercanciaIdmercancia) {
        this.facturaIdfactura = facturaIdfactura;
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    public int getFacturaIdfactura() {
        return facturaIdfactura;
    }

    public void setFacturaIdfactura(int facturaIdfactura) {
        this.facturaIdfactura = facturaIdfactura;
    }

    public int getMercanciaIdmercancia() {
        return mercanciaIdmercancia;
    }

    public void setMercanciaIdmercancia(int mercanciaIdmercancia) {
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) facturaIdfactura;
        hash += (int) mercanciaIdmercancia;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MercanciaHasFacturaPK)) {
            return false;
        }
        MercanciaHasFacturaPK other = (MercanciaHasFacturaPK) object;
        if (this.facturaIdfactura != other.facturaIdfactura) {
            return false;
        }
        if (this.mercanciaIdmercancia != other.mercanciaIdmercancia) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.MercanciaHasFacturaPK[ facturaIdfactura=" + facturaIdfactura + ", mercanciaIdmercancia=" + mercanciaIdmercancia + " ]";
    }
    
}
