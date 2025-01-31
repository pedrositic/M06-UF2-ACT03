package com.iticbcn.paupedros;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.iticbcn.paupedros.model.Companyia;
import com.iticbcn.paupedros.model.Estacio;
import com.iticbcn.paupedros.model.Trajecte;
import com.iticbcn.paupedros.model.Tren;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Crear una nueva compañía
            Companyia companyia = new Companyia();
            companyia.setNom("Ferrocarrils de Catalunya");

            // Crear un nuevo tren y asociarlo a la compañía
            Tren tren = new Tren();
            tren.setModel("Class 430");
            tren.setCompanyia(companyia);
            session.persist(tren);

            companyia.getTrens().add(tren);
            session.persist(companyia);

            // Crear un nuevo trayecto y asociarlo al tren
            Trajecte trajecte = new Trajecte();
            trajecte.setOrigen("Barcelona");
            trajecte.setDesti("Madrid");

            trajecte.setTren(tren);
            tren.getTrajectes().add(trajecte);

            session.persist(trajecte);

            // Crear una nueva estación y asociarla al trayecto
            Estacio estacio = new Estacio();
            estacio.setNom("Valencia");

            trajecte.getEstacions().add(estacio);
            estacio.getTrajectes().add(trajecte);

            session.persist(estacio);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("Operaciones completadas con éxito");
    }
}