package com.demo.onlineLibraryAnaMariaDoroftei.entities;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name="bookCategory")
@RequiredArgsConstructor
public class BookCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique=true)
    private String name;
}
