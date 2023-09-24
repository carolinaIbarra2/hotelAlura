package com.aluraoracle.hotelalura.logica;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="personas")  //como se creo en la base de datos
public class Persona {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    @Column(unique = true, nullable = false) //unique= valor unico, nullable=  no null  
    private String dni;
    @Column(unique = true, nullable = false)
    private String pasaporte;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    private String nacionalidad;
    @Column(nullable = false)
    private String telefono;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @ManyToOne  //Tipo de relacion: Muchos a uno
    @JoinColumn(name="id_usuario") //Nombre de la columna a relacionar    
    private Usuario usuario; //Relacion con el objeto usuario con el id de Usuario. 
    @Column(nullable = false)
    private boolean flag;

    public Persona() {
    }

    public Persona(String dni, String pasaporte, String nombre, String apellido, String nacionalidad, String telefono, Date fechaNacimiento, Usuario usuario, boolean flag) {
        this.dni = dni;
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }    
}