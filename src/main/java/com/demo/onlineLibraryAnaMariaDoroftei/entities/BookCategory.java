package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString()
@NoArgsConstructor
@Entity
@Table(name="bookcategory")
public class BookCategory {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="name", unique=true)
    private String name;
}
