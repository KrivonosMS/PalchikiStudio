package ru.palchikistudio.admin.data;

import ru.palchikistudio.model.MasterClass;

import java.util.List;

/**
 * Created by Admin on 03.11.2018.
 */
public interface MasterClassAdminDao {
    public List<MasterClass> getAllMasterClasses(int from, int to);
    public int getTotalMasterClassCount();
    public void setIsDeletedStatus(int id);
    public void saveMasterClass(MasterClass masterClass);
    public String getImgPath(int id);
}