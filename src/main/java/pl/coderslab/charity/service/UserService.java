package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByUserName(String name);
//    User findByEmail(String email);
    Optional<User> findById(int id);

    void saveUser(User user);
    void saveAdmin(User user);
    void updateUser(User user);
    List<User> findAllByRolesContains(Role role);
    List<User> findAllByRolesContainsNot(Role role);
    void blockUser(User user);
    void unblockUser(User user);
    int countUsers();
    int countAdmins();

}
