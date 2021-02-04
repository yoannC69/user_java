package com.sskorupski.learn.springboot.users;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getById(Long id);

    User getByEmail(String email);

    boolean delete(User user);

    boolean update(User user);

    Long create(User user);

}