package ru.palchikistudio.admin.core;

import ru.palchikistudio.admin.data.MasterClassAdminDaoException;
import ru.palchikistudio.model.MasterClass;

/**
 * Created by Admin on 04.11.2018.
 */
public interface MasterClassAdminService {
    public String getAllMasterClasses(int from, int limit) throws MasterClassAdminServiceException;
    public int getTotalMasterClassCount() throws MasterClassAdminServiceException;
    public String setIsDeleted(int id) throws MasterClassAdminServiceException;
    public String saveMasterClass(MasterClass masterClass) throws MasterClassAdminServiceException;

}
