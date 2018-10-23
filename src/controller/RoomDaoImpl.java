package controller;

import dao.CorporateEventDao;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pojo.Room;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Component("roomDaoImpl")
public class RoomDaoImpl implements CorporateEventDao {
    private static JdbcTemplate template;
    @Value("${roomInsertSQL}")
    private String insertSQL;

    @Autowired
    @Override
    public void setDataSource(DataSource mysqlDataSource) {
        template = new JdbcTemplate(mysqlDataSource);
    }


    public boolean insert(Room room) {
        return insert(room.getKdno(), room.getKcno(), room.getCcno(), room.getKdname(), room.getExptime(), room.getPapername());
    }

    public boolean insert(int kdno, int kcno, int ccno, String kdname, Timestamp exptime, String papername) {
        String sql = insertSQL.substring(0, insertSQL.lastIndexOf('('))
                + "(" + kdno + ", " + kcno + ", " + ccno + ", " + kdname + ", " + exptime + ", " + papername + ")";
        boolean res = insert(insertSQL, kdno, kcno, ccno, kdname, exptime, papername);
        if (res)
            Logger.add_data_sql(sql);
        else
            Logger.add_dup_data_sql(sql);
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
}
