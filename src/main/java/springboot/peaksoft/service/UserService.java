package springboot.peaksoft.service;

import springboot.peaksoft.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    void updateUser(User user);


    void deleteUser(User user);

    User getById(Long id);

    User getByName(String name);
}
