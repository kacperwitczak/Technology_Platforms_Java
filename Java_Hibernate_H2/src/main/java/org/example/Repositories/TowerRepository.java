package org.example.Repositories;

import org.example.Entities.Tower;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TowerRepository {
    private final SessionFactory sessionFactory;

    public TowerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveTower(Tower tower) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(tower);
        tx.commit();
        session.close();
    }

    public Tower findTower(String name) {
        Session session = sessionFactory.openSession();
        Tower tower = session.get(Tower.class, name);
        session.close();
        return tower;
    }

    public void deleteTower(String name) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Tower tower = session.get(Tower.class, name);
        /*if (tower != null) {
            session.remove(tower);
            tx.commit();
            session.close();
        } else {
            tx.rollback();
            session.close();
        }*/
        session.remove(tower);
        tx.commit();
        session.close();
    }

    public void updateTower(Tower tower) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(tower);
        tx.commit();
        session.close();
    }

    public List<Tower> findAllByHeightLessThan(int height) {
        Session session = sessionFactory.openSession();
        Query<Tower> query = session.createQuery("FROM Tower WHERE height < :height", Tower.class);
        query.setParameter("height", height);
        List<Tower> towers = query.getResultList();
        session.close();
        return towers;
    }

    public List<Tower> findAll() {
        Session session = sessionFactory.openSession();
        List<Tower> towers = session.createQuery("FROM Tower", Tower.class).getResultList();
        session.close();
        return towers;
    }
}
