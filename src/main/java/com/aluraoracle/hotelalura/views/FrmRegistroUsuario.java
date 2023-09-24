package com.aluraoracle.hotelalura.views;

import com.aluraoracle.hotelalura.DAO.PersonaDao;
import com.aluraoracle.hotelalura.DAO.UsuarioDao;
import com.aluraoracle.hotelalura.logica.Persona;
import com.aluraoracle.hotelalura.logica.Usuario;
import com.aluraoracle.hotelalura.views.models.UsuarioTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FrmRegistroUsuario extends javax.swing.JFrame {

    private Persona usuarioSeleccionado;
    private List<Persona> usuarios;
    private final PersonaDao personaDao;
    private final UsuarioDao usuarioDao;
    private UsuarioTableModel model;
    private final Usuario usuarioSistema;

    public FrmRegistroUsuario(Usuario usuario) {
        initComponents();
        usuarioSistema = usuario;
        listenerTxtBuscar();
        personaDao = new PersonaDao();
        usuarioDao = new UsuarioDao();
        inhabilitarPanel(false);
        listarUsuarios();
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
                String textoBusqueda = txtBuscar.getText();
                model.filtrarPorNombre(textoBusqueda);
            }
        });
    }

    private void listarUsuarios() {
        usuarios = personaDao.listarTodasLasPersonasConUsuarios();
        model = new UsuarioTableModel(usuarios);
        tblUsuarios.setModel(model);
    }

    private void mostrarOpcionesPersonalizadas() {
        String[] opciones = {"Actualizar", "Eliminar"};
        int seleccion = JOptionPane.showOptionDialog(
                this,
                "¿Qué acción desea realizar?",
                "Acción",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == JOptionPane.YES_OPTION) {
            inhabilitarPanel(true);
            cargarDatosUsuarioAlSeleccionarActualizar(usuarioSeleccionado);
        } else if (seleccion == JOptionPane.NO_OPTION) {
            eliminarUsuario(usuarioSeleccionado.getId(), usuarioSeleccionado.getUsuario().getId());
            listarUsuarios();
        }
    }

    private void eliminarUsuario(Long idPersona, Long idUsuario) {
        boolean personaEliminada = personaDao.eliminarPersona(idPersona);
        boolean usuarioEliminada = usuarioDao.eliminarUsuario(idUsuario);

        if (personaEliminada && usuarioEliminada) {
            JOptionPane.showMessageDialog(null,
                    "SE ELIMINO EL USUARIO SELECCIONADO",
                    "INFORMACIÓN",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "NO SE PUDO ELIMINAR AL USUARIO SELECCIONADO",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private boolean validarCamposDNIPasapoteUsername(Persona persona) {
        boolean registro = true;

        Long idPersona = null;
        Long idUsuario = null;

        if (persona != null) {
            idPersona = persona.getId();
            idUsuario = persona.getUsuario().getId();
        }

        if (!txtDni.getText().isEmpty()
                && !txtPasaporte.getText().isEmpty()
                && !txtNombre.getText().isEmpty()
                && !txtApellido.getText().isEmpty()
                && !txtTelefono.getText().isEmpty()
                && txtFechaNacimiento.getDate() != null) {
            
            if (txtContrasena1.getText().equals(txtContrasena2.getText())) {

                Persona validaDNI = personaDao.buscarPersonaPorDniOPasaporte(txtDni.getText(), idPersona);
                Persona validaPasaporte = personaDao.buscarPersonaPorDniOPasaporte(txtPasaporte.getText(), idPersona);
                Usuario validaUsername = usuarioDao.buscarUsuarioPorUsername(txtNombreUsuario.getText(), idUsuario);

                if (validaDNI != null) {
                    registro = false;
                    JOptionPane.showMessageDialog(null, "El Numero de DNI ya esta asociado a otra persona", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (validaPasaporte != null) {
                    registro = false;
                    JOptionPane.showMessageDialog(null, "El Numero de Pasaporte ya esta asociado a otra persona", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (validaUsername != null) {
                    registro = false;
                    JOptionPane.showMessageDialog(null, "El Nombre de Usuario ya esta asociado a otra persona", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Las contraseñas no coinciden",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

                registro = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor diligencia todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            registro = false;
        }

        return registro;
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
        lblTitulo = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        lblPasaporte = new javax.swing.JLabel();
        txtPasaporte = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblNacionalidad = new javax.swing.JLabel();
        txtNacionalidad = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        txtContrasena1 = new javax.swing.JTextField();
        lblContrasena2 = new javax.swing.JLabel();
        txtContrasena2 = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnRegistrarPersona = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnRegresarVentana = new javax.swing.JButton();
        lblDni1 = new javax.swing.JLabel();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(102, 204, 255));
        lblTitulo.setText("DATOS DE USUARIO");

        lblDni.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDni.setForeground(new java.awt.Color(102, 102, 102));
        lblDni.setText("DNI:");

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });

        lblPasaporte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPasaporte.setForeground(new java.awt.Color(102, 102, 102));
        lblPasaporte.setText("PASAPORTE:");

        txtPasaporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasaporteKeyTyped(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(102, 102, 102));
        lblNombre.setText("NOMBRE:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lblApellido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(102, 102, 102));
        lblApellido.setText("APELLIDO:");

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        lblNacionalidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNacionalidad.setForeground(new java.awt.Color(102, 102, 102));
        lblNacionalidad.setText("NACIONALIDAD:");

        txtNacionalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNacionalidadKeyTyped(evt);
            }
        });

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(102, 102, 102));
        lblTelefono.setText("TELEFONO:");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lblFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFechaNacimiento.setForeground(new java.awt.Color(102, 102, 102));
        lblFechaNacimiento.setText("FECHA DE NACIMIENTO: ");

        lblNombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(102, 102, 102));
        lblNombreUsuario.setText("NOMBRE DE USUARIO:");

        txtNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioKeyTyped(evt);
            }
        });

        lblContrasena.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(102, 102, 102));
        lblContrasena.setText("CONTRASEÑA:");

        lblContrasena2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblContrasena2.setForeground(new java.awt.Color(102, 102, 102));
        lblContrasena2.setText("DIGITE NUEVAMENTE CONTRASEÑA:");

        btnGuardar.setBackground(new java.awt.Color(102, 204, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtBuscar.setToolTipText("");

        btnRegistrarPersona.setBackground(new java.awt.Color(102, 204, 255));
        btnRegistrarPersona.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrarPersona.setText("REGISTRAR NUEVO USUARIO");
        btnRegistrarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPersonaActionPerformed(evt);
            }
        });

        tblUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        tblUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        tblUsuarios.setSelectionBackground(new java.awt.Color(102, 204, 255));
        tblUsuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        btnRegresarVentana.setBackground(new java.awt.Color(255, 255, 255));
        btnRegresarVentana.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back-arrow.png"))); // NOI18N
        btnRegresarVentana.setText("jButton1");
        btnRegresarVentana.setBorder(null);
        btnRegresarVentana.setIconTextGap(-6);
        btnRegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarVentanaActionPerformed(evt);
            }
        });

        lblDni1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDni1.setForeground(new java.awt.Color(102, 102, 102));
        lblDni1.setText("BUSCAR:");

        txtFechaNacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaNacimientoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCancelar))
                            .addComponent(txtContrasena2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblNombreUsuario)
                                        .addComponent(lblFechaNacimiento)
                                        .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre)
                                        .addComponent(lblPasaporte)
                                        .addComponent(lblApellido)
                                        .addComponent(txtPasaporte)
                                        .addComponent(txtNombre)
                                        .addComponent(txtApellido)
                                        .addComponent(lblNacionalidad)
                                        .addComponent(txtNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTelefono)
                                        .addComponent(txtNombreUsuario)
                                        .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtContrasena1)
                                        .addComponent(txtDni, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                    .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblContrasena2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                            .addComponent(txtBuscar)
                            .addComponent(btnRegistrarPersona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDni1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(223, 223, 223)
                        .addComponent(lblTitulo)))
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(lblDni1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPasaporte, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPasaporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTelefono)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFechaNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContrasena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasena1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblContrasena2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrasena2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRegistrarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        txtBuscar.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPersonaActionPerformed
        inhabilitarPanel(true);
        usuarioSeleccionado = null;
        limpiarDatosUsuario();
    }//GEN-LAST:event_btnRegistrarPersonaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitarPanel(false);
        usuarioSeleccionado = null;
        limpiarDatosUsuario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        boolean validarRegistro = validarCamposDNIPasapoteUsername(usuarioSeleccionado);// obtenemos las validaciones en una variable

        if (validarRegistro) { // valida si cumple todas las validaciones

            Usuario usuarioNuevo = new Usuario(
                    txtNombreUsuario.getText(),
                    txtContrasena1.getText(),
                    true);

            Date fechaNacimiento = null;
            try {
                fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(txtFechaNacimiento.getDateFormatString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Persona personaNueva = new Persona(
                    txtDni.getText(),
                    txtPasaporte.getText(),
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtNacionalidad.getText(),
                    txtTelefono.getText(),
                    fechaNacimiento,
                    usuarioNuevo,
                    true);

            if (usuarioSeleccionado == null) {
                Usuario usuarioParaRegistrar = usuarioDao.registrarUsuario(usuarioNuevo);
                Persona personaParaRegistrar = personaDao.registrarPersona(personaNueva);
                validarRegistroExitoso(true, personaParaRegistrar, usuarioParaRegistrar);
                limpiarDatosUsuario();
                inhabilitarPanel(false);
                listarUsuarios();
            } else {
                Usuario usuarioParaActualizar = usuarioDao.actualizarUsuarioYContraseña(
                        usuarioSeleccionado.getUsuario().getId(),
                        usuarioNuevo.getUsername(),
                        usuarioNuevo.getPassword());

                Persona personaParaActualizar = personaDao.actualizarPersona(
                        usuarioSeleccionado.getId(),
                        personaNueva);
                validarRegistroExitoso(false, personaParaActualizar, usuarioParaActualizar);
                limpiarDatosUsuario();
                usuarioSeleccionado = null;
                inhabilitarPanel(false);
                listarUsuarios();
            }

        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void validarRegistroExitoso(boolean tipo, Persona persona, Usuario usuario) {
        boolean respuestaPersona = (persona != null);
        boolean respuestaUsuario = (usuario != null);
        String mensaje = (tipo ? "EL REGISTRO" : "LA ACTUALIZACIÓN");

        if (respuestaUsuario && respuestaPersona) {
            JOptionPane.showMessageDialog(null,
                    mensaje + " SE REALIZÓ CON EXITO",
                    "INFORMACIÓN",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    mensaje + " NO SE REALIZÓ",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        if (evt.getClickCount() == 2) { // Doble clic
            int filaSeleccionada = tblUsuarios.getSelectedRow();
            if (filaSeleccionada != -1) { // Verifica que se haya seleccionado una fila
                // Obtén el objeto Persona de la fila seleccionada
                usuarioSeleccionado = usuarios.get(filaSeleccionada);
                mostrarOpcionesPersonalizadas();
            }
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnRegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarVentanaActionPerformed
        this.dispose();

        FrmMenuPrincipal menuDeUsuario = new FrmMenuPrincipal(usuarioSistema);
        menuDeUsuario.setVisible(true);
        menuDeUsuario.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnRegresarVentanaActionPerformed

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

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        int tamanio = txtApellido.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtNacionalidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNacionalidadKeyTyped
        int tamanio = txtNacionalidad.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtNacionalidadKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int tamanio = txtTelefono.getText().length();
        escribirSoloNumeros(evt, tamanio);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtFechaNacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaNacimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoKeyTyped

    private void txtNombreUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioKeyTyped
        int tamanio = txtNombre.getText().length();
        escribirSoloLetras(evt, tamanio);
    }//GEN-LAST:event_txtNombreUsuarioKeyTyped

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

    private void inhabilitarPanel(boolean flag) {
        //panel izquierda
        btnCancelar.setEnabled(flag);
        btnGuardar.setEnabled(flag);
        txtDni.setEnabled(flag);
        txtPasaporte.setEnabled(flag);
        txtNombre.setEnabled(flag);
        txtApellido.setEnabled(flag);
        txtNacionalidad.setEnabled(flag);
        txtTelefono.setEnabled(flag);
        txtFechaNacimiento.setEnabled(flag);
        txtNombreUsuario.setEnabled(flag);
        txtContrasena1.setEnabled(flag);
        txtContrasena2.setEnabled(flag);

        // panel derecha
        btnRegistrarPersona.setEnabled(!flag);
        txtBuscar.setEnabled(!flag);
        tblUsuarios.setEnabled(!flag);

    }

    private void cargarDatosUsuarioAlSeleccionarActualizar(Persona usuario) {
        txtDni.setText(usuario.getDni());
        txtPasaporte.setText(usuario.getPasaporte());
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        txtNacionalidad.setText(usuario.getNacionalidad());
        txtTelefono.setText(usuario.getTelefono());
        txtFechaNacimiento.setDate(usuario.getFechaNacimiento());
        txtNombreUsuario.setText(usuario.getUsuario().getUsername());
        txtContrasena1.setText(usuario.getUsuario().getPassword());
        txtContrasena2.setText(usuario.getUsuario().getPassword());
    }

    private void limpiarDatosUsuario() {
        txtDni.setText("");
        txtPasaporte.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtNacionalidad.setText("");
        txtTelefono.setText("");
        txtFechaNacimiento.setDate(null);
        txtNombreUsuario.setText("");
        txtContrasena1.setText("");
        txtContrasena2.setText("");
    }

    public boolean validarFecha(String date) {
        boolean respuesta = true;
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            respuesta= false;
        }
        return respuesta;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegistrarPersona;
    private javax.swing.JButton btnRegresarVentana;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblContrasena2;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblDni1;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNacionalidad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblPasaporte;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContrasena1;
    private javax.swing.JTextField txtContrasena2;
    private javax.swing.JTextField txtDni;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNacionalidad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtPasaporte;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
