package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "books")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String isbn;
    private String title;
    private String authors;
    @ManyToOne
    private BookCategory bookCategory;
    private String thumbnailURL;
    @Column(length = 1000)
    private String description;
    private String publisher;
    private String language;
    private int yearOfPublishing;
    private int pages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return id != 0 && Objects.equals(id, book.id);
    }
}
