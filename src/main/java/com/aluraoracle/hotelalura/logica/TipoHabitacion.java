package com.aluraoracle.hotelalura.logica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_habitaciones") 
public class TipoHabitacion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private double precio;
    @Column(nullable = false)
    private boolean flag;    

    public TipoHabitacion() {
    }

    public TipoHabitacion(String nombre, double precio, boolean flag) {
        this.nombre = nombre;
        this.precio = precio;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
