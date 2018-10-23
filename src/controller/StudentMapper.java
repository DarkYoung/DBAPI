package controller;

import org.springframework.jdbc.core.RowMapper;
import pojo.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setRegistno(resultSet.getInt("registno"));
        student.setKdno(resultSet.getInt("kdno"));
        student.setKcno(resultSet.getInt("kcno"));
        student.setCcno(resultSet.getInt("ccno"));
        student.setSeat(resultSet.getInt("ccno"));
        student.setName(resultSet.getString("name"));
        return student;
    }
}
