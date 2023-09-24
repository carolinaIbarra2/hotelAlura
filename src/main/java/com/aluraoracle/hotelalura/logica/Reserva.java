package com.aluraoracle.hotelalura.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_persona_usuario") //Nombre de la columna a relacionar   
    private Persona usuario; //Relacion con el objeto 
    
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_persona_huesped") //Nombre de la columna a relacionar   
    private Persona huesped; 
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)    
    private Date fechaCheckIn;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCheckOut; 
    
    @Column(nullable = false)
    private boolean flag; 
    
    
    @OneToMany(mappedBy = "reserva")
    private List<DetalleReserva> detalles =new ArrayList<>();
    
    public Reserva() {
    }

    public Reserva(Persona usuario, Persona huesped, Date fechaCheckIn, Date fechaCheckOut, boolean flag) {
        this.usuario = usuario;
        this.huesped = huesped;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }
    
    public Persona getUsuario() {
        return usuario;
    }

    public void setUsuario(Persona usuario) {
        this.usuario = usuario;
    }

    public Persona getHuesped() {
        return huesped;
    }

    public void setHuesped(Persona huesped) {
        this.huesped = huesped;
    }

    public Date getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(Date fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public Date getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(Date fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
}
