package net.safae.jpa_many_to_many_case.service;

import net.safae.jpa_many_to_many_case.entities.Role;
import net.safae.jpa_many_to_many_case.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String roleName);
    User authenticate(String userName, String password);
}
