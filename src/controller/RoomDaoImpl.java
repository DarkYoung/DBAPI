package controller;

import dao.CorporateEventDao;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pojo.Room;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component("roomDaoImpl")
public class RoomDaoImpl implements CorporateEventDao {
    private static JdbcTemplate template;
    @Value("${roomInsertSQL}")
    private String insertSQL;
    private StringBuilder builder;

    @Autowired
    @Override
    public void setDataSource(DataSource mysqlDataSource) {
        template = new JdbcTemplate(mysqlDataSource);
        builder = new StringBuilder();
    }


    public boolean insert(Room room) {
        return insert(room.getKdno(), room.getKcno(), room.getCcno(), room.getKdname(), room.getExptime(), room.getPapername());
    }

    public boolean insert(int kdno, int kcno, int ccno, String kdname, Timestamp exptime, String papername) {
        builder.append(insertSQL.substring(0, insertSQL.lastIndexOf('(')))
                .append("(").append(kdno).append(", ").append(kcno).append(", ").append(ccno)
                .append(", ").append(kdname).append(", ").append(exptime)
                .append(", ").append(papername).append(")");
        boolean res = insert(insertSQL, kdno, kcno, ccno, kdname, exptime, papername);
        if (res)
            Logger.add_data_sql(builder.toString());
        else
            Logger.add_dup_data_sql(builder.toString());
        builder.delete(0, builder.length());
        return res;
    }


    @Override
    public boolean insert(String insertSQL, Object... objs) {
        try {
            template.update(insertSQL, objs);
            return true;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    public void batchUpdate(List<Room> rooms) {
        template.batchUpdate(insertSQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Room room = rooms.get(i);
                preparedStatement.setInt(1, room.getKdno());
                preparedStatement.setInt(2, room.getKcno());
                preparedStatement.setInt(3, room.getCcno());
                preparedStatement.setString(4, room.getKdname());
                preparedStatement.setTimestamp(5, room.getExptime());
                preparedStatement.setString(6, room.getPapername());
            }

            @Override
            public int getBatchSize() {
                return rooms.size();
            }

        });
    }

}
