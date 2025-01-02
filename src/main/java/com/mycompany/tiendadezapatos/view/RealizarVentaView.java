/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package com.mycompany.tiendadezapatos.view;

import com.mycompany.tiendadezapatos.dao.ClienteDAO;
import com.mycompany.tiendadezapatos.dao.DetalleFacturaDAO;
import com.mycompany.tiendadezapatos.dao.FacturaDAO;
import com.mycompany.tiendadezapatos.dao.MercanciaDAO;
import com.mycompany.tiendadezapatos.model.Cliente;
import com.mycompany.tiendadezapatos.model.DetalleFactura;
import com.mycompany.tiendadezapatos.model.Factura;
import com.mycompany.tiendadezapatos.model.Mercancia;
import static com.mycompany.tiendadezapatos.view.PrincipalView.panelPrincipal;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JJAB
 */
public class RealizarVentaView extends javax.swing.JInternalFrame {

    private DefaultTableModel dtmCliente;
    private DefaultTableModel dtmMercancia;
    private ClienteDAO clienteDAO = new ClienteDAO();
    private MercanciaDAO mercanciaDAO = new MercanciaDAO();
    private FacturaDAO facturaDAO = new FacturaDAO();

    /**
     * Creates new form RealizarVenta
     */
    public RealizarVentaView() {
        initComponents();
        crearTablaCliente();
        crearTablaMercancia();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnBuscarCedula = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregarMercancia = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMercancia = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotallSinIVA = new javax.swing.JLabel();
        lblSoloIVA = new javax.swing.JLabel();
        lblTotalPagar = new javax.swing.JLabel();
        btnRealizarVenta = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Cliente")));

        btnBuscarCedula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscarCedula.setText("Buscar");
        btnBuscarCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCedulaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setText("Cedula");

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblCliente);

        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnBuscarCedula)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Mercancia")));

        btnAgregarMercancia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnAgregarMercancia.setText("Buscar");
        btnAgregarMercancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMercanciaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel2.setText("Codigo");

        tblMercancia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblMercancia);

        btnEliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btnAgregarMercancia)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarMercancia, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Total a pagar"));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setText("Total sin iva %15:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setText("iva %15");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setText("Total a pagar:");

        lblTotallSinIVA.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTotallSinIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblSoloIVA.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblSoloIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTotalPagar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblTotalPagar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRealizarVenta.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnRealizarVenta.setText("Realizar Venta");
        btnRealizarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRealizarVenta)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotallSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSoloIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotallSinIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoloIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnRealizarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearTablaCliente() {
        dtmCliente = new DefaultTableModel();
        dtmCliente.addColumn("id");
        dtmCliente.addColumn("Cedula");
        dtmCliente.addColumn("Nombre");
        dtmCliente.addColumn("Telefono");
        dtmCliente.addColumn("Correo");

        tblCliente.setModel(dtmCliente);

        tblCliente.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblCliente.getColumnModel().getColumn(0).setMaxWidth(60);
        tblCliente.getColumnModel().getColumn(0).setMinWidth(60);
        tblCliente.getColumnModel().getColumn(0).setResizable(false);

    }

    private void crearTablaMercancia() {
        dtmMercancia = new DefaultTableModel();
        dtmMercancia.addColumn("id");
        dtmMercancia.addColumn("Codigo");
        dtmMercancia.addColumn("Nombre");
        dtmMercancia.addColumn("Cantidad");
        dtmMercancia.addColumn("precio");
        dtmMercancia.addColumn("total");

        tblMercancia.setModel(dtmMercancia);

    }


    private void btnBuscarCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCedulaActionPerformed

        String Cedula = txtCedula.getText();
        List<Cliente> c = clienteDAO.buscarClientePorCedula(Cedula);

        if (!c.isEmpty()) {
            tblCliente.removeAll();

            DefaultTableModel borrar = (DefaultTableModel) tblCliente.getModel();
            borrar.setRowCount(0);

            dtmCliente.addRow(new Object[]{c.get(0).getIdcliente(), c.get(0).getCedula(), c.get(0).getNombres(), c.get(0).getTelefono(),
                c.get(0).getCorreo()});
        } else {
            JOptionPane.showMessageDialog(this, "El cliente no existe", "", JOptionPane.ERROR_MESSAGE);

        }

        txtCedula.setText("");

    }//GEN-LAST:event_btnBuscarCedulaActionPerformed

    private void btnAgregarMercanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMercanciaActionPerformed

        String codigo = txtCodigo.getText();
        List<Mercancia> m = mercanciaDAO.buscarMercanciaPorCodigo(codigo);

        if (!m.isEmpty()) {
            String cantidad = JOptionPane.showInputDialog(this, "Ingrese una cantidad");

            if (cantidad != null) {

                if (validarCantidad(cantidad)) {

                    int cantidadVenta = Integer.parseInt(cantidad);
                    int stockBD = m.get(0).getStock();

                    if (cantidadVenta <= stockBD) {

                        int j = 0;
                        int cantidadFilas = tblMercancia.getRowCount();
                        boolean finCiclo = true;

                        while (j < cantidadFilas && finCiclo) {
                            if (codigo.equals(tblMercancia.getModel().getValueAt(j, 1))) {
                                finCiclo = false;

                            }
                            j++;
                        }

                        //se agrega la mercancia a la tabla
                        if (finCiclo) {

                            dtmMercancia.addRow(new Object[]{m.get(0).getIdmercancia(), m.get(0).getCodigo(), m.get(0).getNombre(),
                                cantidad, m.get(0).getPrecioVentaUnidad(),
                                calcularTotalIndivudual(cantidad, "" + m.get(0).getPrecioVentaUnidad())});
                            //
                            txtCodigo.setText("");

                            calcularTotalPedido();

                        } else {

                            //Si la mercancia ya fue agregada a la tabla anteriormente, se vuelve a validar que
                            //exista el stock y se edita la nuevca cantidad y se agrega el nuevo total individual
                            j--;
                            int cnat = Integer.parseInt("" + tblMercancia.getValueAt(j, 3));
                            int nuevoStock = cnat + cantidadVenta;

                            if (nuevoStock <= m.get(0).getStock()) {

                                dtmMercancia.setValueAt(nuevoStock, j, 3);
                                dtmMercancia.setValueAt(calcularTotalIndivudual("" + nuevoStock, "" + m.get(0).getPrecioVentaUnidad()), j, 5);

                                calcularTotalPedido();

                            } else {
                                JOptionPane.showMessageDialog(this, "No hay cantidad suficiente", "", JOptionPane.ERROR_MESSAGE);

                            }

                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "No hay cantidad suficiente", "", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Ingrese una cantidad correcta", "", JOptionPane.ERROR_MESSAGE);
                }

            }

        } else {
            JOptionPane.showMessageDialog(this, "La mercancia no existe", "", JOptionPane.ERROR_MESSAGE);

        }

    }//GEN-LAST:event_btnAgregarMercanciaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            DefaultTableModel eliminarFila = (DefaultTableModel) tblMercancia.getModel();
            eliminarFila.removeRow(tblMercancia.getSelectedRow());

            calcularTotalPedido();
        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRealizarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarVentaActionPerformed

        int idCliente = 1;

        if (tblCliente.getRowCount() == 1) {
            idCliente = Integer.parseInt("" + tblCliente.getValueAt(0, 0));
        }

        try {

            Factura factura = new Factura();
            factura.setClienteIdcliente(clienteDAO.obtenerClientePorID(idCliente));
            factura.setFechafactura(new Date());
            factura.setTotal(new BigDecimal(lblTotalPagar.getText()));
            boolean crearFactura = facturaDAO.crearFactura(factura);

            if (crearFactura) {

                for (int i = 0; i < tblMercancia.getRowCount(); i++) {

                    int idM = Integer.parseInt("" + tblMercancia.getValueAt(i, 0));

                    DetalleFactura detalleFactura = new DetalleFactura();
                    DetalleFacturaDAO detalleFacturaDAO = new DetalleFacturaDAO();

                    detalleFactura.setMercanciaIdmercancia(mercanciaDAO.obtenerMercanciaPorID(idM));
                    detalleFactura.setFacturaIdfactura(factura);
                    detalleFactura.setCantidad(Integer.parseInt("" + tblMercancia.getValueAt(i, 3)));
                    detalleFacturaDAO.crearDetalleFactura(detalleFactura);

                }

                panelPrincipal.removeAll();
                panelPrincipal.repaint();
                JOptionPane.showMessageDialog(this, "Se realizo la venta con exito", "FACTURA", JOptionPane.ERROR_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this, "No se puede realizar la factura", "", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se puede realizar la factura", "", JOptionPane.ERROR_MESSAGE);

        }


    }//GEN-LAST:event_btnRealizarVentaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            DefaultTableModel eliminarFila = (DefaultTableModel) tblCliente.getModel();
            eliminarFila.removeRow(0);
            txtCedula.setText("");

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    public BigDecimal calcularTotalIndivudual(String cantidad, String precio) {
        BigDecimal precioBig = new BigDecimal(precio);
        BigDecimal cantidadBig = new BigDecimal(cantidad);

        return precioBig.multiply(cantidadBig);
    }

    private void calcularTotalPedido() {
        BigDecimal total = new BigDecimal("0");

        for (int i = 0; i < tblMercancia.getRowCount(); i++) {
            total = total.add(new BigDecimal("" + tblMercancia.getModel().getValueAt(i, 5)));
        }

        BigDecimal soloIVA = new BigDecimal("" + total);
        BigDecimal soloIVAFormat = soloIVA.multiply(new BigDecimal("0.15")).setScale(2, RoundingMode.DOWN);

        BigDecimal totalPagar = new BigDecimal("" + soloIVAFormat);
        BigDecimal totalPagarFormat = totalPagar.add(total).setScale(2, RoundingMode.DOWN);

        lblTotallSinIVA.setText("" + total);

        lblSoloIVA.setText("" + soloIVAFormat);

        lblTotalPagar.setText("" + totalPagarFormat);

    }

    /*
     * Validacion Cantidad
     * expresion regular que permite cantidades de 
     * 1 hasta 99999
     */
    private boolean validarCantidad(String cantidad) {
        return cantidad.matches("[1-9]([0-9]{0,4})?");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMercancia;
    private javax.swing.JButton btnBuscarCedula;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRealizarVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSoloIVA;
    private javax.swing.JLabel lblTotalPagar;
    private javax.swing.JLabel lblTotallSinIVA;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTable tblMercancia;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
