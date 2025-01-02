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
@Table(name = "detalle_compra")
@NamedQueries({
    @NamedQuery(name = "DetalleCompra.findAll", query = "SELECT d FROM DetalleCompra d"),
    @NamedQuery(name = "DetalleCompra.findByIddetalleCompra", query = "SELECT d FROM DetalleCompra d WHERE d.iddetalleCompra = :iddetalleCompra"),
    @NamedQuery(name = "DetalleCompra.findByCantidad", query = "SELECT d FROM DetalleCompra d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleCompra.obtenerPorMercancia", query = "SELECT d FROM DetalleCompra d WHERE d.mercanciaIdmercancia = :id")})
public class DetalleCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetalle_compra")
    private Integer iddetalleCompra;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "compra_mercancia_idcompra_mercancia", referencedColumnName = "idcompra_mercancia")
    @ManyToOne(optional = false)
    private CompraMercancia compraMercanciaIdcompraMercancia;
    @JoinColumn(name = "mercancia_idmercancia", referencedColumnName = "idmercancia")
    @ManyToOne(optional = false)
    private Mercancia mercanciaIdmercancia;

    public DetalleCompra() {
    }

    public DetalleCompra(Integer iddetalleCompra) {
        this.iddetalleCompra = iddetalleCompra;
    }

    public DetalleCompra(Integer iddetalleCompra, int cantidad) {
        this.iddetalleCompra = iddetalleCompra;
        this.cantidad = cantidad;
    }

    public Integer getIddetalleCompra() {
        return iddetalleCompra;
    }

    public void setIddetalleCompra(Integer iddetalleCompra) {
        this.iddetalleCompra = iddetalleCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public CompraMercancia getCompraMercanciaIdcompraMercancia() {
        return compraMercanciaIdcompraMercancia;
    }

    public void setCompraMercanciaIdcompraMercancia(CompraMercancia compraMercanciaIdcompraMercancia) {
        this.compraMercanciaIdcompraMercancia = compraMercanciaIdcompraMercancia;
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
        hash += (iddetalleCompra != null ? iddetalleCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCompra)) {
            return false;
        }
        DetalleCompra other = (DetalleCompra) object;
        if ((this.iddetalleCompra == null && other.iddetalleCompra != null) || (this.iddetalleCompra != null && !this.iddetalleCompra.equals(other.iddetalleCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.DetalleCompra[ iddetalleCompra=" + iddetalleCompra + " ]";
    }

}
