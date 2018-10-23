package controller;

import dao.ImportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("csvDaoImpl")
public class CSVDaoImpl extends ImportDao {
    @Autowired
    @Override
    public void setDataSource(DataSource csvDataSource) {
        super.setDataSource(csvDataSource);
    }

}
