package ru.palchikistudio.user.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.palchikistudio.user.data.MasterClassUserDaoImpl;
import ru.palchikistudio.user.data.MasterClassDaoException;
import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 04.11.2018.
 */
public class MasterClassUserServiceImpl implements MasterClassUserService {
    private final MasterClassUserDaoImpl masterClassDao;

    public MasterClassUserServiceImpl(MasterClassUserDaoImpl masterClassDao) {
        this.masterClassDao = masterClassDao;
    }

    @Override
    public String getAllActualMasterClasses() throws MasterClassUserServiceException {
        try {
            List<MasterClass> masterClasses = masterClassDao.getAllActualMasterClasses();
            return transformToJson(masterClasses);
        } catch (MasterClassDaoException e) {
            throw new MasterClassUserServiceException("Ошибка при получении списка актуальных мастер-классов", e);
        }
    }

    private String transformToJson(List<MasterClass> masterClasses) {
        Gson gson = new GsonBuilder().setDateFormat(MasterClass.DATE_FORMAT).create();
        return gson.toJson(masterClasses);
    }
}
