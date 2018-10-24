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
    private StringBuilder builder;

    @Autowired
    @Override
    public void setDataSource(DataSource mysqlDataSource) {
        template = new JdbcTemplate(mysqlDataSource);
        builder = new StringBuilder();
    }


    public boolean insert(Student student) {
        return insert(student.getName(), student.getRegistno(), student.getKdno(), student.getKcno(), student.getCcno(), student.getSeat());
    }

    public boolean insert(String name, Integer registno, Integer kdno, Integer kcno, Integer ccno, Integer seat) {
        builder.append(insertSQL.substring(0, insertSQL.lastIndexOf('(')))
                .append("(").append(registno).append(", ").append(name).append(", ")
                .append(kdno).append(", ").append(kcno).append(", ")
                .append(ccno).append(", ").append(seat).append(")");
        boolean res = insert(insertSQL, registno, name, kdno, kcno, ccno, seat);
        if (res) {
            Logger.add_data_sql(builder.toString());
        } else
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
}
