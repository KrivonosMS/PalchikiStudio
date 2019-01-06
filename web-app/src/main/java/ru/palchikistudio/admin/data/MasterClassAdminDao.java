package ru.palchikistudio.admin.data;

import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 03.11.2018.
 */
public interface MasterClassAdminDao {
    public List<MasterClass> getAllMasterClasses(int from, int to) throws MasterClassAdminDaoException;
    public int getTotalMasterClassCount() throws MasterClassAdminDaoException;
    public void setIsDeletedStatus(int id) throws MasterClassAdminDaoException;
    public void createMasterClass(MasterClass masterClass) throws MasterClassAdminDaoException;
    public void updateMasterClass(MasterClass masterClass) throws MasterClassAdminDaoException;

}
