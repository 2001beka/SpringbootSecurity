package springboot.peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.peaksoft.model.User;
import springboot.peaksoft.repository.UserRepository;

import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userDao;

    @Autowired
    public UserServiceImpl(UserRepository userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userDao.getById(id);
    }

        @Override
        public User getByName(String name) {
            List<User> users = getAllUsers();
            return users.stream().filter(x -> x.getName().equals(name)).findAny().orElse(null);
        }
}
