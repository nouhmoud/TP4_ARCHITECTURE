package services;

import dao.IDao;
import entities.Machine;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

public class MachineService implements IDao<Machine> {

    @Override
    public boolean create(Machine o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(o); // Remplace save()
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    @Override
    public boolean delete(Machine o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(o); // Remplace delete()
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    @Override
    public boolean update(Machine o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(o); // Remplace update()
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    @Override
    public Machine findById(int id) {
        Machine machine = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            machine = session.find(Machine.class, id); // remplace get()
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return machine;
    }








    @Override
    public List<Machine> findAll() {
        List<Machine> machines = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            machines = session.createQuery("from Machine", Machine.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return machines;
    }

    public List<Machine> findBetweenDate(Date d1, Date d2) {
        List<Machine> machines = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            machines = session.createNamedQuery("findBetweenDate", Machine.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return machines;
    }
}
