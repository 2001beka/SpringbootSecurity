package springboot.peaksoft.service;

import springboot.peaksoft.model.UserRole;

import java.util.List;

public interface RoleUserService {
    List<String> getRoleNamesToList();

    UserRole getRoleByName(String name);

    List<UserRole> getAllRoles();
}
