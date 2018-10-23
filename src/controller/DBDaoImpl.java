package controller;

import dao.ImportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("dbDaoImpl")
public class DBDaoImpl extends ImportDao {
    @Autowired
    @Override
    public void setDataSource(DataSource sqliteDataSource) {
        super.setDataSource(sqliteDataSource);
    }
}
