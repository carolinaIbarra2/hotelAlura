package com.aluraoracle.hotelalura.logica;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="habitaciones")
public class Habitacion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_tipo_habitacion") //Nombre de la columna a relacionar   
    private TipoHabitacion tipoHabitacion; //Relacion con el objeto usuario con el id de Usuario. 
    @Column(unique = true, nullable = false) //unique= valor unico, nullable=  no null  
    private String numeroHabitacion;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false) //unique= valor unico, nullable=  no null  
    private EstadoHabitacion estadoHabitacion;
    @Column(nullable = false)
    private boolean flag; 
    
    @OneToMany(mappedBy = "habitacion")
    private List<DetalleReserva> detalles =new ArrayList<>();
    

    public Habitacion() {
    }

    public Habitacion(TipoHabitacion tipoHabitacion, String numeroHabitacion, EstadoHabitacion estadoHabitacion, boolean flag) {
        this.tipoHabitacion = tipoHabitacion;
        this.numeroHabitacion = numeroHabitacion;
        this.estadoHabitacion = estadoHabitacion;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public EstadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return numeroHabitacion + " - " + tipoHabitacion.getNombre();
    }
    
    
    
}
