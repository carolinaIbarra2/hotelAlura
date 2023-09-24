/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aluraoracle.hotelalura.prueba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author guima
 */
public class PruebaFrmRegistroUsuario {
    public static void main(String[] args) {
        String fechaTexto = "12 set. 2023";
        SimpleDateFormat formato = new SimpleDateFormat("dd MMM. yyyy", new Locale("es"));

        try {
            Date fecha = formato.parse(fechaTexto);
            System.out.println("La fecha es válida: " + fecha);
        } catch (ParseException e) {
            System.out.println("La fecha no es válida.");
        }
    }
}
