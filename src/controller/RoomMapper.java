package controller;

import org.springframework.jdbc.core.RowMapper;
import pojo.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setKdno(resultSet.getInt("kdno"));
        room.setKcno(resultSet.getInt("kcno"));
        room.setCcno(resultSet.getInt("ccno"));
        room.setExptime(Timestamp.valueOf(resultSet.getString("exptime") + ":00"));
        room.setKdname(resultSet.getString("kdname"));
        room.setPapername(resultSet.getString("papername"));
        return room;
    }
}
