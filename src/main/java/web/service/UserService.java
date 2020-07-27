package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    List<User> listUsers();

    Role getRoleByName(String name);

}
