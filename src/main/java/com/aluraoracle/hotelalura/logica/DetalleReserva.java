package com.aluraoracle.hotelalura.logica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detalle_reserva")
public class DetalleReserva {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_reserva") //Nombre de la columna a relacionar
    private Reserva reserva; //Relacion con el objeto
    
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_habitacion") //Nombre de la columna a relacionar   
    private Habitacion habitacion; //Relacion con el objeto
    
    @Column(nullable = false)
    private double precio;
    
    @Column(nullable = false)
    private boolean flag; 

    public DetalleReserva() {
    }

    public DetalleReserva(Reserva reserva, Habitacion habitacion, double precio, boolean flag) {
        this.reserva = reserva;
        this.habitacion = habitacion;
        this.precio = precio;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }
 
    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
