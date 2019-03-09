package ru.palchikistudio.admin.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.palchikistudio.admin.data.MasterClassAdminDao;
import ru.palchikistudio.admin.data.MasterClassAdminDaoException;
import ru.palchikistudio.admin.data.MasterClassAdminDaoImpl;
import ru.palchikistudio.admin.response.MasterClassResponse;
import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 04.11.2018.
 */
@Service
public class MasterClassAdminServiceImpl implements MasterClassAdminService {
    private MasterClassAdminDao masterClassDao;

    public MasterClassAdminServiceImpl() {
    }

    @Autowired
    public void setMasterClassDao(MasterClassAdminDaoImpl masterClassDao) {
        this.masterClassDao = masterClassDao;
    }

    @Override
    public MasterClassResponse getAllMasterClasses(int from, int limit) throws MasterClassAdminServiceException {
        try {
            int totalCount = getTotalMasterClassCount();
            List<MasterClass> masterClasses = masterClassDao.getAllMasterClasses(from, limit);
            MasterClassResponse answer = new MasterClassResponse(masterClasses, totalCount);
            return answer;
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошибка при получении списка мастер-классов", e);
        }
    }

    @Override
    public int getTotalMasterClassCount() throws MasterClassAdminServiceException {
        try {
            return masterClassDao.getTotalMasterClassCount();
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошика при удалении мастер-класса", e);
        }
    }

    @Override
    public void setIsDeleted(int id) throws MasterClassAdminServiceException {
        try {
            masterClassDao.setIsDeletedStatus(id);
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошика обращении к удалениому мастер-класса", e);
        }
    }

    @Override
    public void saveMasterClass(MasterClass masterClass) throws MasterClassAdminServiceException {
        try {
            if (masterClass.getMasterClassId() == null) {
                masterClassDao.createMasterClass(masterClass);
            } else {
                masterClassDao.updateMasterClass(masterClass);
            }
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошика обращении к удалениому мастер-класса", e);
        }
    }
}