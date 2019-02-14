package ru.palchikistudio.admin.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.palchikistudio.model.mapper.MasterClassMapper;
import ru.palchikistudio.db.ConnectionManager;
import ru.palchikistudio.model.MasterClass;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Admin on 01.11.2018.
 */
@Repository
public class MasterClassAdminDaoImpl implements MasterClassAdminDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MasterClassAdminDaoImpl() {
    }

    @Override
    public List<MasterClass> getAllMasterClasses(int from, int limit) throws MasterClassAdminDaoException {
        String query = String.format(
                " select * from" +
                "   palchiki_studio.tbl_master_events events" +
                " where" +
                "   is_deleted = '0'" +
                " order by event_date asc" +
                " limit %d, %d",
                from, limit);

        return jdbcTemplate.query(
                query,
                new MasterClassMapper()
        );
    }

    @Override
    public int getTotalMasterClassCount() throws MasterClassAdminDaoException {
        String query =
            " select" +
            "   count(id)" +
            " from" +
            "   palchiki_studio.tbl_master_events" +
            " where" +
            "   is_deleted = '0'";

        return jdbcTemplate.queryForObject(
                query,
                new Object[] {},
                Integer.class
        );
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
       jdbcTemplate.update(
               query,
               id
       );
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
        jdbcTemplate.update(
                    query,
                    masterClass.getMasterClassName(),
                    masterClass.getTeacherName(),
                    masterClass.getDescription(),
                    masterClass.getCoast(),
                    new java.sql.Date(masterClass.getMasterClassDate().getTime()),
                    masterClass.getImgPath()
        );
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
        if (isImgPathPresented(masterClass)) {
            jdbcTemplate.update(
                    query,
                    masterClass.getMasterClassName(),
                    masterClass.getTeacherName(),
                    masterClass.getDescription(),
                    masterClass.getCoast(),
                    new java.sql.Date(masterClass.getMasterClassDate().getTime()),
                    masterClass.getImgPath(),
                    masterClass.getMasterClassId()
            );
        } else {
            jdbcTemplate.update(
                    query,
                    masterClass.getMasterClassName(),
                    masterClass.getTeacherName(),
                    masterClass.getDescription(),
                    masterClass.getCoast(),
                    new java.sql.Date(masterClass.getMasterClassDate().getTime()),
                    masterClass.getMasterClassId()
            );
        }
    }

    private boolean isImgPathPresented(MasterClass masterClass) {
        return masterClass.getImgPath() != null && !"".equals(masterClass.getImgPath());
    }
}
