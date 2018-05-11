/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOA;

import enity.HibernateUtil;
import enity.Men;
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
public class UserDAO {
    private static Session session;
    public static boolean addUser(User u) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(u);
            tx.commit();
            session.close();
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static User getUser(String id, String pwd) {
        User u = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from User where id = '" + id + "' and password = '" + pwd + "'");
        u = (User) req.uniqueResult();
        session.close();
        return u;
    }
    
    public static List<User> getAdmin(char type) {
        List<User> l = null;

        session = HibernateUtil.getSessionFactory().openSession();
        Query req = session.createQuery("from User where type = '"+type+"'");
//        Query req = session.createQuery("from User where id = '" + id + "' and password = '" + pwd + "' and type = '"+type+"'");
        l = req.list();
        session.close();
        return l;
        
    }
}
