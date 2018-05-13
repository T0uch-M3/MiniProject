/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOA;

import enity.HibernateUtil;
import enity.Men;
import enity.Stat;
import enity.User;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Touch-Me
 */
public class StatDAO {
    private static Session session;
    
     public static List<Stat> getListStat() {
        List<Stat> l = null;

        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Stat");
        l = req.list();
        session.close();
        return l;
    }
      public static List<Stat> getListStat2() {
        List<Stat> l = null;

        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Stat where name != 'Women' and name != 'Men'");
        l = req.list();
        session.close();
        return l;
    }
      public static Stat getStat(String name) {
          
        Stat s = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Stat where name = '" + name + "'");
        s = (Stat) req.uniqueResult();
        session.close();
        return s;
    }
      public static Stat getStatByQte(int qte) {//had no other choice, i just can't think of other solution no now anw
          
        Stat s = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from Stat where qte = '" + qte + "'");
        s = (Stat) req.uniqueResult();
        session.close();
        return s;
    }
     public static boolean addStat(Stat s) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(s);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
     public static boolean updateStat(String name, int qte){        
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("Update Stat set qte = '"+qte+"' where name = '"+name+"'");
        req.executeUpdate();
        session.close();
        return true;
    }
}
