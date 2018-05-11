/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOA;

import enity.HibernateUtil;
import enity.Men;
import enity.Women;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Touch-Me
 */
public class WomenDAO {
    
    private static Session session;

    public static boolean addWomen(Women w) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(w);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Women> listWomen() {
        List<Women> l = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Women");
        l = req.list();
        session.close();
        return l;
    }

    public static Women getWomen(String name) {
        Women w = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Women where name = '" + name + "'");
        w = (Women) req.uniqueResult();
        session.close();
        return w;
    }

    public static boolean saveChange(String name, int qte, float price) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("update Women set qte = '" + qte + "', price = '" + price + "' where name = '" + name + "'");
        req.executeUpdate();
        session.close();
        return true;
    }

    public static boolean removeWomen(Women w) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(w);
        tx.commit();
        session.close();
        return true;
    }
}
