/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "detalle_factura")
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d"),
    @NamedQuery(name = "DetalleFactura.findByIddetalleFactura", query = "SELECT d FROM DetalleFactura d WHERE d.iddetalleFactura = :iddetalleFactura"),
    @NamedQuery(name = "DetalleFactura.findByCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.cantidad = :cantidad")})
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_factura")
    private Integer iddetalleFactura;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "factura_idfactura", referencedColumnName = "idfactura")
    @ManyToOne(optional = false)
    private Factura facturaIdfactura;
    @JoinColumn(name = "mercancia_idmercancia", referencedColumnName = "idmercancia")
    @ManyToOne(optional = false)
    private Mercancia mercanciaIdmercancia;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer iddetalleFactura) {
        this.iddetalleFactura = iddetalleFactura;
    }

    public DetalleFactura(Integer iddetalleFactura, int cantidad) {
        this.iddetalleFactura = iddetalleFactura;
        this.cantidad = cantidad;
    }

    public Integer getIddetalleFactura() {
        return iddetalleFactura;
    }

    public void setIddetalleFactura(Integer iddetalleFactura) {
        this.iddetalleFactura = iddetalleFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Factura getFacturaIdfactura() {
        return facturaIdfactura;
    }

    public void setFacturaIdfactura(Factura facturaIdfactura) {
        this.facturaIdfactura = facturaIdfactura;
    }

    public Mercancia getMercanciaIdmercancia() {
        return mercanciaIdmercancia;
    }

    public void setMercanciaIdmercancia(Mercancia mercanciaIdmercancia) {
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalleFactura != null ? iddetalleFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.iddetalleFactura == null && other.iddetalleFactura != null) || (this.iddetalleFactura != null && !this.iddetalleFactura.equals(other.iddetalleFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.DetalleFactura[ iddetalleFactura=" + iddetalleFactura + " ]";
    }
    
}
