package web.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao  {
    User getUserByName(String name);

    public User getUserById(Long id);

    void addUser(User user);

    public void updateUser(User user);

    public void removeUser(Long id);

    public List<User> listUsers();

}

