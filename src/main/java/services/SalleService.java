package services;

import dao.IDao;
import entities.Salle;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Service de gestion des entités Salle (CRUD)
 * Implémente les opérations de création, lecture, mise à jour et suppression
 * avec gestion propre des sessions Hibernate.
 */
public class SalleService implements IDao<Salle> {

    /**
     * Crée une nouvelle salle dans la base de données
     * @param o objet Salle à insérer
     * @return true si insertion réussie, false sinon
     */
    @Override
    public boolean create(Salle o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            // 🔍 Log pour diagnostic
            System.out.println("→ Création salle : " + o.getCode());

            session.persist(o); // méthode moderne (remplace save())
            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    /**
     * Supprime une salle existante
     * @param o salle à supprimer
     * @return true si suppression réussie
     */
    @Override
    public boolean delete(Salle o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.remove(o); // moderne (remplace delete())

            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    /**
     * Met à jour une salle existante
     * @param o salle à mettre à jour
     * @return true si mise à jour réussie
     */
    @Override
    public boolean update(Salle o) {
        boolean etat = false;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.merge(o); // moderne (remplace update())

            tx.commit();
            etat = true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return etat;
    }

    /**
     * Recherche une salle par son ID
     * @param id identifiant de la salle
     * @return Salle trouvée ou null si inexistante
     */
    @Override
    public Salle findById(int id) {
        Salle salle = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            salle = session.find(Salle.class, id); // méthode moderne recommandée
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return salle;
    }

    /**
     * Récupère toutes les salles existantes
     * @return liste des salles
     */
    @Override
    public List<Salle> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Salle", Salle.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return List.of(); // retourne liste vide plutôt que null
        }
    }
}
