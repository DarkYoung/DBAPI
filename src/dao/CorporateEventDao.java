package dao;

import javax.sql.DataSource;

public interface CorporateEventDao {
    void setDataSource(DataSource dataSource);

    boolean insert(String insertSQL, Object... objs);
}
