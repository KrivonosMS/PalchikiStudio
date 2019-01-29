package ru.palchikistudio.user.core;

import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 04.11.2018.
 */
public interface MasterClassUserService {
    public List<MasterClass> getAllActualMasterClasses() throws MasterClassUserServiceException;
}
