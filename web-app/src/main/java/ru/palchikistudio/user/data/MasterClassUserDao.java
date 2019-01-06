package ru.palchikistudio.user.data;

import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 03.11.2018.
 */
public interface MasterClassUserDao {
    public List<MasterClass> getAllActualMasterClasses() throws MasterClassDaoException;
    public List<MasterClass> getAllMasterClasses(int from, int to) throws MasterClassDaoException;

}
