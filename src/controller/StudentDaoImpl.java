package controller;

import dao.CorporateEventDao;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pojo.Student;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component("studentDaoImpl")
public class StudentDaoImpl implements CorporateEventDao {
    private static JdbcTemplate template;
    @Value("${studentInsertSQL}")
    private String insertSQL;

    @Autowired
    @Override
    public void setDataSource(DataSource mysqlDataSource) {
        template = new JdbcTemplate(mysqlDataSource);
    }


    public boolean insert(Student student) {
        return insert(student.getName(), student.getRegistno(), student.getKdno(), student.getKcno(), student.getCcno(), student.getSeat());
    }

    public boolean insert(String name, Integer registno, Integer kdno, Integer kcno, Integer ccno, Integer seat) {
        String sql = insertSQL.substring(0, insertSQL.lastIndexOf('('))
                + "(" + registno + ", " + name + ", " + kdno + ", " + kcno + ", " + ccno + ", " + seat + ")";
        boolean res = insert(insertSQL, registno, name, kdno, kcno, ccno, seat);
        if (res) {
            Logger.add_data_sql(sql);
        } else
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
