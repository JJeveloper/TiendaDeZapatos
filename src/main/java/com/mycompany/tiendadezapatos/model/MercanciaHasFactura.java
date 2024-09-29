/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "mercancia_has_factura")
@NamedQueries({
    @NamedQuery(name = "MercanciaHasFactura.findAll", query = "SELECT m FROM MercanciaHasFactura m"),
    @NamedQuery(name = "MercanciaHasFactura.findByFacturaIdfactura", query = "SELECT m FROM MercanciaHasFactura m WHERE m.mercanciaHasFacturaPK.facturaIdfactura = :facturaIdfactura"),
    @NamedQuery(name = "MercanciaHasFactura.findByMercanciaIdmercancia", query = "SELECT m FROM MercanciaHasFactura m WHERE m.mercanciaHasFacturaPK.mercanciaIdmercancia = :mercanciaIdmercancia"),
    @NamedQuery(name = "MercanciaHasFactura.findByCantidad", query = "SELECT m FROM MercanciaHasFactura m WHERE m.cantidad = :cantidad")})
public class MercanciaHasFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MercanciaHasFacturaPK mercanciaHasFacturaPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "factura_idfactura", referencedColumnName = "idfactura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Factura factura;
    @JoinColumn(name = "mercancia_idmercancia", referencedColumnName = "idmercancia", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mercancia mercancia;

    public MercanciaHasFactura() {
    }

    public MercanciaHasFactura(MercanciaHasFacturaPK mercanciaHasFacturaPK) {
        this.mercanciaHasFacturaPK = mercanciaHasFacturaPK;
    }

    public MercanciaHasFactura(MercanciaHasFacturaPK mercanciaHasFacturaPK, int cantidad) {
        this.mercanciaHasFacturaPK = mercanciaHasFacturaPK;
        this.cantidad = cantidad;
    }

    public MercanciaHasFactura(int facturaIdfactura, int mercanciaIdmercancia) {
        this.mercanciaHasFacturaPK = new MercanciaHasFacturaPK(facturaIdfactura, mercanciaIdmercancia);
    }

    public MercanciaHasFacturaPK getMercanciaHasFacturaPK() {
        return mercanciaHasFacturaPK;
    }

    public void setMercanciaHasFacturaPK(MercanciaHasFacturaPK mercanciaHasFacturaPK) {
        this.mercanciaHasFacturaPK = mercanciaHasFacturaPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Mercancia getMercancia() {
        return mercancia;
    }

    public void setMercancia(Mercancia mercancia) {
        this.mercancia = mercancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mercanciaHasFacturaPK != null ? mercanciaHasFacturaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MercanciaHasFactura)) {
            return false;
        }
        MercanciaHasFactura other = (MercanciaHasFactura) object;
        if ((this.mercanciaHasFacturaPK == null && other.mercanciaHasFacturaPK != null) || (this.mercanciaHasFacturaPK != null && !this.mercanciaHasFacturaPK.equals(other.mercanciaHasFacturaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.MercanciaHasFactura[ mercanciaHasFacturaPK=" + mercanciaHasFacturaPK + " ]";
    }
    
}
