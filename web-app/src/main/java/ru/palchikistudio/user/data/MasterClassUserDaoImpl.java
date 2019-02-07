package ru.palchikistudio.user.data;

import org.springframework.beans.factory.annotation.Autowired;
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
public class MasterClassUserDaoImpl implements MasterClassUserDao {
    private ConnectionManager connectionManager;

    public MasterClassUserDaoImpl() {
    }

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<MasterClass> getAllActualMasterClasses() throws MasterClassDaoException {
        List<MasterClass> actualMasterClasses = new ArrayList<>();
        String query =
                " select" +
                "   master_name," +
                "   teacher_name," +
                "   description," +
                "   coast," +
                "   event_date," +
                "   img_name" +
                " from" +
                "   palchiki_studio.tbl_master_events events" +
                " where" +
                "   event_date > now()" +
                "   and" +
                "   is_deleted = '0'" +
                " order by event_date asc";

        try(Connection connection = connectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String masterClassName = rs.getString(1);
                String teacherName = rs.getString(2);
                String description = rs.getString(3);
                int coast = rs.getInt(4);
                Date masterClassDate = rs.getTimestamp(5);
                String imgName = rs.getString(6);
                if (imgName == null || "".equals(imgName)) {
                    imgName = MasterClass.DEFAULT_IMG;
                }

                MasterClass masterClass = new MasterClass.Builder(masterClassName, coast, masterClassDate)
                        .addTeacherName(teacherName)
                        .addDescription(description)
                        .addImgPath(MasterClass.IMG_DIRECTORY + File.separator + imgName)
                        .build();

                actualMasterClasses.add(masterClass);
            }
        } catch (Exception e) {
            throw new MasterClassDaoException("Ошбика при получении списка предстоящих мастер-классов", e);
        }
        return actualMasterClasses;
    }

    @Override
    public List<MasterClass> getAllMasterClasses(int from, int limit) throws MasterClassDaoException {
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
                    if (imgName == null || "".equals(imgName)) {
                        imgName = MasterClass.DEFAULT_IMG;
                    }
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
            throw new MasterClassDaoException("Ошбика при получении списка мастер-классов", e);
        }
        return masterClasses;
    }
}
