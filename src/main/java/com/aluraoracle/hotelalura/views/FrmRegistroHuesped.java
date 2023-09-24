package com.aluraoracle.hotelalura.views;

import com.aluraoracle.hotelalura.DAO.DetalleReservaDao;
import com.aluraoracle.hotelalura.DAO.HabitacionDao;
import com.aluraoracle.hotelalura.DAO.PersonaDao;
import com.aluraoracle.hotelalura.DAO.ReservaDao;
import com.aluraoracle.hotelalura.logica.DetalleReserva;
import com.aluraoracle.hotelalura.logica.Habitacion;
import com.aluraoracle.hotelalura.logica.Persona;
import com.aluraoracle.hotelalura.logica.Reserva;
import com.aluraoracle.hotelalura.logica.Usuario;
import com.aluraoracle.hotelalura.prueba.pruebaCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class FrmRegistroHuesped extends javax.swing.JFrame {

    private final Usuario usuarioSistema;
    
    private final PersonaDao personaDao;
    private final HabitacionDao habitacionDao;
    private final ReservaDao reservaDao;
    private final DetalleReservaDao detalleReservaDao;
    
    private List<Habitacion> habitaciones;
    
    private Habitacion habitacionSeleccionada;
    private Date fechaCheckIn;
    private Date fechaCheckOut;

    private Persona huespedDetalleRegistro;

    public FrmRegistroHuesped(Usuario usuarioSistema, Date fechaCheckIn, Date fechaCheckOut) {
        initComponents();
        this.usuarioSistema = usuarioSistema;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;

        personaDao = new PersonaDao();
        habitacionDao = new HabitacionDao();
        detalleReservaDao = new DetalleReservaDao();
        reservaDao = new ReservaDao();
        
        habitaciones = habitacionDao.listarHabitaciones();

        DefaultComboBoxModel<Habitacion> comboBoxModel = new DefaultComboBoxModel<>(habitaciones.toArray(new Habitacion[0]));
        cbxHabitacion.setModel(comboBoxModel);

        habitacionSeleccionada = (Habitacion) cbxHabitacion.getSelectedItem();
        txtPrecio.setEnabled(false);
        if (habitacionSeleccionada != null) {
            txtPrecio.setText(String.valueOf(habitacionSeleccionada.getTipoHabitacion().getPrecio()));
        }

        txtFechaNacimiento.getDateEditor().setEnabled(false);
        txtFechaNacimiento.setDateFormatString("yyyy-MM-dd");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPasaporte = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtNacionalidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        cbxHabitacion = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(51, 204, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registro.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aH-150px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 204, 255));
        jLabel3.setText("REGISTRO HUÉSPED");

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("DNI");

        txtPasaporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasaporteKeyTyped(evt);
            }
        });

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("PASAPORTE");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("NACIONALIDAD");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("TELEFONO");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        cbxHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHabitacionActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(102, 204, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/disquete.png"))); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(102, 204, 255));
        btnCerrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnCerrar.setText("CERRAR");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("NOMBRE");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("APELLIDO");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("FECHA DE NACIMIENTO");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("HABITACION");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("PRECIO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPasaporte, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                .addComponent(txtDni, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxHabitacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPasaporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 935, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        boolean validacionCampos = validarCamposDNIPasapote();
        if (validacionCampos) {
            if (habitacionSeleccionada != null) {
                boolean habitacionDisponible = detalleReservaDao.habitacionDisponible(habitacionSeleccionada.getId(), convertirFecha(fechaCheckIn), convertirFecha(fechaCheckOut));
                if (habitacionDisponible) {

                    Persona huespedRegistro = null;

                    if (huespedDetalleRegistro != null) {

                        huespedDetalleRegistro.setNombre(txtNombre.getText());
                        huespedDetalleRegistro.setApellido(txtApellido.getText());
                        huespedDetalleRegistro.setNacionalidad(txtNacionalidad.getText());
                        huespedDetalleRegistro.setTelefono(txtTelefono.getText());
                        huespedDetalleRegistro.setFechaNacimiento(txtFechaNacimiento.getDate());

                        huespedRegistro = personaDao.actualizarPersona(
                                huespedDetalleRegistro.getId(),
                                huespedDetalleRegistro);

                    } else {

                        Persona huespedNuevo = new Persona(
                                txtDni.getText(),
                                txtPasaporte.getText(),
                                txtNombre.getText(),
                                txtApellido.getText(),
                                txtNacionalidad.getText(),
                                txtTelefono.getText(),
                                txtFechaNacimiento.getDate(),
                                null,
                                true);
                        
                        huespedRegistro = personaDao.registrarPersona(huespedNuevo);
                    }

                    if (huespedRegistro != null) {
                        Reserva reserva = new Reserva(
                                personaDao.buscarPersonaidUsuario(usuarioSistema.getId()),
                                huespedRegistro,
                                fechaCheckIn,
                                fechaCheckOut,
                                true);

                        reserva = reservaDao.registrarReserva(reserva);

                        DetalleReserva detalleReserva = new DetalleReserva(
                                reserva,
                                habitacionSeleccionada,
                                habitacionSeleccionada.getTipoHabitacion().getPrecio(),
                                true);

                        DetalleReserva detalleReservaRegistro = detalleReservaDao.registrarDetalleReserva(detalleReserva);

                        if (detalleReservaRegistro != null) {
                            JOptionPane.showMessageDialog(null, "El REGISTRO SE REALIZÓ CON EXITO", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                            this.dispose();

                            FrmMenuPrincipal menuDeUsuario = new FrmMenuPrincipal(usuarioSistema);
                            menuDeUsuario.setVisible(true);
                            menuDeUsuario.setLocationRelativeTo(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "El REGISTRO NO SE REALIZÓ", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El REGISTRO DEL HUESPED NO SE REALIZÓ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "La habitación seleccionada no está disponible en el rango de fechas solicitado", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una habitacion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();

        FrmMenuPrincipal menuDeUsuario = new FrmMenuPrincipal(usuarioSistema);
        menuDeUsuario.setVisible(true);
        menuDeUsuario.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cbxHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHabitacionActionPerformed
        habitacionSeleccionada = (Habitacion) cbxHabitacion.getSelectedItem();
        if (habitacionSeleccionada != null) {
            txtPrecio.setText(String.valueOf(habitacionSeleccionada.getTipoHabitacion().getPrecio()));
        }
    }//GEN-LAST:event_cbxHabitacionActionPerformed

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        int tamanio = txtDni.getText().length();
        escribirSoloNumeros(evt, tamanio);
    }//GEN-LAST:event_txtDniKeyTyped

    private void txtPasaporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasaporteKeyTyped
        int tamanio = txtPasaporte.getText().length();
        escribirSoloNumeros(evt, tamanio);
    }//GEN-LAST:event_txtPasaporteKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int tamanio = txtNombre.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        int tamanio = txtNacionalidad.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int tamanio = txtTelefono.getText().length();
        escribirSoloNumeros(evt, tamanio);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        int tamanio = txtApellido.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private boolean validarCamposDNIPasapote() {
        boolean registro = true;

        if (!txtDni.getText().isEmpty()
                || !txtPasaporte.getText().isEmpty()) {
            if (huespedDetalleRegistro == null) {
                Persona validaDNI = personaDao.buscarPersonaPorDniOPasaporte(txtDni.getText(), null);
                Persona validaPasaporte = personaDao.buscarPersonaPorDniOPasaporte(txtPasaporte.getText(), null);

                if (validaDNI != null && txtDni.isEnabled()) {
                    registro = false;
                    mostrarOpcionesTraerPersona("DNI", validaDNI);
                }
                if (validaPasaporte != null && txtPasaporte.isEnabled()) {
                    registro = false;
                    mostrarOpcionesTraerPersona("pasaporte", validaPasaporte);
                }
            }
        }

        if (txtDni.getText().isEmpty()
                && txtPasaporte.getText().isEmpty()
                && txtNombre.getText().isEmpty()
                && txtNacionalidad.getText().isEmpty()
                && txtTelefono.getText().isEmpty()
                && txtFechaNacimiento.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Por favor diligencia todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            registro = false;
        }

        return registro;
    }

    private void mostrarOpcionesTraerPersona(String tipoDocumento, Persona huesped) {
        String[] opciones = {"SI", "NO"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "El Numero de " + tipoDocumento + " ya esta asociado a otra persona. ¿Desea llenar los campos con los datos de la persona?",
                "Acción",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == JOptionPane.YES_OPTION) {
            huespedDetalleRegistro = huesped;
            txtDni.setEnabled(false);
            txtPasaporte.setEnabled(false);
            txtDni.setText(huesped.getDni());
            txtPasaporte.setText(huesped.getPasaporte());
            txtNombre.setText(huesped.getNombre());
            txtApellido.setText(huesped.getApellido());
            txtNacionalidad.setText(huesped.getApellido());
            txtTelefono.setText(huesped.getTelefono());
            txtFechaNacimiento.setDate(huesped.getFechaNacimiento());
            txtNacionalidad.setText(huesped.getNacionalidad());
        } else if (seleccion == JOptionPane.NO_OPTION) {
            txtDni.setEnabled(true);
            txtPasaporte.setEnabled(true);
        }
    }

    private void escribirSoloNumeros(java.awt.event.KeyEvent evt, int tamanio) {

        char c = evt.getKeyChar();

        if (Character.isLetter(c) || tamanio >= 10) {
            evt.consume();
        }
    }

    private void escribirSoloLetras(java.awt.event.KeyEvent evt, int tamanio) {

        char c = evt.getKeyChar();

        if (!Character.isLetter(c) || tamanio >= 50) {
            evt.consume();
        }
    }

    public static Date convertirFecha(Date date) {
        Date fecha = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada = formato.format(date);
        try {
            fecha = formato.parse(fechaFormateada);
        } catch (ParseException ex) {
            Logger.getLogger(pruebaCalendar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Habitacion> cbxHabitacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDni;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPasaporte;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
