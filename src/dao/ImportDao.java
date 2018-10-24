package dao;

import controller.RoomDaoImpl;
import controller.RoomMapper;
import controller.StudentDaoImpl;
import controller.StudentMapper;
import logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Room;
import pojo.Student;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

public abstract class ImportDao {
    private static JdbcTemplate template;
    @Value("${queryStudentSQL}")
    private String queryStudentSQL;
    @Value("${queryRoomSQL}")
    private String queryRoomSQL;
    @Autowired
    private RoomDaoImpl roomDao;
    @Autowired
    private StudentDaoImpl studentDao;

    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public int readData() {
        //读入room表数据
        //将room数据插入mysql数据库
        List<Room> rooms = template.query(queryRoomSQL, new RoomMapper());
        for (Room room : rooms) {
            roomDao.insert(room);
        }

        //读入student表数据
        List<Student> students = template.query(queryStudentSQL, new StudentMapper());
        //将student数据插入mysql数据库
        for (Student student : students) {
            studentDao.insert(student);
        }
        return Logger.getInsertCount();
    }
}
