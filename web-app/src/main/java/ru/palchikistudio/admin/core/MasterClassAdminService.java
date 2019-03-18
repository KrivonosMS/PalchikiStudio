package ru.palchikistudio.admin.core;

import ru.palchikistudio.admin.response.MasterClassResponse;
import ru.palchikistudio.model.MasterClass;

/**
 * Created by Admin on 04.11.2018.
 */
public interface MasterClassAdminService {
    public MasterClassResponse getAllMasterClasses(int from, int limit);
    public int getTotalMasterClassCount();
    public void setIsDeleted(int id);
    public void saveMasterClass(MasterClass masterClass);
    public String getImgPath(int id);
}
