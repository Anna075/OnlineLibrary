package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import com.demo.onlineLibraryAnaMariaDoroftei.enums.Roles;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

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
    private String password;
    private String joinDate;
    @Enumerated(STRING)
    private Roles roles;

    @OneToMany(mappedBy="user",
            cascade= {CascadeType.ALL},
            fetch=FetchType.EAGER)
    private Set<ReadBook> readBooks;

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
