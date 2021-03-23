package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String name);

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);

    void registerUser(User user);

    void saveUser(User user);

    void saveAdmin(User user);

    void updateUser(User user);

    List<User> findAllByRolesContains(Role role);

    List<User> findAllByRolesContainsNot(Role role);

    void blockUser(User user);

    void unblockUser(User user);

    int countUsers();

    int countAdmins();

    String generateEmailChangeKey(User user);

    boolean tryActivateAccount(String key);

    User isEmailKeyValid(String key);

    void changePassword(String password, User user);

    boolean testPassword(String password, User user);

    void deleteUser(int id);

}
