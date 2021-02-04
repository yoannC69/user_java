package com.sskorupski.learn.springboot.users;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MysqlUserDAO implements UserDAO {

    private static class Queries {
        static final String GET_ALL = "SELECT id, username, email, password FROM users";
    }

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

    @Override
    public User addUser(long id) {

        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

    @Override
    public User getUserById(long id) {

        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

    @Override
    public User update(long id) {

        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

    @Override
    public User deleteUser(long id) {

        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

}
