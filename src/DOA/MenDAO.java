/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOA;

import enity.HibernateUtil;
import enity.Men;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Touch-Me
 */
public class MenDAO {
    private static Session session;
    public static boolean addMen(Men m){
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.save(m);
            tx.commit();
            session.close();
            return true;
        }catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
