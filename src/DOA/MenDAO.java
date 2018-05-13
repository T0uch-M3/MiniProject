/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOA;

import enity.HibernateUtil;
import enity.Men;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Touch-Me
 */
public class MenDAO {

    private static Session session;

    public static boolean addMen(Men m) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(m);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Men> listMen() {
        List<Men> l = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Men");
        l = req.list();
        session.close();
        return l;
    }
    public static List<Men> listMenPeriod(String period) {
        List<Men> l = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Men where period = '" + period + "'");
        l = req.list();
        session.close();
        return l;
    }
     public static List<Men> listMenColor(String color) {
        List<Men> l = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Men where color = '" + color + "'");
        l = req.list();
        session.close();
        return l;
    }
     public static List<Men> listMenStat() {//lol this sh** is working, who said can't find any ideas hahahaha
        List<Men> l = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Men ORDER BY qte ASC");
        l = req.list();
        session.close();
        return l;
    }
    
    
    public static Men getMen(String name) {
        Men m = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Men where name = '" + name + "'");
        m = (Men) req.uniqueResult();
        session.close();
        return m;
    }
    

    public static boolean saveChange(String name, int qte, float price) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("update Men set qte = '" + qte + "', price = '" + price + "' where name = '" + name + "'");
        req.executeUpdate();
        session.close();
        return true;
    }

    public static boolean removeMen(Men m) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(m);
        tx.commit();
        session.close();
        return true;
    }
    
}
