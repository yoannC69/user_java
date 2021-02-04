package com.sskorupski.learn.springboot.users;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class MysqlUserDAO implements UserDAO {

    private static class Queries {
        static final String GET_ALL = "SELECT id, username, email, password FROM users";
        static final String GET_BY_EMAIL = "SELECT id, username, email, password FROM users WHERE email = ?";
        static final String GET_BY_ID = "SELECT * FROM users WHERE id = ?";
        static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";
        static final String UPDATE_BY_ID = "UPDATE users SET username = ?, email = ?, password  = ? WHERE id = ?";
        static final String INSERT_ONE = "INSERT INTO users(id, username, email, password) values(?,?,?,?)";
    }

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(Queries.GET_ALL, new UserRowMapper());
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(Queries.GET_BY_ID, new Object[]{id}, new UserRowMapper());
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(Queries.GET_BY_EMAIL, new Object[]{email}, new UserRowMapper());
    }

    @Override
    public boolean delete(User user) {
        return jdbcTemplate.update(Queries.DELETE_BY_ID, user.getId()) > 0;
    }

    @Override
    public boolean update(User user) {
        return jdbcTemplate.update(Queries.UPDATE_BY_ID,
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getId()) > 0;
    }

    @Override
    public Long create(User user) {
        jdbcTemplate.update(Queries.INSERT_ONE,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword());
        return Optional.ofNullable(getByEmail(user.getEmail()))
                .map(User::getId)
                .orElse(null);
    }

}