/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "actualizarprecio")
@NamedQueries({
    @NamedQuery(name = "Actualizarprecio.findAll", query = "SELECT a FROM Actualizarprecio a"),
    @NamedQuery(name = "Actualizarprecio.findByActualizarprecio", query = "SELECT a FROM Actualizarprecio a WHERE a.actualizarprecio = :actualizarprecio"),
    @NamedQuery(name = "Actualizarprecio.findByAntiguopreciocompra", query = "SELECT a FROM Actualizarprecio a WHERE a.antiguopreciocompra = :antiguopreciocompra"),
    @NamedQuery(name = "Actualizarprecio.findByAntiguoprecioventa", query = "SELECT a FROM Actualizarprecio a WHERE a.antiguoprecioventa = :antiguoprecioventa"),
    @NamedQuery(name = "Actualizarprecio.findByNuevopreciocompra", query = "SELECT a FROM Actualizarprecio a WHERE a.nuevopreciocompra = :nuevopreciocompra"),
    @NamedQuery(name = "Actualizarprecio.findByNuevoprecioventa", query = "SELECT a FROM Actualizarprecio a WHERE a.nuevoprecioventa = :nuevoprecioventa"),
    @NamedQuery(name = "Actualizarprecio.findByMercanciaIdmercancia", query = "SELECT a FROM Actualizarprecio a WHERE a.mercanciaIdmercancia = :mercanciaIdmercancia")})
public class Actualizarprecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "actualizarprecio")
    private Integer actualizarprecio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "antiguopreciocompra")
    private BigDecimal antiguopreciocompra;
    @Basic(optional = false)
    @Column(name = "antiguoprecioventa")
    private BigDecimal antiguoprecioventa;
    @Basic(optional = false)
    @Column(name = "nuevopreciocompra")
    private BigDecimal nuevopreciocompra;
    @Basic(optional = false)
    @Column(name = "nuevoprecioventa")
    private BigDecimal nuevoprecioventa;
    @Basic(optional = false)
    @Column(name = "mercancia_idmercancia")
    private int mercanciaIdmercancia;
    @JoinColumn(name = "detallepedido_iddetallepedido", referencedColumnName = "iddetallepedido")
    @ManyToOne(optional = false)
    private Detallepedido detallepedidoIddetallepedido;

    public Actualizarprecio() {
    }

    public Actualizarprecio(Integer actualizarprecio) {
        this.actualizarprecio = actualizarprecio;
    }

    public Actualizarprecio(Integer actualizarprecio, BigDecimal antiguopreciocompra, BigDecimal antiguoprecioventa, BigDecimal nuevopreciocompra, BigDecimal nuevoprecioventa, int mercanciaIdmercancia) {
        this.actualizarprecio = actualizarprecio;
        this.antiguopreciocompra = antiguopreciocompra;
        this.antiguoprecioventa = antiguoprecioventa;
        this.nuevopreciocompra = nuevopreciocompra;
        this.nuevoprecioventa = nuevoprecioventa;
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    public Integer getActualizarprecio() {
        return actualizarprecio;
    }

    public void setActualizarprecio(Integer actualizarprecio) {
        this.actualizarprecio = actualizarprecio;
    }

    public BigDecimal getAntiguopreciocompra() {
        return antiguopreciocompra;
    }

    public void setAntiguopreciocompra(BigDecimal antiguopreciocompra) {
        this.antiguopreciocompra = antiguopreciocompra;
    }

    public BigDecimal getAntiguoprecioventa() {
        return antiguoprecioventa;
    }

    public void setAntiguoprecioventa(BigDecimal antiguoprecioventa) {
        this.antiguoprecioventa = antiguoprecioventa;
    }

    public BigDecimal getNuevopreciocompra() {
        return nuevopreciocompra;
    }

    public void setNuevopreciocompra(BigDecimal nuevopreciocompra) {
        this.nuevopreciocompra = nuevopreciocompra;
    }

    public BigDecimal getNuevoprecioventa() {
        return nuevoprecioventa;
    }

    public void setNuevoprecioventa(BigDecimal nuevoprecioventa) {
        this.nuevoprecioventa = nuevoprecioventa;
    }

    public int getMercanciaIdmercancia() {
        return mercanciaIdmercancia;
    }

    public void setMercanciaIdmercancia(int mercanciaIdmercancia) {
        this.mercanciaIdmercancia = mercanciaIdmercancia;
    }

    public Detallepedido getDetallepedidoIddetallepedido() {
        return detallepedidoIddetallepedido;
    }

    public void setDetallepedidoIddetallepedido(Detallepedido detallepedidoIddetallepedido) {
        this.detallepedidoIddetallepedido = detallepedidoIddetallepedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actualizarprecio != null ? actualizarprecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actualizarprecio)) {
            return false;
        }
        Actualizarprecio other = (Actualizarprecio) object;
        if ((this.actualizarprecio == null && other.actualizarprecio != null) || (this.actualizarprecio != null && !this.actualizarprecio.equals(other.actualizarprecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.Actualizarprecio[ actualizarprecio=" + actualizarprecio + " ]";
    }
    
}
