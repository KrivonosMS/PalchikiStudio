package ru.palchikistudio.model.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.palchikistudio.model.MasterClass;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 13.02.2019.
 */
public class MasterClassMapper implements RowMapper<MasterClass> {
    @Override
    public MasterClass mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new MasterClass.Builder(
                rs.getString("master_name"),
                rs.getInt("coast"),
                rs.getTimestamp("event_date")
                )
                .addMasterClassId(rs.getInt("id"))
                .addTeacherName(rs.getString("teacher_name"))
                .addDescription(rs.getString("description"))
                .addImgPath(rs.getString("img_name"))
                .build();
    }
}