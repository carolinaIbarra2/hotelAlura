package com.aluraoracle.hotelalura.logica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")  //como se creo en la base de datos
public class Usuario {
    
    @Id   
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private Long id;
    @Column(unique = true, nullable = false) //unique= valor unico, nullable=  no null     
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean flag;
    
    public Usuario() {
    }

    public Usuario(String username, String password, boolean flag) {
        this.username = username;
        this.password = password;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    } 
}
