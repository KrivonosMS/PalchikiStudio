package ru.palchikistudio.admin.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;
import ru.palchikistudio.db.ConnectionManager;
import ru.palchikistudio.model.MasterClass;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 01.11.2018.
 */
@Repository
public class MasterClassAdminDaoImpl implements MasterClassAdminDao {
    private ConnectionManager connectionManager;

    public MasterClassAdminDaoImpl() {
    }

    @Autowired
    public void setConnection(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<MasterClass> getAllMasterClasses(int from, int limit) throws MasterClassAdminDaoException {
        List<MasterClass> masterClasses = new ArrayList<>();
        String query =
                " select" +
                "   id," +
                "   master_name," +
                "   teacher_name," +
                "   description," +
                "   coast," +
                "   event_date," +
                "   img_name" +
                " from" +
                "   palchiki_studio.tbl_master_events events" +
                " where" +
                "   is_deleted = '0'" +
                " order by event_date asc" +
                " limit ?, ?";

        try(Connection connection = connectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, from);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String masterClassName = rs.getString(2);
                    String teacherName = rs.getString(3);
                    String description = rs.getString(4);
                    int coast = rs.getInt(5);
                    Date masterClassDate = rs.getTimestamp(6);
                    String imgName = rs.getString(7);

                    MasterClass masterClass = new MasterClass.Builder(masterClassName, coast, masterClassDate)
                            .addMasterClassId(id)
                            .addTeacherName(teacherName)
                            .addDescription(description)
                            .addImgPath(MasterClass.IMG_DIRECTORY + File.separator + imgName)
                            .build();

                    masterClasses.add(masterClass);
                }
            }
        } catch (Exception e) {
            throw new MasterClassAdminDaoException("Ошбика при получении списка мастер-классов", e);
        }
        return masterClasses;
    }

    @Override
    public int getTotalMasterClassCount() throws MasterClassAdminDaoException {
        int res = 0;
        String query =
            " select" +
            "   count(id)" +
            " from" +
            "   palchiki_studio.tbl_master_events" +
            " where" +
            "   is_deleted = '0'";

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
        if(rs.next()) {
             res = rs.getInt(1);
        }
         return res;
        } catch (Exception e) {
            throw new MasterClassAdminDaoException("Ошбика при получении списка мастер-классов", e);
        }
    }

    @Override
    public void setIsDeletedStatus(int id) throws MasterClassAdminDaoException {
        String query =
        " update" +
        "   palchiki_studio.tbl_master_events" +
        " set" +
         "  is_deleted = '1'" +
        " where" +
        "   id = ?";

        try(Connection connection = connectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new MasterClassAdminDaoException("Ошбика при удалении мастер-класса с id =" + id, e);
        }
    }

    @Override
    public void createMasterClass(MasterClass masterClass) throws MasterClassAdminDaoException {
        String query =
                " insert into" +
                "   palchiki_studio.tbl_master_events" +
                "   (" +
                "       master_name,teacher_name,description,coast,event_date,img_name" +
                "   )" +
                " values(?,?,?,?,?,?)";

        try(Connection connection = connectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, masterClass.getMasterClassName());
            ps.setString(2, masterClass.getTeacherName());
            ps.setString(3, masterClass.getDescription());
            ps.setInt(4, masterClass.getCoast());
            ps.setDate(5,  new java.sql.Date(masterClass.getMasterClassDate().getTime()));
            ps.setString(6, masterClass.getImgPath());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new MasterClassAdminDaoException(
                    "Ошбика при добавлении мастер-класса '" + masterClass.getMasterClassName() + "'",
                    e
            );
        }
    }

    @Override
    public void updateMasterClass(MasterClass masterClass) throws MasterClassAdminDaoException {
        StringBuilder sb = new StringBuilder();
        sb.append(" update" +
                "   palchiki_studio.tbl_master_events" +
                " set" +
                "   master_name = ?," +
                "   teacher_name = ?," +
                "   description = ?," +
                "   coast = ?," +
                "   event_date = ?");
        if (isImgPathPresented(masterClass)) {
            sb.append( ", img_name = ?");
        }
        sb.append(" where id = ?");
        String query = sb.toString();
        try(Connection connection = connectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, masterClass.getMasterClassName());
            ps.setString(2, masterClass.getTeacherName());
            ps.setString(3, masterClass.getDescription());
            ps.setInt(4, masterClass.getCoast());
            ps.setDate(5, new java.sql.Date(masterClass.getMasterClassDate().getTime()));
            if (isImgPathPresented(masterClass)) {
                ps.setString(6, masterClass.getImgPath());
                ps.setInt(7, masterClass.getMasterClassId());
            } else {
                ps.setInt(6, masterClass.getMasterClassId());
            }
            ps.executeUpdate();
        } catch (Exception e) {
            throw new MasterClassAdminDaoException(
                    "Ошбика при обновлении мастер-класса с id =" + masterClass.getMasterClassId(),
                    e
            );
        }
    }

    private boolean isImgPathPresented(MasterClass masterClass) {
        return masterClass.getImgPath() != null && !"".equals(masterClass.getImgPath());
    }
}
