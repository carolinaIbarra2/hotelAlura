/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraoracle.hotelalura.views.models;

import com.aluraoracle.hotelalura.logica.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author guima
 */
public class UsuarioTableModel  extends AbstractTableModel {
    private List<Persona> usuarios;
    private List<Persona> usuariosFiltrados;
    private final String[] columnNames = {"Nombre de Usuario"};

    public UsuarioTableModel(List<Persona> usuarios) {
        this.usuarios = usuarios;
        this.usuariosFiltrados = new ArrayList<>(usuarios);
    }

    @Override
    public int getRowCount() {
        return usuariosFiltrados.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona usuario = usuariosFiltrados.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usuario.getUsuario().getUsername();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public  void filtrarPorNombre(String filtro){
        usuariosFiltrados.clear();
        
        for (Persona usuario : usuarios) {
            String username = usuario.getUsuario().getUsername().toLowerCase();
            if (username.contains(filtro.toLowerCase())) {
                usuariosFiltrados.add(usuario);
            }
        }
        
        fireTableDataChanged();
    }
}