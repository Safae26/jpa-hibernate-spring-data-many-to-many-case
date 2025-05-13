package net.safae.jpa_many_to_many_case.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String desc;
    @ManyToMany(fetch = FetchType.LAZY)
    //@JoinTable(name = "USERS_ROLES")
    private List<User> users;
}
