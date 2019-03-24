package ru.palchikistudio.admin.data;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 01.11.2018.
 */
@Repository
public class MasterClassAdminDaoImpl implements MasterClassAdminDao {
    private static final Logger LOGGER = Logger.getLogger(MasterClassAdminDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    public MasterClassAdminDaoImpl() {

    }

    @Override
    public List<MasterClass> getAllMasterClasses(int from, int limit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MasterClass.class);
        List<MasterClass> masterClasses = criteria
                        .add(Restrictions.eq("isDeleted", false))
                        .addOrder(Order.asc("masterClassDate"))
                        .setFirstResult(from)
                        .setMaxResults(limit)
                        .list();
        session.getTransaction().commit();
        session.close();
        return  masterClasses;
    }

    @Override
    public int getTotalMasterClassCount() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MasterClass.class);
        int count  = criteria
                        .add(Restrictions.eq("isDeleted", false))
                        .list()
                        .size();
        session.getTransaction().commit();
        session.close();
        return count;
    }

    @Override
    public void setIsDeletedStatus(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MasterClass masterClass = session.get(MasterClass.class, id);
        masterClass.setIsDeleted(true);
        session.update(masterClass);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveMasterClass(MasterClass masterClass) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(masterClass);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String getImgPath(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        MasterClass masterClass = session.get(MasterClass.class, id);
        session.getTransaction().commit();
        session.close();
        return masterClass.getImgPath();
    }
}
