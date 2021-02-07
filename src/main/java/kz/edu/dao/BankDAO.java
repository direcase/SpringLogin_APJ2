package kz.edu.dao;

import kz.edu.model.Card;
import kz.edu.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class BankDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public BankDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public Card findByNumber(int number)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Card card;
        try
        {
            CriteriaBuilder builder1 = session.getCriteriaBuilder();
            CriteriaQuery<Card> q1 = builder1.createQuery(Card.class);
            Root<Card> root1 = q1.from(Card.class);

            Predicate predicateUsername = builder1.equal(root1.get("card_number"), number);
            card = session.createQuery(q1.where(predicateUsername)).getSingleResult();
            }
        catch (NoResultException noResultException)
        {
            card = null;
        }
        finally
        {
            session.close();
        }
        return card;
    }
}
