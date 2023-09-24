package com.aluraoracle.hotelalura.views;

import com.aluraoracle.hotelalura.DAO.PersonaDao;
import com.aluraoracle.hotelalura.DAO.ReservaDao;
import com.aluraoracle.hotelalura.logica.Persona;
import com.aluraoracle.hotelalura.logica.Reserva;
import com.aluraoracle.hotelalura.logica.Usuario;
import com.aluraoracle.hotelalura.views.models.PersonaTableModel;
import com.aluraoracle.hotelalura.views.models.ReservaTableModel;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FrmBusquedas extends javax.swing.JFrame {

    private final Usuario usuarioSistema;
    private List<Persona> huespedes;
    private List<Reserva> reservas;
    private PersonaDao personaDao;
    private ReservaDao reservaDao;
    private PersonaTableModel modelHuesped;
    private ReservaTableModel modelReserva;
    private FrmRegistroUsuario registroUsuario;

    public FrmBusquedas(Usuario usuarioSistema) {
        initComponents();
        this.usuarioSistema = usuarioSistema;
        listenerTxtBuscar();
        personaDao = new PersonaDao();
        reservaDao = new ReservaDao();
        listarUsuarios();
        listarReserva();
    }

    private void listarReserva() {
        reservas = reservaDao.listarTodasLasReservas();
        modelReserva = new ReservaTableModel(reservas);
        tblReservas.setModel(modelReserva);
    }

    private void listarUsuarios() {
        huespedes = personaDao.listarTodasLasPersonas();
        modelHuesped = new PersonaTableModel(huespedes);
        tblHuespedes.setModel(modelHuesped);
    }

    private void listenerTxtBuscar() {

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtro();
            }

            private void filtro() {

                if (jtpTabla.getSelectedIndex() == 0) {
                    String textoBusquedaSB = txtBuscar.getText();
                    modelHuesped.filtrarPorNombre(textoBusquedaSB);
                }
                if (jtpTabla.getSelectedIndex() == 1) {
                    String textoBusquedaSB = txtBuscar.getText();
                    modelReserva.filtrarPorNombre(textoBusquedaSB);
                }
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        jtpTabla = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHuespedes = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 255));
        jLabel3.setText("SISTEMA DE BUSQUEDA");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Ha-100px.png"))); // NOI18N

        txtBuscar.setToolTipText("");

        btnCerrar.setBackground(new java.awt.Color(102, 204, 255));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jtpTabla.setToolTipText("");
        jtpTabla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtpTablaStateChanged(evt);
            }
        });

        tblHuespedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblHuespedes);

        jtpTabla.addTab("Huespedes", jScrollPane3);

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblReservas);

        jtpTabla.addTab("Reservas", jScrollPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addGap(81, 81, 81)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jtpTabla.getAccessibleContext().setAccessibleName("Huespedes");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();

        FrmMenuPrincipal menuPrincipal = new FrmMenuPrincipal(usuarioSistema);
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jtpTablaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpTablaStateChanged
        if (jtpTabla.getSelectedIndex() == 0 || jtpTabla.getSelectedIndex() == 1) {
            txtBuscar.setText("");
        }
    }//GEN-LAST:event_jtpTablaStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jtpTabla;
    private javax.swing.JTable tblHuespedes;
    private javax.swing.JTable tblReservas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
