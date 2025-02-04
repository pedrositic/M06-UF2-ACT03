package com.iticbcn.paupedros.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Trajecte;

public class TrajecteDAO {
  private final SessionFactory sessionFactory;

  public TrajecteDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public void crearTrajecte(Trajecte inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.persist(inst);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }

  public Trajecte obtenirTrajecte(int TrajecteId) {
    Trajecte Trajecte = null;
    try (Session session = sessionFactory.openSession()) {
      Trajecte = session.find(Trajecte.class, TrajecteId);
    } catch (HibernateException e) {
      System.err.println("Error en Hibernate: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error inesperado: " + e.getMessage());
    }
    return Trajecte;
  }

  public void actualitzarTrajecte(Trajecte inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.merge(inst);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }

  public void eliminarTrajecte(Trajecte inst) {
    try (Session sess = sessionFactory.openSession()) {
      sess.beginTransaction();
      try {
        sess.remove(inst);
        sess.getTransaction().commit();
      } catch (HibernateException e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error en Hibernate: " + e.getMessage());
        }
      } catch (Exception e) {
        if (sess.getTransaction() != null) {
          sess.getTransaction().rollback();
          System.err.println("Error inesperado: " + e.getMessage());
        }
      }
    }
  }
}