package ru.palchikistudio.user.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.palchikistudio.model.MasterClass;

import java.sql.Date;
import java.util.List;

/**
 * Created by Admin on 01.11.2018.
 */
@Repository
public class MasterClassUserDaoImpl implements MasterClassUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public MasterClassUserDaoImpl() {
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MasterClass> getAllActualMasterClasses() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(MasterClass.class);
        List<MasterClass> masterClasses =
                        criteria
                        .add(Restrictions.eq("isDeleted", false))
                        .add(Restrictions.ge("masterClassDate", new Date(System.currentTimeMillis())))
                        .list();
        session.getTransaction().commit();
        session.close();
        return  masterClasses;
    }
}
