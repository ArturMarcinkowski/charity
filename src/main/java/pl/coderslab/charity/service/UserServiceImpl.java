package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.utils.RandomString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(0);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        User oldUser = userRepository.findById(user.getId()).get();
        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setSurname(user.getSurname());
        oldUser.setEmail(user.getEmail());
        userRepository.save(oldUser);
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllByRolesContains(Role role) {
        return userRepository.findAllByRolesContains(role);
    }

    @Override
    public List<User> findAllByRolesContainsNot(Role role) {
        return userRepository.findAllByRolesNotContains(role);
    }


    @Override
    public void blockUser(User user) {
        user.setEnabled(0);
        userRepository.save(user);
    }

    @Override
    public void unblockUser(User user) {
        user.setEnabled(1);
        userRepository.save(user);
    }

    @Override
    public int countUsers() {
        return (int) userRepository.count();
    }

    @Override
    public int countAdmins() {
        Role role = roleRepository.findByName("ROLE_ADMIN");
        return userRepository.countAllByRolesContaining(role);
    }

    @Override
    public String generateEmailChangeKey(User user) {
        String randomString = RandomString.getRandomString(32);
        user.setEmailChangeKey(randomString);
        user.setEmailChangeDate(LocalDateTime.now());
        userRepository.save(user);
        return randomString;
    }

    @Override
    public boolean tryActivateAccount(String key) {
        Optional<User> optionalUser = userRepository.findByEmailChangeKey(key);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(1);
            user.setEmailChangeKey(null);
            user.setEmailChangeDate(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User isEmailKeyValid(String key) {
        Optional<User> optionalUser = userRepository.findByEmailChangeKey(key);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Duration duration = Duration.between(user.getEmailChangeDate(), LocalDateTime.now());
            if (duration.toDays() > 1) {
                return null;
            }
            return user;
        }
        return null;
    }

    @Override
    public void changePassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        user.setEmailChangeKey(null);
        user.setEmailChangeDate(null);
        userRepository.save(user);
    }

    @Override
    public boolean testPassword(String password, User user) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Role role = roleRepository.findByName("ROLE_ADMIN");
            if (user.isAdmin()) {
                if (userRepository.countAllByRolesContaining(role) == 1) {
                    return;
                }
            }
            userRepository.delete(user);
        }
    }
}
