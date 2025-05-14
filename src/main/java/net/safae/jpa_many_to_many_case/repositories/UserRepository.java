package net.safae.jpa_many_to_many_case.repositories;

import net.safae.jpa_many_to_many_case.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
