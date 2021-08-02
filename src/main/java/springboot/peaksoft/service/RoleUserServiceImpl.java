package springboot.peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.peaksoft.model.UserRole;
import springboot.peaksoft.repository.RoleRepository;

import java.util.List;

@Service
@Transactional
public class RoleUserServiceImpl implements RoleUserService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleUserServiceImpl(RoleRepository userRepository) {
        this.roleRepository = userRepository;
    }

    @Override
    public List<String> getRoleNamesToList() {
        return roleRepository.getRoleNamesToList();
    }

    @Override
    public UserRole getRoleByName(String name) {
        List<UserRole> roles = getAllRoles();
        return roles.stream().filter(x -> x.getRole().equals(name)).findAny().orElse(null);
    }

    @Override
    public List<UserRole> getAllRoles() {
        return roleRepository.findAll();
    }
}
