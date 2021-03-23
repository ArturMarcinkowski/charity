package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    //    User findByEmail(String email);
    List<User> findAllByRolesContains(Role role);

    List<User> findAllByRolesNotContains(Role role);

    int countAllByRolesContaining(Role role);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailChangeKey(String emailChangeKey);


}
