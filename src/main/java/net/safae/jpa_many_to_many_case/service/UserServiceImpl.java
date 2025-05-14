package net.safae.jpa_many_to_many_case.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import net.safae.jpa_many_to_many_case.entities.Role;
import net.safae.jpa_many_to_many_case.entities.User;
import net.safae.jpa_many_to_many_case.repositories.UserRepository;
import net.safae.jpa_many_to_many_case.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    //@Autowired
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    // Injection par constructeur
    // public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    //        this.userRepository = userRepository;
    //        this.roleRepository = roleRepository;
    //    }

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        // Adding a hashed password later
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = findUserByUsername(userName);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles()!=null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = userRepository.findByUsername((userName));
        if (user==null) throw new RuntimeException("Bad credentials");
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Bad credentials");
    }
}
