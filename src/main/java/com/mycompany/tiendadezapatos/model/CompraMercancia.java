/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tiendadezapatos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JJAB
 */
@Entity
@Table(name = "compra_mercancia")
@NamedQueries({
    @NamedQuery(name = "CompraMercancia.findAll", query = "SELECT c FROM CompraMercancia c"),
    @NamedQuery(name = "CompraMercancia.findByIdcompraMercancia", query = "SELECT c FROM CompraMercancia c WHERE c.idcompraMercancia = :idcompraMercancia"),
    @NamedQuery(name = "CompraMercancia.findByTotal", query = "SELECT c FROM CompraMercancia c WHERE c.total = :total"),
    @NamedQuery(name = "CompraMercancia.findByFechacompra", query = "SELECT c FROM CompraMercancia c WHERE c.fechacompra = :fechacompra")})
public class CompraMercancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompra_mercancia")
    private Integer idcompraMercancia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @Basic(optional = false)
    @Column(name = "fechacompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacompra;
    @JoinColumn(name = "proveedor_idproveedor", referencedColumnName = "idproveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedorIdproveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compraMercanciaIdcompraMercancia")
    private List<DetalleCompra> detalleCompraList;

    public CompraMercancia() {
    }

    public CompraMercancia(Integer idcompraMercancia) {
        this.idcompraMercancia = idcompraMercancia;
    }

    public CompraMercancia(Integer idcompraMercancia, BigDecimal total, Date fechacompra) {
        this.idcompraMercancia = idcompraMercancia;
        this.total = total;
        this.fechacompra = fechacompra;
    }

    public Integer getIdcompraMercancia() {
        return idcompraMercancia;
    }

    public void setIdcompraMercancia(Integer idcompraMercancia) {
        this.idcompraMercancia = idcompraMercancia;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public Proveedor getProveedorIdproveedor() {
        return proveedorIdproveedor;
    }

    public void setProveedorIdproveedor(Proveedor proveedorIdproveedor) {
        this.proveedorIdproveedor = proveedorIdproveedor;
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
        hash += (idcompraMercancia != null ? idcompraMercancia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompraMercancia)) {
            return false;
        }
        CompraMercancia other = (CompraMercancia) object;
        if ((this.idcompraMercancia == null && other.idcompraMercancia != null) || (this.idcompraMercancia != null && !this.idcompraMercancia.equals(other.idcompraMercancia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tiendadezapatos.model.CompraMercancia[ idcompraMercancia=" + idcompraMercancia + " ]";
    }
    
}
