/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "detallepedido")
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d"),
    @NamedQuery(name = "Detallepedido.findByIddetallepedido", query = "SELECT d FROM Detallepedido d WHERE d.iddetallepedido = :iddetallepedido"),
    @NamedQuery(name = "Detallepedido.findByCantidad", query = "SELECT d FROM Detallepedido d WHERE d.cantidad = :cantidad")})
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddetallepedido")
    private Integer iddetallepedido;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detallepedidoIddetallepedido")
    private List<Actualizarprecio> actualizarprecioList;
    @JoinColumn(name = "mercancia_idmercancia", referencedColumnName = "idmercancia")
    @ManyToOne(optional = false)
    private Mercancia mercanciaIdmercancia;
    @JoinColumn(name = "pedido_idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido pedidoIdpedido;

    public Detallepedido() {
    }

    public Detallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public Detallepedido(Integer iddetallepedido, int cantidad) {
        this.iddetallepedido = iddetallepedido;
        this.cantidad = cantidad;
    }

    public Integer getIddetallepedido() {
        return iddetallepedido;
    }

    public void setIddetallepedido(Integer iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Actualizarprecio> getActualizarprecioList() {
        return actualizarprecioList;
    }

    public void setActualizarprecioList(List<Actualizarprecio> actualizarprecioList) {
        this.actualizarprecioList = actualizarprecioList;
    }

    public Mercancia getMercanciaIdmercancia() {
        return mercanciaIdmercancia;
    }

    public void setMercanciaIdmercancia(Mercancia mercanciaIdmercancia) {
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    public Pedido getPedidoIdpedido() {
        return pedidoIdpedido;
    }

    public void setPedidoIdpedido(Pedido pedidoIdpedido) {
        this.pedidoIdpedido = pedidoIdpedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetallepedido != null ? iddetallepedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.iddetallepedido == null && other.iddetallepedido != null) || (this.iddetallepedido != null && !this.iddetallepedido.equals(other.iddetallepedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.Detallepedido[ iddetallepedido=" + iddetallepedido + " ]";
    }
    
}
