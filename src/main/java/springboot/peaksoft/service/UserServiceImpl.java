package springboot.peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.peaksoft.model.User;
import springboot.peaksoft.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userDao;

    @Autowired
    public UserServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public User updateUser(User user) {

        return userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public User getByName(String name) {
        List<User> users = getAllUsers();
        return users.stream().filter(x -> x.getName().equals(name)).findAny().orElse(null);
    }
}
