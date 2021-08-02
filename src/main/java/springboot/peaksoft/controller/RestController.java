package springboot.peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.peaksoft.model.User;
import springboot.peaksoft.model.UserRole;
import springboot.peaksoft.service.RoleUserService;
import springboot.peaksoft.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;
    private final RoleUserService roleUserService;

    public RestController(UserService userService, RoleUserService roleUserService) {
        this.userService = userService;
        this.roleUserService = roleUserService;
    }

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @GetMapping("/userId")
    public ResponseEntity<User> getByName(Principal principal) {
        try {
            return new ResponseEntity<>(userService.getByName(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<User> add(@RequestBody User user) {
        try {
            Set<UserRole> roleDB = new HashSet<>();
            roleDB.add(roleUserService.getRoleByName("ROLE_USER"));
            user.setRoles(roleDB);
            return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        try {
            Set<UserRole> roleDB = new HashSet<>();
            roleDB.add(roleUserService.getRoleByName("ROLE_USER"));
            user.setRoles(roleDB);
            return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @CrossOrigin
    @DeleteMapping("/{userId}")
    public ResponseEntity delete(@PathVariable("userId") Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}