/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "mercancia")
@NamedQueries({
    @NamedQuery(name = "Mercancia.findAll", query = "SELECT m FROM Mercancia m"),
    @NamedQuery(name = "Mercancia.findByIdmercancia", query = "SELECT m FROM Mercancia m WHERE m.idmercancia = :idmercancia"),
    @NamedQuery(name = "Mercancia.findByCodigo", query = "SELECT m FROM Mercancia m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Mercancia.findByNombre", query = "SELECT m FROM Mercancia m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Mercancia.findByStock", query = "SELECT m FROM Mercancia m WHERE m.stock = :stock"),
    @NamedQuery(name = "Mercancia.findByPrecioCompraUnidad", query = "SELECT m FROM Mercancia m WHERE m.precioCompraUnidad = :precioCompraUnidad"),
    @NamedQuery(name = "Mercancia.findByPrecioVentaUnidad", query = "SELECT m FROM Mercancia m WHERE m.precioVentaUnidad = :precioVentaUnidad"),
    @NamedQuery(name = "Mercancia.obtenerMercanciaPorProveedor", query = "SELECT m FROM Mercancia m INNER JOIN m.detalleCompraList dc INNER JOIN dc.compraMercanciaIdcompraMercancia cm INNER JOIN cm.proveedorIdproveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Mercancia.actualizarStock", query = "UPDATE Mercancia m SET m.stock = s WHERE m.idmercancia = :id")})
public class Mercancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmercancia")
    private Integer idmercancia;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "stock")
    private int stock;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio_compra_unidad")
    private BigDecimal precioCompraUnidad;
    @Basic(optional = false)
    @Column(name = "precio_venta_unidad")
    private BigDecimal precioVentaUnidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mercanciaIdmercancia")
    private List<Detallepedido> detallepedidoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mercanciaIdmercancia")
    private List<DetalleFactura> detalleFacturaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mercanciaIdmercancia")
    private List<DetalleCompra> detalleCompraList;

    public Mercancia() {
    }

    public Mercancia(Integer idmercancia) {
        this.idmercancia = idmercancia;
    }

    public Mercancia(Integer idmercancia, String codigo, String nombre, int stock, BigDecimal precioCompraUnidad, BigDecimal precioVentaUnidad) {
        this.idmercancia = idmercancia;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precioCompraUnidad = precioCompraUnidad;
        this.precioVentaUnidad = precioVentaUnidad;
    }

    public Integer getIdmercancia() {
        return idmercancia;
    }

    public void setIdmercancia(Integer idmercancia) {
        this.idmercancia = idmercancia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrecioCompraUnidad() {
        return precioCompraUnidad;
    }

    public void setPrecioCompraUnidad(BigDecimal precioCompraUnidad) {
        this.precioCompraUnidad = precioCompraUnidad;
    }

    public BigDecimal getPrecioVentaUnidad() {
        return precioVentaUnidad;
    }

    public void setPrecioVentaUnidad(BigDecimal precioVentaUnidad) {
        this.precioVentaUnidad = precioVentaUnidad;
    }

    public List<Detallepedido> getDetallepedidoList() {
        return detallepedidoList;
    }

    public void setDetallepedidoList(List<Detallepedido> detallepedidoList) {
        this.detallepedidoList = detallepedidoList;
    }

    public List<DetalleFactura> getDetalleFacturaList() {
        return detalleFacturaList;
    }

    public void setDetalleFacturaList(List<DetalleFactura> detalleFacturaList) {
        this.detalleFacturaList = detalleFacturaList;
    }

    public List<DetalleCompra> getDetalleCompraList() {
        return detalleCompraList;
    }

    public void setDetalleCompraList(List<DetalleCompra> detalleCompraList) {
        this.detalleCompraList = detalleCompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmercancia != null ? idmercancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mercancia)) {
            return false;
        }
        Mercancia other = (Mercancia) object;
        if ((this.idmercancia == null && other.idmercancia != null) || (this.idmercancia != null && !this.idmercancia.equals(other.idmercancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.Mercancia[ idmercancia=" + idmercancia + " ]";
    }

}
