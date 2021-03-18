package pl.coderslab.charity.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

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

//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }

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
        user.setPassword(oldUser.getPassword());
//        user.setUsername(oldUser.getUsername());
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAllByRolesContains(Role role){
        return userRepository.findAllByRolesContains(role);
    }
    @Override
    public List<User> findAllByRolesContainsNot(Role role){
        return userRepository.findAllByRolesNotContains(role);
    }


    @Override
    public void blockUser(User user){
        user.setEnabled(0);
        userRepository.save(user);
    }
    @Override
    public void unblockUser(User user){
        user.setEnabled(1);
        userRepository.save(user);
    }

    @Override
    public int countUsers(){
        return (int) userRepository.count();
    }

    @Override
    public int countAdmins(){
        Role role = roleRepository.findByName("ROLE_ADMIN");
        return userRepository.countAllByRolesContaining(role);
    }
}
