package ru.palchikistudio.admin.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.palchikistudio.admin.data.MasterClassAdminDao;
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
    public MasterClassResponse getAllMasterClasses(int from, int limit) {
        int totalCount = getTotalMasterClassCount();
        List<MasterClass> masterClasses = masterClassDao.getAllMasterClasses(from, limit);
        MasterClassResponse answer = new MasterClassResponse(masterClasses, totalCount);
        return answer;
    }

    @Override
    public int getTotalMasterClassCount() {
        return masterClassDao.getTotalMasterClassCount();
    }

    @Override
    public void setIsDeleted(int id) {
        masterClassDao.setIsDeletedStatus(id);
    }

    @Override
    public void saveMasterClass(MasterClass masterClass) {
        masterClassDao.saveMasterClass(masterClass);
    }

    @Override
    public String getImgPath(int id) {
       return masterClassDao.getImgPath(id);
    }
}