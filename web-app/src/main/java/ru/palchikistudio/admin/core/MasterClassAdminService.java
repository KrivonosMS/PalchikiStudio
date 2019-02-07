package ru.palchikistudio.admin.core;

import ru.palchikistudio.admin.data.MasterClassAdminDaoException;
import ru.palchikistudio.admin.response.MasterClassResponse;
import ru.palchikistudio.model.MasterClass;

/**
 * Created by Admin on 04.11.2018.
 */
public interface MasterClassAdminService {
    public MasterClassResponse getAllMasterClasses(int from, int limit) throws MasterClassAdminServiceException;
    public int getTotalMasterClassCount() throws MasterClassAdminServiceException;
    public void setIsDeleted(int id) throws MasterClassAdminServiceException;
    public void saveMasterClass(MasterClass masterClass) throws MasterClassAdminServiceException;

}
