package ru.palchikistudio.admin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.palchikistudio.admin.data.MasterClassAdminDaoException;
import ru.palchikistudio.admin.data.MasterClassAdminDaoImpl;
import ru.palchikistudio.admin.response.MasterClassResponse;
import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 04.11.2018.
 */
public class MasterClassAdminServiceImpl implements MasterClassAdminService {
    private static String value;
    private final MasterClassAdminDaoImpl masterClassDao;

    public MasterClassAdminServiceImpl(MasterClassAdminDaoImpl masterClassDao) {
        this.masterClassDao = masterClassDao;
    }

    @Override
    public String getAllMasterClasses(int from, int limit) throws MasterClassAdminServiceException {
        try {
            int totalCount = getTotalMasterClassCount();
            List<MasterClass> masterClasses = masterClassDao.getAllMasterClasses(from, limit);
            MasterClassResponse answer = new MasterClassResponse(masterClasses, totalCount);
            return transformToJson(answer);
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошибка при получении списка мастер-классов", e);
        }
    }

    private String transformToJson(MasterClassResponse  masterClassesResponse) {
        Gson gson = new GsonBuilder().setDateFormat(MasterClass.DATE_FORMAT).create();
        return gson.toJson(masterClassesResponse);
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
    public String setIsDeleted(int id) throws MasterClassAdminServiceException {
        try {
            masterClassDao.setIsDeletedStatus(id);
            return "{\"success\": \"true\"}";
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошика обращении к удалениому мастер-класса", e);
        }
    }

    @Override
    public String saveMasterClass(MasterClass masterClass) throws MasterClassAdminServiceException {
        try {
            if (masterClass.getMasterClassId() == null) {
                masterClassDao.createMasterClass(masterClass);
            } else {
                masterClassDao.updateMasterClass(masterClass);
            }
            return "{\"success\": \"true\"}";
        } catch (MasterClassAdminDaoException e) {
            throw new MasterClassAdminServiceException("Ошика обращении к удалениому мастер-класса", e);
        }
    }
}