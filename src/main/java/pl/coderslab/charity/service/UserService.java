package pl.coderslab.charity.service;

import pl.coderslab.charity.model.User;

public interface UserService {

    User findByUserName(String name);
//    User findByEmail(String email);

    void saveUser(User user);


}
