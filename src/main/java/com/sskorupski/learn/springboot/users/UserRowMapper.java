package com.sskorupski.learn.springboot.users;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sskorupski.learn.springboot.users.UserRowMapper.ColumnNames.*;

public class UserRowMapper implements RowMapper<User> {

    static class ColumnNames {
        static final String ID = "id";
        static final String USERNAME = "username";
        static final String EMAIL = "email";
        static final String PASSWORD = "password";
    }

    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID));
        user.setUsername(resultSet.getString(USERNAME));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
        return user;
    }
}