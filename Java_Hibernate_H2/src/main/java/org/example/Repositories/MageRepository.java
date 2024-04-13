package org.example.Repositories;

import org.example.Entities.Mage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MageRepository {
    private final SessionFactory sessionFactory;

    public MageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveMage(Mage mage) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(mage);
        tx.commit();
        session.close();
    }

    public Mage findMage(String name) {
        Session session = sessionFactory.openSession();
        Mage mage = session.get(Mage.class, name);
        session.close();
        return mage;
    }

    public void deleteMage(String name) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Mage mage = session.get(Mage.class, name);
        /*if (mage != null) {
            session.remove(mage);
            tx.commit();
            session.close();
        } else {
            tx.rollback();
            session.close();
        }*/
        session.remove(mage);
        tx.commit();
        session.close();
    }

    public void updateMage(Mage mage) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(mage);
        tx.commit();
        session.close();
    }

    public List<Mage> findAllByLevelGreaterThan(int level) {
        Session session = sessionFactory.openSession();
        Query<Mage> query = session.createQuery("FROM Mage WHERE level > :level", Mage.class);
        query.setParameter("level", level);
        List<Mage> mages = query.getResultList();
        session.close();
        return mages;
    }

    public List<Mage> findAllByLevelGreaterThanAndTowerEquals(int level, String towerName) {
        Session session = sessionFactory.openSession();
        Query<Mage> query = session.createQuery("FROM Mage WHERE level > :level AND tower.name = :towerName", Mage.class);
        query.setParameter("level", level);
        query.setParameter("towerName", towerName);
        List<Mage> mages = query.getResultList();
        session.close();
        return mages;
    }

    public List<Mage> findAll() {
        Session session = sessionFactory.openSession();
        List<Mage> mages = session.createQuery("FROM Mage", Mage.class).getResultList();
        session.close();
        return mages;
    }
}
