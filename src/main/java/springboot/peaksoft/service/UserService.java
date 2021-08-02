package springboot.peaksoft.service;

import springboot.peaksoft.model.User;

import java.util.List;

public interface UserService {


    User saveUser(User user);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);

    User getById(Long id);

    User getByName(String name);
}
