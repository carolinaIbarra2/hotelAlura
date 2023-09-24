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
public class PersonaTableModel extends AbstractTableModel {
    private List<Persona> personas;
    private List<Persona> personasFiltradas;
    private final String[] columnNames = {"DNI", "Pasaporte", "Nombre", "Apellido", "Nacionalidad", "Teléfono", "Fecha de Nacimiento"};

    public PersonaTableModel(List<Persona> personas) {
        this.personas = personas;
        this.personasFiltradas = new ArrayList<>(personas);
    }

    @Override
    public int getRowCount() {
        return personasFiltradas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = personasFiltradas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return persona.getDni();
            case 1:
                return persona.getPasaporte();
            case 2:
                return persona.getNombre();
            case 3:
                return persona.getApellido();
            case 4:
                return persona.getNacionalidad();
            case 5:
                return persona.getTelefono();
            case 6:
                return persona.getFechaNacimiento();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public  void filtrarPorNombre(String filtro){
        personasFiltradas.clear();
        
        for (Persona persona : personas) {
            String username = persona.getNombre().toLowerCase()+" "+persona.getApellido().toLowerCase();
            if (username.contains(filtro.toLowerCase())) {
                personasFiltradas.add(persona);
            }
        }
        
        fireTableDataChanged();
    }
}
