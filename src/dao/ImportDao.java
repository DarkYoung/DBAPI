package dao;

import controller.RoomDaoImpl;
import controller.RoomMapper;
import controller.StudentDaoImpl;
import controller.StudentMapper;
import logger.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import pojo.Room;
import pojo.Student;

import javax.sql.DataSource;
import java.util.List;

public abstract class ImportDao {
    private static JdbcTemplate template;
    @Value("${queryStudentSQL}")
    private String queryStudentSQL;
    @Value("${queryRoomSQL}")
    private String queryRoomSQL;

    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    public int readData(ApplicationContext context) {
        int count = 0;
        //读入room表数据
        List<Room> rooms = template.query(queryRoomSQL, new RoomMapper());
        //将room数据插入mysql数据库
        RoomDaoImpl roomDao = (RoomDaoImpl) context.getBean("roomDaoImpl");
        for (Room room : rooms) {
            if (roomDao.insert(room))
                count++;
        }
        //读入student表数据
        List<Student> students = template.query(queryStudentSQL, new StudentMapper());
        //将student数据插入mysql数据库
        StudentDaoImpl studentDao = (StudentDaoImpl) context.getBean("studentDaoImpl");
        for (Student student : students) {
            if (studentDao.insert(student))
                count++;
        }
        Logger.close();
        return count;
    }
}
