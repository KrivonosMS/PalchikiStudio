package ru.palchikistudio.user.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.palchikistudio.user.data.MasterClassUserDaoImpl;
import ru.palchikistudio.user.data.MasterClassDaoException;
import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 04.11.2018.
 */
@Service
public class MasterClassUserServiceImpl implements MasterClassUserService {
    private MasterClassUserDaoImpl masterClassUserDao;

    public MasterClassUserServiceImpl() {
    }

    @Autowired
    public void setMasterClassUserDao(MasterClassUserDaoImpl masterClassUserDao) {
        this.masterClassUserDao = masterClassUserDao;
    }

    @Override
    public List<MasterClass> getAllActualMasterClasses() throws MasterClassUserServiceException {
        try {
            return masterClassUserDao.getAllActualMasterClasses();
        } catch (MasterClassDaoException e) {
            throw new MasterClassUserServiceException("Ошибка при получении списка актуальных мастер-классов", e);
        }
    }

    private String transformToJson(List<MasterClass> masterClasses) {
        Gson gson = new GsonBuilder().setDateFormat(MasterClass.DATE_FORMAT).create();
        return gson.toJson(masterClasses);
    }
}
