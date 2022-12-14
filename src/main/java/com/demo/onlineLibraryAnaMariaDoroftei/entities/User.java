package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import com.demo.onlineLibraryAnaMariaDoroftei.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstname;
    private String surname;
    private String phoneNumber;
    private String password = "";
    private Date joinDate;
    @ElementCollection
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(STRING)
    private Collection<Roles> roles;

    @OneToMany(mappedBy="user",
            cascade= {CascadeType.ALL},
            fetch=FetchType.EAGER)
    private Set<ReadBook> readBooks;

    public User(String email, String password, Roles roles) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
