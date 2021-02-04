package com.sskorupski.learn.springboot.users;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User create(User user) {
        Long userId = userDAO.create(user);
        user.setId(userId);
        return user;
    }

    public User getById(Long id) {
        return userDAO.getById(id);
    }

    public boolean update(User user) {
        return userDAO.update(user);
    }

    public boolean delete(User user) {
        return userDAO.delete(user);
    }

}