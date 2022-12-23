package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import com.demo.onlineLibraryAnaMariaDoroftei.enums.Roles;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User{

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
    private List<ReadBook> readBooks = new ArrayList<>();

    public void addReadBook(ReadBook readBook) {
        readBooks.add(readBook);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }
}
