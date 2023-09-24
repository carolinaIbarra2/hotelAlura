package com.aluraoracle.hotelalura.Main;

import com.aluraoracle.hotelalura.views.FrmMenuInicio;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HotelAlura {

    public static void main(String[] args) {
     
        EntityManagerFactory entityManagerFactory;
        entityManagerFactory = Persistence.createEntityManagerFactory("persistence_hotel_alura");
        entityManagerFactory.close();
        FrmMenuInicio menuPrincipal = new FrmMenuInicio();
        menuPrincipal.setVisible(true);
        menuPrincipal.setLocationRelativeTo(null);
    }
}
