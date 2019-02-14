package ru.palchikistudio.user.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.palchikistudio.db.ConnectionManager;
import ru.palchikistudio.model.MasterClass;
import ru.palchikistudio.model.mapper.MasterClassMapper;

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
    private JdbcTemplate jdbcTemplate;

    public MasterClassUserDaoImpl() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MasterClass> getAllActualMasterClasses() throws MasterClassDaoException {
                String query =
                " select * from" +
                "   palchiki_studio.tbl_master_events events" +
                " where" +
                "   event_date > now()" +
                "   and" +
                "   is_deleted = '0'" +
                " order by event_date asc";

        return jdbcTemplate.query(
                query,
                new MasterClassMapper()
        );
    }
}
