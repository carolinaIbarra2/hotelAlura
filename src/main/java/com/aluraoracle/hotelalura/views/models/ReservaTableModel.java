package com.aluraoracle.hotelalura.views.models;

import com.aluraoracle.hotelalura.logica.Reserva;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ReservaTableModel extends AbstractTableModel{
    
    private List<Reserva> reservas;
    private List<Reserva> reservasFiltradas;
    private final String[]columnNames = {"Usuario", "Persona", "Fecha CheckIn", "Fecha CheckOut"};

    public ReservaTableModel(List<Reserva> reservas) {
        this.reservas = reservas;
        this.reservasFiltradas = new ArrayList<>(reservas);
    }

    @Override
    public int getRowCount() {
        return reservasFiltradas.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        Reserva reserva = reservasFiltradas.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return reserva.getUsuario().getNombre() +" " +reserva.getUsuario().getApellido();
            case 1:
                return reserva.getHuesped().getNombre()+" "+reserva.getHuesped().getApellido();
            case 2:
                return reserva.getFechaCheckIn();
            case 3:
                return reserva.getFechaCheckOut();
            
            default:
                return null;
        
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public  void filtrarPorNombre(String filtro){
        reservasFiltradas.clear();
        
        for (Reserva reserva : reservas) {
            String reservaBuscar = reserva.getHuesped().getNombre().toLowerCase()+" "+reserva.getHuesped().getApellido().toLowerCase();
            if (reservaBuscar.contains(filtro.toLowerCase())) {
                reservasFiltradas.add(reserva);
            }
        }
        
        fireTableDataChanged();
    }
    
}
